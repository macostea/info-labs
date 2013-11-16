//
//  LinkedList.h
//  Thread Sync
//
//  Created by Mihai Costea on 16/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Thread_Sync__LinkedList__
#define __Thread_Sync__LinkedList__

#include <iostream>
#include "Node.h"

class LinkedList {
public:
    Node *firstNode;
    ~LinkedList();
    int numberOfElements();
    void removeNode(Node *node);
    void printList();
};

#endif /* defined(__Thread_Sync__LinkedList__) */
