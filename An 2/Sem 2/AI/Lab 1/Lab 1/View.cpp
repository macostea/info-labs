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
    for (int it=0; it<1000; it++) {
        vector.push_back(2);
        vector.push_back(9);
        vector.push_back(5);
        vector.push_back(0);
        vector.push_back(1);
        vector.push_back(10);
        vector.push_back(6);
    }

    this->vector = vector;
}

void View::generateTree() {
    time_t now = time(nullptr);
    this->controller->generateSolutionTreeForVector(this->vector);
    time_t finished = time(nullptr);
    std::cout << "Elapsed time: " << finished - now << std::endl;
}

void View::printTree() {
    std::vector<int> solution = this->controller->findSolution();
    
    std::cout << "Solution:";
    
    for (int i=0; i < solution.size(); i++) {
        std::cout << solution[i] << " ";
    }
    
    std::cout << std::endl;
}