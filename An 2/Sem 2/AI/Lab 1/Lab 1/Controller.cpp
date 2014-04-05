//
//  Controller.cpp
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Controller.h"

Controller::Controller(SearchMethod *searchMethod) {
    this->searchMethod = searchMethod;
}

void Controller::generateSolutionTreeForVector(std::vector<int> vector) {
    this->solutionTree = *this->searchMethod->generateSolutionTree(vector);
}

std::vector<int> Controller::findSolution() {
    return this->searchMethod->findSolutionForTree(this->solutionTree);
}