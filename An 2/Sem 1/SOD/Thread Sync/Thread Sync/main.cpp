//
//  main.cpp
//  Thread Sync
//
//  Created by Mihai Costea on 16/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <iostream>
#include <math.h>
#include <pthread.h>
#include "LinkedList.h"

using namespace std;

static int const kSimultanousThreads = 3;

pthread_t *mWorkers;
pthread_mutex_t mClearSmallValuesMutex;
pthread_mutex_t mSimultaneousThreadsMutex[kSimultanousThreads];
LinkedList *mLinkedList;
typedef struct {
    int workerNumber;
}WorkerArgs;

void *workerFunction(void *args) {
    WorkerArgs *workerArgs = (WorkerArgs *)args;
    
    while (mLinkedList->numberOfElements() != 0) {
        int trylock = -1;
        int it = 0;
        while (trylock != 0) {
            trylock = pthread_mutex_trylock(&mSimultaneousThreadsMutex[it]);
            if (++it == kSimultanousThreads) {
                it = 0;
            }
        }
        Node *currNode = mLinkedList->firstNode;
        int numberOfRequiredElements = 0;
        
        while (currNode != nullptr) {
            if (currNode->worker == workerArgs->workerNumber) {
                currNode->value = sqrt(currNode->value);
            }
            if (currNode->value < 2) {
                numberOfRequiredElements++;
            }
            currNode = currNode->next;
        }
        
        if (numberOfRequiredElements >= 5 || numberOfRequiredElements == mLinkedList->numberOfElements()) {
            pthread_mutex_unlock(&mClearSmallValuesMutex);
        }
        
        pthread_mutex_unlock(&mSimultaneousThreadsMutex[it]);
    }
    
    return nullptr;
}

void clearSmallValues() {
    mLinkedList->printList();
    Node *currNode = mLinkedList->firstNode;
    while (currNode != nullptr) {
        if (currNode->value < 2) {
            mLinkedList->removeNode(currNode);
            currNode = mLinkedList->firstNode;
        } else {
            currNode = currNode->next;
        }
    }
}

int main(int argc, const char * argv[])
{
    int numberOfElements, numberOfWorkers;
    cin >> numberOfElements;
    cin >> numberOfWorkers;
    mLinkedList = new LinkedList();
    
    srand((int)time(nullptr));
    
    if (numberOfElements == 0) {
        return 0;
    } else {
        Node *firstNode = new Node;
        firstNode->value = rand() % 10000;
        mLinkedList->firstNode = firstNode;
    }
    
    mWorkers = new pthread_t[numberOfWorkers];
    
    Node *lastNode = mLinkedList->firstNode;
    int workerNumber = -1;
    
    for (int it=1; it<numberOfElements; it++) {
        Node *node = new Node;
        node->value = rand() % 10000;
        if (++workerNumber == numberOfWorkers) {
            workerNumber = 0;
        }
        node->worker = workerNumber;
        
        lastNode->next = node;
        lastNode = node;
    }
    
    pthread_mutex_init(&mClearSmallValuesMutex, nullptr);

    for (int it=0; it<kSimultanousThreads; it++) {
        pthread_mutex_init(&mSimultaneousThreadsMutex[it], nullptr);
    }
    pthread_mutex_lock(&mClearSmallValuesMutex);
    
    for (int it=0; it<numberOfWorkers; it++) {
        WorkerArgs *args = new WorkerArgs;
        args->workerNumber = it;
        pthread_create(&mWorkers[it], nullptr, workerFunction, args);
    }
    
    while (mLinkedList->numberOfElements() != 0) {
        pthread_mutex_lock(&mClearSmallValuesMutex);
        clearSmallValues();
    }
    
    for (int it=0; it<numberOfWorkers; it++) {
        pthread_join(mWorkers[it], nullptr);
    }
    
    pthread_mutex_destroy(&mClearSmallValuesMutex);
    for (int it=0; it<kSimultanousThreads; it++) {
        pthread_mutex_destroy(&mSimultaneousThreadsMutex[it]);
    }
    
    delete mLinkedList;
    
    return 0;
}

