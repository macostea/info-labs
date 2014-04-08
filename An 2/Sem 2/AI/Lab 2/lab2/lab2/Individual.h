//
//  Individual.h
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __lab2__Individual__
#define __lab2__Individual__

#include <iostream>
#include <set>

#include "Edge.h"

class Individual {
public:
    std::set<Edge *> e1; // First partition
    std::set<Edge *> e2; // Second partition
    uint32_t fitness;
    
    ~Individual();
};

#endif /* defined(__lab2__Individual__) */
