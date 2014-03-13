//
//  main.cpp
//  Lab1
//
//  Created by Mihai Costea on 13/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include <iostream>

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>

#include "utils.h"

int             _serverSock;
threadinfo_t    _workers[5];

void *workerFunction(void *args) {
    int *argsPtr = (int *)args;
    int clientSock = *argsPtr;
    
    int count1, count2;
    recv(clientSock, &count1, 4, 0);
    
    return nullptr;
}

void handleClient(int socket) {
    for (int it = 0; it < 5; it++) {
        if (!_workers[it].isAllocated) {
            pthread_create(&_workers[it].thread, nullptr, workerFunction, (void *)&socket);
        }
    }
}

int main(int argc, const char * argv[])
{
    int clientSock;
    struct sockaddr_in serverAddr, clientAddr;
    
    _serverSock = socket(AF_INET, SOCK_STREAM, 0);
    if (_serverSock < 0) {
        perror("Failed to create socket");
        return errno;
    }
    
    memset(&serverAddr, 0, sizeof(sockaddr_in));
    serverAddr.sin_port = htons(5555);
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    
    if (bind(_serverSock, (struct sockaddr *)&serverAddr, sizeof(sockaddr_in)) < 0) {
        perror("Failed to bind");
        return errno;
    }
    
    listen(_serverSock, 5);
    socklen_t l;
    memset(&clientAddr, 0, sizeof(sockaddr_in));
    l = sizeof(clientAddr);
    while (1) {
        clientSock = accept(_serverSock, (struct sockaddr *)&clientAddr, (socklen_t *)&l);
        
        handleClient(clientSock);
    }
    
    
    return 0;
}

