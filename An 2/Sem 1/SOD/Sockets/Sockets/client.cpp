//
//  client.cpp
//  Sockets
//
//  Created by Mihai Costea on 11/28/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <iostream>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <arpa/inet.h>

#include "utils.h"

int main(int argc, const char * argv[])
{
    int clientSock;
    struct sockaddr_in server;
    
    message_t *message = new message_t;
    message_t *resp = new message_t;
    
    clientSock = socket(AF_INET, SOCK_STREAM, 0);
    
    if (clientSock < 0) {
        perror("Failed to create socket!");
        return errno;
    }
    
    memset(&server, 0, sizeof(server));
    server.sin_port = htons(1111);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = inet_addr("127.0.0.1");
    
    if (connect(clientSock, (struct sockaddr *)&server, sizeof(server)) < 0) {
        perror("Failed to connect!");
        return errno;
    }
    
    srand((uint)time(nullptr));
    message->n1 = rand() % 100;
    message->n2 = rand() % 100;
    message->n3 = rand() % 100;
    
    int isThreadCreated = 0;
    recv(clientSock, &isThreadCreated, sizeof(isThreadCreated), 0);
    
    if (isThreadCreated == kThreadCreated) {
        send(clientSock, &message, sizeof(message_t), 0);
        
        int recvBytes = recv(clientSock, &resp, sizeof(message_t), 0);
        
        printf("The triplet with the largest sum is %f %f %f\n", resp->n1, resp->n2, resp->n3);
    }
    

    
    close(clientSock);
    
    return 0;
}

