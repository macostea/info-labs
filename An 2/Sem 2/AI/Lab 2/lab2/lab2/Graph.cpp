//
//  Graph.cpp
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Graph.h"

Graph::~Graph() {
    for (int it=0; it<edges.size(); it++) {
        delete edges[it];
    }
}