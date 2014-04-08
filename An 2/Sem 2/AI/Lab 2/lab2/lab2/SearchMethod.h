//
//  SearchMethod.h
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab_1__SearchMethod__
#define __Lab_1__SearchMethod__

#include <iostream>

#include "Utils.h"
#include "Graph.h"

class SearchMethod {
    
public:
    virtual SearchResult findSolution(Graph *graph) = 0;
    virtual ~SearchMethod(){};
};

#endif /* defined(__Lab_1__SearchMethod__) */
