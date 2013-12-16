//
//  utils.h
//  Sockets
//
//  Created by Mihai Costea on 11/28/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef Sockets_utils_h
#define Sockets_utils_h

#include <netinet/ip.h>
#include <netinet/in.h>

#define MAXSTRING 100

static const int kThreadCreated = 5; //random value;

typedef struct {
    float       n1;
    float       n2;
    float       n3;
    
    char        ip[MAXSTRING];
} message_t;

typedef struct {
    pthread_t   thread;
    int	        isAllocated;
} threadinfo_t;

typedef struct {
	message_t		*message;
    int             threadId;
} workerArgs_t;

#endif
