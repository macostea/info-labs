//
//  Controller.cpp
//  lab2
//
//  Created by Mihai Costea on 08/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Controller.h"

Controller::Controller(SearchMethod *searchMethod) {
    this->searchMethod = searchMethod;
}

SearchResult Controller::findSolution() {
    return this->searchMethod->findSolution(this->graph);
}