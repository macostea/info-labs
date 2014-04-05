//
//  GBFS.h
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab_1__GBFS__
#define __Lab_1__GBFS__

#include <iostream>
#include "BestFS.h"
#include "Tree.h"
#include "SwitchOperator.h"

class GBFS : public BestFS {
    
public:
    virtual TreeNode *generateSolutionTree(std::vector<int> vector);
    virtual std::vector<int> findSolutionForTree(TreeNode tree, bool printSteps = true);
};

#endif /* defined(__Lab_1__GBFS__) */
