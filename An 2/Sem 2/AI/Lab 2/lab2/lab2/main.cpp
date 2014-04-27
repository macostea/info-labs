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

#include "Controller.h"
#include "View.h"

int main(int argc, const char * argv[])
{
    SearchMethod *evolutive = new Evolutive(4, 0.3, 0.3, 2048, 2);
    SearchMethod *pso = new PSO(1, 2, 0.729, 1.49445, VICINITY_TYPE_GLOBAL);
//    Controller *controller = new Controller(evolutive);
    Controller *controller = new Controller(pso);
    
    View *view = new View(controller);
    view->readGraphFromFile("graph.txt");
    view->getSolution();
    
    delete view;
    delete controller;
    delete pso;
    delete evolutive;
    
    return 0;
}

