//
//  main.cpp
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include <iostream>

#include "Graph.h"
#include "Evolutive.h"
#include "PSO.h"

int main(int argc, const char * argv[])
{

    Graph *graph = new Graph();
    graph->nodes.insert(1);
    graph->nodes.insert(2);
    graph->nodes.insert(3);
    graph->nodes.insert(4);
    graph->nodes.insert(5);
    
    graph->edges.push_back(new Edge(1, 2));
    graph->edges.push_back(new Edge(2, 3));
    graph->edges.push_back(new Edge(3, 4));
    graph->edges.push_back(new Edge(4, 5));
    graph->edges.push_back(new Edge(5, 1));
    graph->edges.push_back(new Edge(1, 3));
    graph->edges.push_back(new Edge(1, 4));
    graph->edges.push_back(new Edge(3, 5));

//    Evolutive *evolutive = new Evolutive(4, graph, 0.3, 0.3, 2048, 2);
//    evolutive->findSolution();
    
    Velocity velocity;
    std::vector<double> e1_velocity, e2_velocity;
    e1_velocity.push_back(1);
    e1_velocity.push_back(1);
    e1_velocity.push_back(1);
    e1_velocity.push_back(1);
    e2_velocity.push_back(1);
    e2_velocity.push_back(1);
    e2_velocity.push_back(1);
    e2_velocity.push_back(1);
    
    velocity.e1_velocity = e1_velocity;
    velocity.e2_velocity = e2_velocity;

    
    PSO *pso = new PSO(velocity, 2, 0.729, 1.49445, VICINITY_TYPE_GLOBAL, graph);
    pso->findSolution();
    
    
//    delete evolutive;
    delete graph;
    
    return 0;
}

