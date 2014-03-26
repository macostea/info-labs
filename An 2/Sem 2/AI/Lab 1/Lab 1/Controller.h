//
//  Controller.h
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab_1__Controller__
#define __Lab_1__Controller__

#include <iostream>
#include <vector>

#include "Tree.h"

class Controller {
    treeNode<std::vector<int>> solutionTree;
public:
    Controller();
    void generateSolutionTreeForVector(std::vector<int>vector);
};

#endif /* defined(__Lab_1__Controller__) */
