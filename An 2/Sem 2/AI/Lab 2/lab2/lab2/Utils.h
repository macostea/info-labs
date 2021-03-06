//
//  Utils.h
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __lab2__Utils__
#define __lab2__Utils__

#include <iostream>
#include <set>
#include <vector>

#include "Edge.h"
#include "Graph.h"

size_t numberOfEdgesOutsideTriangle(std::set<Edge *> &edges);
size_t numberOfEdgesOutsideTriangle(std::vector<bool> &edges, Graph *data);

typedef struct{
    std::set<Edge *> e1; // First partition
    std::set<Edge *> e2; // Second partition
} SearchResult;

#endif /* defined(__lab2__Utils__) */
