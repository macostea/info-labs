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
#include "SearchMethod.h"

class Controller {
    SearchMethod *searchMethod;
public:
    TreeNode solutionTree;
    
    Controller(SearchMethod *searchMethod);
    void generateSolutionTreeForVector(std::vector<int>vector);
    std::vector<int> findSolution();
};

#endif /* defined(__Lab_1__Controller__) */
