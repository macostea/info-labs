//
//  utils.h
//  Lab1
//
//  Created by Mihai Costea on 13/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#pragma once

#include <pthread.h>

typedef struct {
    pthread_t   thread;
    bool        isAllocated;
} threadinfo_t;

