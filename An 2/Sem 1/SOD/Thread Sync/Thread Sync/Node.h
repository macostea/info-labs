//
//  Node.h
//  Thread Sync
//
//  Created by Mihai Costea on 16/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Thread_Sync__Node__
#define __Thread_Sync__Node__

#include <iostream>

typedef struct elem{
    int value;
    int worker;
    struct elem *next;
} Node;

#endif /* defined(__Thread_Sync__Node__) */
