//
//  View.cpp
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "View.h"

View::View(Controller *controller) {
    this->controller = controller;
}

void View::readElements() {
    std::vector<int> vector;
    vector.push_back(2);
    vector.push_back(9);
    vector.push_back(5);
    vector.push_back(0);
    vector.push_back(1);
    vector.push_back(10);
    
    this->vector = vector;
}

void View::generateTree() {
    this->controller->generateSolutionTreeForVector(this->vector);
}