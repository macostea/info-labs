//
//  server.cpp
//  Sockets
//
//  Created by Mihai Costea on 11/28/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <semaphore.h>
#include <rpc/rpc.h>

#include "utils.h"
#include "rpc.h"

threadinfo_t mWorkers[5];
sem_t mSemaphore;
message_t mLargestSumTriplet;
pthread_rwlock_t mRWLock;

void safeExit() {
	int it=0;
    for (it=0; it<5; it++) {
        pthread_join(mWorkers[it].thread, NULL);
    }
    
    sem_destroy(&mSemaphore);
    pthread_rwlock_destroy(&mRWLock);
        
    exit(0);
}

void sigint_handler(int signal) {
    safeExit();
}

void *workerFunc(void *attr) {
	workerArgs_t *args = (workerArgs_t*)attr;
    message_t *receivedMessage = args->message;
    
    pthread_rwlock_rdlock(&mRWLock);
    if (receivedMessage->n1 + receivedMessage->n2 + receivedMessage->n3 > mLargestSumTriplet.n1 + mLargestSumTriplet.n2 + mLargestSumTriplet.n3) {
        pthread_rwlock_unlock(&mRWLock);
        pthread_rwlock_wrlock(&mRWLock);
        
        mLargestSumTriplet.n1 = receivedMessage->n1;
        mLargestSumTriplet.n2 = receivedMessage->n2;
        mLargestSumTriplet.n3 = receivedMessage->n3;
        
		gethostname(mLargestSumTriplet.ip, MAXSTRING);
        
        pthread_rwlock_unlock(&mRWLock);
        pthread_rwlock_rdlock(&mRWLock);
    }
    
    message_t sendToClient;
    sendToClient.n1 = mLargestSumTriplet.n1;
    sendToClient.n2 = mLargestSumTriplet.n2;
    sendToClient.n3 = mLargestSumTriplet.n3;
	strcpy(sendToClient.ip, mLargestSumTriplet.ip);

    pthread_rwlock_unlock(&mRWLock);
    
    mWorkers[args->threadId].isAllocated = 0;
    sem_post(&mSemaphore);
    
    pthread_exit(&sendToClient);
    return 0;
}

message_t *createThread(message_t *message) {   
	void *res;
	workerArgs_t *args = (workerArgs_t *)malloc(sizeof(workerArgs_t));
	args->message = message;
	int it = 0;
    for (it=0; it<5; it++) {
        if (!mWorkers[it].isAllocated) {
			args->threadId = it;
            pthread_create(&mWorkers[it].thread, NULL, workerFunc, args);
            mWorkers[it].isAllocated = 1;
            pthread_join(mWorkers[it].thread, &res);

			return (message_t *)res;
        }
    }
}

message_t *getLargestSumTriplet(message_t *message) {
	sem_wait(&mSemaphore);
	sleep(10);
    return createThread(message);
}

int main(int argc, const char * argv[]) {
    signal(SIGINT, sigint_handler);
    mLargestSumTriplet.n1 = 0;
    mLargestSumTriplet.n2 = 0;
    mLargestSumTriplet.n3 = 0;
	int it=0;
    for (it=0; it<5; it++) {
        mWorkers[it].isAllocated = 0;
    }
    
    pthread_rwlock_init(&mRWLock, NULL);
    sem_init(&mSemaphore, 0, 5);

	registerrpc(PROGRAM_EXEC, VERS_EXEC, EXEC_RUN, getLargestSumTriplet, xdr_message_t, xdr_message_t);
	
	svc_run();
    
    return 0;
}

