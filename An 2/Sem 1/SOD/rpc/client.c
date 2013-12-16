//
//  client.cpp
//  Sockets
//
//  Created by Mihai Costea on 11/28/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "rpc.h"
#include "utils.h"

int main(int argc, const char * argv[])
{   
	message_t *message = (message_t *)malloc(sizeof(message_t));
	message_t *resp = (message_t *)malloc(sizeof(message_t));
    
    srand((uint)time(NULL));
    message->n1 = rand() % 100;
    message->n2 = rand() % 100;
    message->n3 = rand() % 100;

	callrpc("localhost", PROGRAM_EXEC, VERS_EXEC, EXEC_RUN, (xdrproc_t)xdr_message_t, (char *)message, (xdrproc_t)xdr_message_t, (char *)resp);
        
	printf("The triplet with the largest sum is %f %f %f\n", resp->n1, resp->n2, resp->n3);
    printf("The ip of the client with the largest sum is: %s\n", resp->ip);

    return 0;
}

