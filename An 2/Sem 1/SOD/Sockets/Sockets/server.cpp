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

void *workerFunc(void *attr) {
    workerArgs_t *args = (workerArgs_t *) attr;
    int signal = kThreadCreated;
    send(args->clientSock, &signal, sizeof(int), 0);
    
    message_t *receivedMessage = new message_t;
    int recvBytes = recv(args->clientSock, receivedMessage, sizeof(message_t), 0);
    
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
    
    send(args->clientSock, &sendToClient, sizeof(sendToClient), 0);
    
    pthread_rwlock_unlock(&mRWLock);
    
    mWorkers[args->threadId].isAllocated = false;
    sem_post(&mSemaphore);
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
    int serverSock, clientSock;
    struct sockaddr_in serverAddr, clientAddr;
    mLargestSumClient = new sockaddr_in;
    memset(&mLargestSumTriplet, 0, sizeof(mLargestSumTriplet));
    for (int it=0; it<5; it++) {
        mWorkers[it].isAllocated = false;
    }
    
    pthread_rwlock_init(&mRWLock, nullptr);
    sem_init(&mSemaphore, 0, 5);
    
    serverSock = socket(AF_INET, SOCK_STREAM, 0);
    
    if (serverSock < 0) {
        perror("Failed to create socket!");
        return errno;
    }
    
    memset(&serverAddr, 0, sizeof(serverAddr));
    serverAddr.sin_port = htons(1111);
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    
    if (bind(serverSock, (struct sockaddr *)&serverAddr, sizeof(serverAddr)) < 0) {
        perror("Failed to bind!");
        return errno;
    }
    
    listen(serverSock, 5);
    
    socklen_t l;
    memset(&clientAddr, 0, sizeof(clientAddr));
    l = sizeof(clientAddr);
    while (1) {
        clientSock = accept(serverSock, (struct sockaddr *)&clientAddr, &l);
        sem_wait(&mSemaphore);
        
        createThread(clientSock, &clientAddr);
    }
        
    close(clientSock);
    close(serverSock);
    
    return 0;
}

