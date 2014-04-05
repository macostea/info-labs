//
//  Tree.h
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab_1__Tree__
#define __Lab_1__Tree__

#include <iostream>
#include <vector>

class TreeNode {
    
public:
    std::vector<int> data;
    std::vector<TreeNode *> children;
};

#endif /* defined(__Lab_1__Tree__) */
