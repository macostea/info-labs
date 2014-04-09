//
//  DFS.cpp
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "DFS.h"

void swap(int *x, int *y) {
    int temp;
    temp = *x;
    *x = *y;
    *y = temp;
}

TreeNode *generateSolutionTree(std::vector<int> vector, int i) {
//    int j;
//    if (i == vector.size() - 1) {
//        
//    }
    return nullptr;
}

TreeNode *DFS::generateSolutionTree(std::vector<int> vector) {
    TreeNode *tree = new TreeNode();
    tree->data = vector;
    
    TreeNode *currentNode = tree;
    
    std::vector<TreeNode *> children;
    for (int i=0; i<vector.size() - 1; i++) {
        for (int j=i+1; j<vector.size(); j++) {
            std::vector<int> copy = currentNode->data;
            int temp = copy[i];
            copy[i] = copy[j];
            copy[j] = temp;
            
            TreeNode *child = new TreeNode();
            child->data = copy;
            children.push_back(child);
        }
    }
    
    return nullptr;
}