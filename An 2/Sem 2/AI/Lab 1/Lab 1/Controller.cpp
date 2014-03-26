//
//  Controller.cpp
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Controller.h"
#include "SwitchOperator.h"

Controller::Controller() {
    
}

void Controller::generateSolutionTreeForVector(std::vector<int> vector) {
    this->solutionTree.data = vector;
    
    for (int it=1; it < vector.size(); it++) {
        std::vector<int> copy = vector;
        
    }
}