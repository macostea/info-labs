//
//  server.cpp
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
#include <semaphore.h>
#include <arpa/inet.h>

#include "utils.h"

threadinfo_t mWorkers[5];
sem_t mSemaphore;
sockaddr_in *mLargestSumClient;
message_t mLargestSumTriplet;
pthread_rwlock_t mRWLock;
int mServerSock;

void safeExit() {
    for (int it=0; it<5; it++) {
        pthread_join(mWorkers[it].thread, nullptr);
    }
    
    sem_destroy(&mSemaphore);
    pthread_rwlock_destroy(&mRWLock);
    
    close(mServerSock);
    
    exit(0);
}

void sigint_handler(int signal) {
    safeExit();
}

void *workerFunc(void *attr) {
    workerArgs_t *args = (workerArgs_t *) attr;
    int signal = kThreadCreated;
    send(args->clientSock, &signal, sizeof(int), 0);
    
    char *buffer = (char *)malloc(sizeof(message_t));
    
    message_t *receivedMessage = new message_t;
    recv(args->clientSock, buffer, sizeof(message_t), 0);
    memcpy(receivedMessage, buffer, sizeof(message_t)); // deserialize the data
    
    pthread_rwlock_rdlock(&mRWLock);
    if (receivedMessage->n1 + receivedMessage->n2 + receivedMessage->n3 > mLargestSumTriplet.n1 + mLargestSumTriplet.n2 + mLargestSumTriplet.n3) {
        pthread_rwlock_unlock(&mRWLock);
        pthread_rwlock_wrlock(&mRWLock);
        
        mLargestSumTriplet.n1 = receivedMessage->n1;
        mLargestSumTriplet.n2 = receivedMessage->n2;
        mLargestSumTriplet.n3 = receivedMessage->n3;
        
        mLargestSumClient = args->clientAddr;
        
        pthread_rwlock_unlock(&mRWLock);
        pthread_rwlock_rdlock(&mRWLock);
    }
    
    message_t sendToClient;
    sendToClient.n1 = mLargestSumTriplet.n1;
    sendToClient.n2 = mLargestSumTriplet.n2;
    sendToClient.n3 = mLargestSumTriplet.n3;
    inet_ntop(AF_INET, &mLargestSumClient->sin_addr, sendToClient.ip, sizeof(sendToClient.ip));
    
    memset(buffer, 0, sizeof(message_t));
    memcpy(buffer, &sendToClient, sizeof(message_t)); // serialize the data
    send(args->clientSock, buffer, sizeof(sendToClient), 0);
    
    pthread_rwlock_unlock(&mRWLock);
    
    mWorkers[args->threadId].isAllocated = false;
    close(args->clientSock);
    sem_post(&mSemaphore);
    
    pthread_exit(nullptr);
    return nullptr;
}

void createThread(int socket, struct sockaddr_in *clientAddr) {
    workerArgs_t *args = new workerArgs_t;
    args->clientAddr = clientAddr;
    args->clientSock = socket;
    
    for (int it=0; it<5; it++) {
        if (!mWorkers[it].isAllocated) {
            args->threadId = it;
            pthread_create(&mWorkers[it].thread, nullptr, workerFunc, args);
            mWorkers[it].isAllocated = true;
            return;
        }
    }
}

int main(int argc, const char * argv[]) {
    signal(SIGINT, sigint_handler);
    int clientSock;
    struct sockaddr_in serverAddr, clientAddr;
    mLargestSumClient = new sockaddr_in;
    mLargestSumTriplet.n1 = 0;
    mLargestSumTriplet.n2 = 0;
    mLargestSumTriplet.n3 = 0;
    for (int it=0; it<5; it++) {
        mWorkers[it].isAllocated = false;
    }
    
    pthread_rwlock_init(&mRWLock, nullptr);
    sem_init(&mSemaphore, 0, 5);
    
    mServerSock = socket(AF_INET, SOCK_STREAM, 0);
    
    if (mServerSock < 0) {
        perror("Failed to create socket!");
        return errno;
    }
    
    memset(&serverAddr, 0, sizeof(serverAddr));
    serverAddr.sin_port = htons(1111);
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    
    if (bind(mServerSock, (struct sockaddr *)&serverAddr, sizeof(serverAddr)) < 0) {
        perror("Failed to bind!");
        return errno;
    }
    
    listen(mServerSock, 5);
    
    socklen_t l;
    memset(&clientAddr, 0, sizeof(clientAddr));
    l = sizeof(clientAddr);
    while (1) {
        clientSock = accept(mServerSock, (struct sockaddr *)&clientAddr, &l);
        sem_wait(&mSemaphore);
        
        createThread(clientSock, &clientAddr);
    }
        
    safeExit();
    
    return 0;
}

