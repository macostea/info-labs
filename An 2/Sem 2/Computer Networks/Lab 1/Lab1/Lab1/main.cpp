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
#include <arpa/inet.h>
#include <vector>

int             _serverSock;
int             _clientSock;

void timeOut(int signal) {
    int32_t resp = -1;
    resp = htonl(resp);
    printf("Time out!\n");
    send(_clientSock, &resp, sizeof(int32_t), 0);
    close(_clientSock);
    
    exit(1);
}

void handleClient() {
    uint32_t count1, count2;
    signal(SIGALRM, timeOut);
    alarm(10);
    recv(_clientSock, &count1, sizeof(uint32_t), 0);
    alarm(10);
    recv(_clientSock, &count2, sizeof(uint32_t), 0);
    alarm(0);
    
    std::vector<int32_t> string1, string2;
    
    for (int it=0; it<count1; it++) {
        int32_t no = 0;
        alarm(10);
        recv(_clientSock, &no, sizeof(int32_t), 0);
        string1.push_back(no);
    }
    alarm(0);
    for (int it=0; it<count2; it++) {
        int32_t no = 0;
        alarm(10);
        recv(_clientSock, &no, sizeof(int32_t), 0);
        string2.push_back(no);
    }
    alarm(0);
    
    //SUCCESS
    std::vector<int32_t> common;
    
    for (int it=0; it<string1.size(); it++) {
        for (int jt=0; jt<string2.size(); jt++) {
            if (string1[it] == string2[jt]) {
                common.push_back(string1[it]);
            }
        }
    }
    
    int32_t resp = (int32_t)common.size();
    send(_clientSock, &resp, sizeof(int32_t), 0);
    
    for (int it=0; it<common.size(); it++) {
        send(_clientSock, &common[it], sizeof(int32_t), 0);
    }
    
    close(_clientSock);
    
    exit(0);
}

int main(int argc, const char * argv[])
{
    struct sockaddr_in serverAddr, clientAddr;
    
    _serverSock = socket(AF_INET, SOCK_STREAM, 0);
    if (_serverSock < 0) {
        perror("Failed to create socket");
        return errno;
    }
    
    memset(&serverAddr, 0, sizeof(struct sockaddr_in));
    serverAddr.sin_port = htons(5555);
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    
    if (bind(_serverSock, (struct sockaddr *)&serverAddr, sizeof(struct sockaddr_in)) < 0) {
        perror("Failed to bind");
        return errno;
    }
    
    listen(_serverSock, 5);
    while (1) {
        socklen_t l;
        memset(&clientAddr, 0, sizeof(struct sockaddr_in));
        l = sizeof(clientAddr);
        _clientSock = accept(_serverSock, (struct sockaddr *)&clientAddr, (socklen_t *)&l);
        
        if (fork() == 0) {
            handleClient();
        }
    }
    
    return 0;
}

