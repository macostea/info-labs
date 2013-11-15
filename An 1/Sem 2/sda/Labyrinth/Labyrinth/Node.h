//
//  Node.h
//  Labyrinth
//
//  Created by Mihai Costea on 28/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__Node__
#define __Labyrinth__Node__

#include <iostream>

template <class T>
class NodeAndPriority{
public:
    int priority;
    T data;
    NodeAndPriority<T> *left;
    NodeAndPriority<T> *right;

};

#endif /* defined(__Labyrinth__Node__) */
