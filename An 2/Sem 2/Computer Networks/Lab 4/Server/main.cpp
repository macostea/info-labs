//
//  main.cpp
//  Server
//
//  Created by Mihai Costea on 29/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include <iostream>
#include <fcntl.h>
#include "Network.h"

struct sockaddr_in _clientAddr;
int _serverSock;
int SRVARG;

void handleClient(char message[256]) {
    int fd = open(message, O_RDONLY);
    
//    struct stat st;
//    stat(message, &st);
//    off_t size = st.st_size;
    
    char *buffer = (char *)malloc(SRVARG);
    
    size_t bytesRead = 0;
    
    Network network;
    while ((bytesRead = read(fd, buffer, SRVARG)) > 0) {
        network.reliableSend(_serverSock, buffer, bytesRead, (struct sockaddr *)&_clientAddr, (socklen_t)sizeof(struct sockaddr_in));
        memset(buffer, 0, SRVARG);
    }
    
    free(buffer);
    close(fd);
    
    exit(0);
}

int main(int argc, const char * argv[])
{
    if (argc != 2) {
        return -1;
    }
    SRVARG = atoi(argv[1]);
    
    struct sockaddr_in serverAddr;
    memset(&serverAddr, 0, sizeof(struct sockaddr_in));
    memset(&_clientAddr, 0, sizeof(struct sockaddr_in));
    
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(5555);
    
    if ((_serverSock = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
        perror("Failed to create socket!");
        return errno;
    }
    
    if (bind(_serverSock, (struct sockaddr *)&serverAddr, (socklen_t)sizeof(struct sockaddr_in)) < 0) {
        perror("Failed to bind!");
        return errno;
    }
    
//    while (1) {
        struct sockaddr_in clientAddr;
        memset(&clientAddr, 0, sizeof(struct sockaddr_in));
        char message[256];
        socklen_t len = (socklen_t)sizeof(struct sockaddr_in);
        
        Network network;
        network.reliableRecv(_serverSock, &message, 256, (struct sockaddr *)&clientAddr, &len);
        _clientAddr = clientAddr;
//        if (fork() == 0) {
            handleClient(message);
//        }
//    }
    
    return 0;
}

