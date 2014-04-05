//
//  SearchMethod.h
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab_1__SearchMethod__
#define __Lab_1__SearchMethod__

#include <iostream>

#include "Tree.h"

class SearchMethod {
    
public:
    virtual TreeNode *generateSolutionTree(std::vector<int> vector) = 0;
    virtual std::vector<int> findSolutionForTree(TreeNode tree, bool printSteps = true) = 0;
};

#endif /* defined(__Lab_1__SearchMethod__) */
