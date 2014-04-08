//
//  Graph.h
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __lab2__Graph__
#define __lab2__Graph__

#include <iostream>
#include <set>
#include <vector>
#include "Edge.h"

class Graph {
    
public:
    std::set<int> nodes;
    std::vector<Edge *> edges;
    
    ~Graph();
};

#endif /* defined(__lab2__Graph__) */
