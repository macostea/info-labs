//
//  Network.h
//  Lab 2
//
//  Created by Mihai Costea on 13/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab_2__Network__
#define __Lab_2__Network__

#include <iostream>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <netinet/in.h>

static int const MAXPKTSIZE = 512;
static int const MAXRETRY   = 5;
static int const TIMEOUT    = 5;

typedef struct {
    uint32_t sequenceNo;
    uint16_t payloadLen;
    uint16_t ackFlag;
    uint32_t ackValue;
    char     payload[MAXPKTSIZE];
} Packet;

class Network {
    int sequenceNo;
    int dropPacket;
    int retrySend;
    size_t packetSend(int sockfd, Packet *packet, size_t bufflen, const struct sockaddr *dest_addr, socklen_t destlen);
    size_t packetRecv(int sockfd, void *buffer, size_t bufflen, struct sockaddr *src_addr, socklen_t *srclen);
public:
    Network();
    size_t reliableSend(int sockfd, void *buffer, size_t bufflen, const struct sockaddr *dest_addr, socklen_t destlen);
    size_t reliableRecv(int sockfd, void *buffer, size_t bufflen, struct sockaddr *src_addr, socklen_t *srclen);
};



#endif /* defined(__Lab_2__Network__) */
