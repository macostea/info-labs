//
//  main.cpp
//  Client
//
//  Created by Mihai Costea on 29/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include <iostream>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <fcntl.h>

int CLTARG;
int _fd, _clientSock;
char *_buffer;

void timeout(int signal) {
    close(_fd);
    close(_clientSock);
    
    free(_buffer);
    
    exit(0);
}

int main(int argc, const char * argv[])
{
    if (argc != 2) {
        return -1;
    }
    
    CLTARG = atoi(argv[1]);
    
    struct sockaddr_in serverAddr;
    memset(&serverAddr, 0, sizeof(struct sockaddr_in));
    
    if ((_clientSock = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
        perror("Failed to create socket!");
        return errno;
    }
    
    struct hostent *host;
    host = gethostbyname("localhost");
    if (!host) {
        perror("Could not obtain host");
        return errno;
    }
    
    memcpy(&serverAddr.sin_addr, host->h_addr_list[0], host->h_length);
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(5555);
    
    char message[256] = "test.txt";
    
    sendto(_clientSock, &message, 256, 0, (struct sockaddr *)&serverAddr, (socklen_t)sizeof(struct sockaddr_in));
    
    _buffer = (char *)malloc(CLTARG);
    
    memset(&serverAddr, 0, sizeof(struct sockaddr_in));
    socklen_t sockLen = sizeof(struct sockaddr_in);
    
    char filename[256] = "recv_test.txt";
    _fd = open(filename, O_CREAT|O_APPEND|O_WRONLY);
    
    signal(SIGALRM, timeout);
    
    alarm(5); // reasonable amount of time i say
    while (1) {
        alarm(5);
        size_t receivedBytes = recvfrom(_clientSock, _buffer, CLTARG, 0, (struct sockaddr *)&serverAddr, &sockLen);
        alarm(0);
        
        write(_fd, _buffer, receivedBytes);
        memset(_buffer, 0, CLTARG);
    }
    
    return 0;
}

