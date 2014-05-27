//
//  Network.cpp
//  Lab 2
//
//  Created by Mihai Costea on 13/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Network.h"

#include <sys/select.h>

Network::Network() {
    sequenceNo = 0;
    retrySend = 0;
    dropPacket = 0;
}

size_t Network::reliableSend(int sockfd, void *buffer, size_t bufflen, const struct sockaddr *dest_addr, socklen_t destlen) {
    Packet packet;
    packet.sequenceNo = this->sequenceNo;
    packet.payloadLen = bufflen;
    memcpy(packet.payload, buffer, bufflen);
    packet.ackFlag = 0;
    
    return this->packetSend(sockfd, &packet, sizeof(packet), dest_addr, destlen);
}

size_t Network::reliableRecv(int sockfd, void *buffer, size_t bufflen, struct sockaddr *src_addr, socklen_t *srclen) {
    return this->packetRecv(sockfd, buffer, bufflen, src_addr, srclen);
}

size_t Network::packetSend(int sockfd, Packet *packet, size_t bufflen, const struct sockaddr *dest_addr, socklen_t destlen) {
    if (this->retrySend == MAXRETRY) {
        return -1;
    }
    
    printf("Sending packet with seq no: %d\n", packet->sequenceNo);
    printf("Payload length: %d\n", packet->payloadLen);
    
    if (packet->payloadLen > 0) {
        bool gotResponse = false;
        int numberOfTries = 0;
        size_t sendSize = 0;
        while (numberOfTries < MAXRETRY && !gotResponse) {
            fd_set fdSet;
            struct timeval timeout;
            timeout.tv_sec = 0;
            timeout.tv_usec = 50000;
            FD_ZERO(&fdSet);
            FD_SET(sockfd, &fdSet);
            
            sendSize = sendto(sockfd, packet, bufflen, 0, dest_addr, destlen);
            if (select(sockfd + 1, &fdSet, nullptr, nullptr, &timeout) > 0) {
                gotResponse = true;
            }
            numberOfTries++;
        }
        
        if (!gotResponse) {
            return -1;
        }
        
        Packet ack;
        socklen_t acklen;
        struct sockaddr_in ackAddr;
        recvfrom(sockfd, &ack, MAXPKTSIZE, 0, (struct sockaddr *)&ackAddr, &acklen);
        
        printf("Got packet with seq no: %d\n", ack.sequenceNo);
        printf("Payload length: %d\n", ack.payloadLen);
        if (ack.ackValue == this->sequenceNo + 1) {
            this->sequenceNo++;
            retrySend = 0;
            return sendSize;
        } else {
            sleep(TIMEOUT);
            retrySend++;
            return this->packetSend(sockfd, packet, bufflen, dest_addr, destlen);
        }
    
        return sendSize;
    } else {
        return sendto(sockfd, packet, bufflen, 0, dest_addr, destlen);
    }
}

size_t Network::packetRecv(int sockfd, void *buffer, size_t bufflen, struct sockaddr *src_addr, socklen_t *srclen) {
    fd_set fdSet;
    FD_ZERO(&fdSet);
    FD_SET(sockfd, &fdSet);
    this->retrySend = 0;
    Packet packet;
    
    dropPacket++;
    if (select(sockfd + 1, &fdSet, nullptr, nullptr, nullptr) > 0) {
        recvfrom(sockfd, &packet, sizeof(packet), 0, src_addr, srclen);
        printf("Got packet with seq no: %d\n", packet.sequenceNo);
        printf("Payload length: %d\n", packet.payloadLen);
        if (packet.sequenceNo == sequenceNo) {
            if (dropPacket % 4 != 0) {
                memcpy(buffer, packet.payload, packet.payloadLen);
                sequenceNo++;
                Packet ackPacket;
                ackPacket.sequenceNo = sequenceNo;
                ackPacket.ackFlag = 1;
                ackPacket.ackValue = sequenceNo;
                ackPacket.payloadLen = 0;
                printf("Drop packet: %d\n", dropPacket);
                printf("Sending acknowledgment\n");
                this->packetSend(sockfd, &ackPacket, sizeof(ackPacket), src_addr, *srclen);
            } else {
            printf("NOT SENDING ACKNOWLEDGEMENT for packet: %d\n", packet.sequenceNo);
            }
        } else {
            Packet ackPacket;
            ackPacket.ackFlag = 1;
            ackPacket.ackValue = this->sequenceNo;
            this->packetSend(sockfd, &ackPacket, sizeof(ackPacket), src_addr, *srclen);
        }
        
        return packet.payloadLen;
    }
    
    return 0;
}