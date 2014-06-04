//
//  main.cpp
//  Lab5
//
//  Created by Mihai Costea on 29/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include <iostream>

#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#include <vector>

typedef struct {
    char version[8];
    char roomName[32];
    char id[32];
    char oper;
    uint16_t msgLen;
    char *message;
} Message;

std::vector<std::string> _rooms;
pthread_t _recvThread;

int _sock;
struct sockaddr_in _addr;

char _userId[32];

bool joinedRoom(char roomName[32]) {
    bool joinedRoom = false;
    for (int it=0; it<_rooms.size(); it++) {
        if (_rooms[it].compare(roomName) == 0) {
            joinedRoom = true;
            break;
        }
    }
    
    return joinedRoom;
}

void handleMessage(Message message) {
    switch (message.oper) {
        case '1':
            if (joinedRoom(message.roomName)) {
                std::cout << "user " << message.id << " joined room " << message.roomName << std::endl;
            }
            break;
            
        case '2':
            if (joinedRoom(message.roomName)) {
                std::cout << "user " << message.id << " left room " << message.roomName << std::endl;
            }
            break;
            
        case '3':
            if (joinedRoom(message.roomName)) {
                std::cout << "got message " << message.message << " in room " << message.roomName << " from user " << message.id << std::endl;
            }
            break;
            
        case '4':{
            Message message;
            strcpy(message.version, "CHATv1");
            strcpy(message.id, _userId);
            message.oper = '5';
            std::string rooms;
            for (int it=0; it<_rooms.size(); it++) {
                rooms.append(_rooms[it]);
                rooms.append("\0");
            }
            rooms.append("\0");
            
            message.msgLen = rooms.length();
            memcpy(message.message, rooms.c_str(), UINT16_MAX);
            
            sendto(_sock, &message, sizeof(message), 0, (struct sockaddr *)&_addr, sizeof(_addr));
            
            break;
        }
            
        default:
            break;
    }
}

void *recvFunction(void *args) {
    while (1) {
        struct sockaddr_in addr;
        memset(&addr, 0, sizeof(addr));
        socklen_t len = 0;
        
        Message message;
        
        size_t recvLen = recvfrom(_sock, &message, sizeof(message), 0, (struct sockaddr *)&addr, &len);
        if (recvLen > 0) {
            handleMessage(message);
        }
    }
    
    return nullptr;
}

int main(int argc, const char * argv[])
{
    strcpy(_userId, "lelman");
    if ((_sock = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP) < 0)) {
        perror("Error creating socket");
        exit(1);
    }
    
    int on = 1;
    if (setsockopt(_sock, SOL_SOCKET, SO_REUSEADDR, &on, sizeof(on)) < 0) {
        perror("Error setting reuse addr");
        exit(1);
    }
    
    _addr.sin_addr.s_addr = INADDR_ANY;
    _addr.sin_family = AF_INET;
    _addr.sin_port = htons(7777);
    
    if (bind(_sock, (struct sockaddr *)&_addr, sizeof(_addr)) < 0) {
        perror("Error binding socket");
        exit(1);
    }
    
    struct ip_mreq multicastRequest;
    multicastRequest.imr_multiaddr.s_addr = inet_addr("224.100.101.102");
    multicastRequest.imr_interface.s_addr = htonl(INADDR_ANY);
    
    if (setsockopt(_sock, IPPROTO_IP, IP_ADD_MEMBERSHIP, (void *)&multicastRequest, sizeof(multicastRequest)) < 0) {
        perror("Error adding membership");
        exit(1);
    }
    
    pthread_create(&_recvThread, nullptr, recvFunction, nullptr);
    
    return 0;
}

