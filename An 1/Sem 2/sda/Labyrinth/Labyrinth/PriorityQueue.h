//
//  PriorityQueue.h
//  Labyrinth
//
//  Created by Mihai Costea on 26/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__PriorityQueue__
#define __Labyrinth__PriorityQueue__

#include <iostream>

template <class T>
class PriorityQueue {
public:
    PriorityQueue(){};
    virtual ~PriorityQueue(){};
    virtual void insertElementWithPriority(T element, int priority) = 0;
    virtual T popMin() = 0;
    virtual T popMax() = 0;
    virtual bool isEmpty() = 0;
};

#endif /* defined(__Labyrinth__PriorityQueue__) */
