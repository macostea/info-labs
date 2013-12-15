//
//  utils.h
//  Sockets
//
//  Created by Mihai Costea on 11/28/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef Sockets_utils_h
#define Sockets_utils_h

#include <iostream>
#include <netinet/ip.h>
#include <netinet/in.h>


static const int kThreadCreated = 5; //random value;

typedef struct {
    float       n1;
    float       n2;
    float       n3;
    
    char        ip[INET6_ADDRSTRLEN];
} message_t;

typedef struct {
    pthread_t   thread;
    bool        isAllocated;
} threadinfo_t;

typedef struct {
    int             clientSock;
    sockaddr_in     *clientAddr;
    int             threadId;
} workerArgs_t;

#endif
