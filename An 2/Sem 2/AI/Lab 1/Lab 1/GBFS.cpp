//
//  GBFS.cpp
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "GBFS.h"

TreeNode *GBFS::generateSolutionTree(std::vector<int> vector) {
    int i,j;
    int iMin;
    
    TreeNode *tree = new TreeNode();
    tree->data = vector;
    
    std::vector<int> copy = vector;
    
    TreeNode *currentNode = tree;
    
    for (j = 0; j < copy.size() - 1; j++) {
        iMin = j;
        
        for (i = j+1; i < copy.size(); i++) {
            if (copy[i] < copy[iMin]) {
                iMin = i;
            }
        }
        
        if (iMin != j) {
            int temp = copy[iMin];
            copy[iMin] = copy[j];
            copy[j] = temp;
            
            TreeNode *child = new TreeNode();
            std::vector<TreeNode *> children;
            child->data = copy;
            children.push_back(child);
            currentNode->children = children;
            currentNode = child;
        }
    }
    
    return tree;
}

std::vector<int> GBFS::findSolutionForTree(TreeNode tree, bool printSteps) {
    TreeNode *currentNode = &tree;
    
    std::vector<int> solution;
    
    while (currentNode != nullptr) {
        if (printSteps) {
            for (int i=0; i < currentNode->data.size(); i++) {
                std::cout << currentNode->data[i] << " ";
            }
            std::cout << std::endl;
        }
        
        if (currentNode->children.size() != 0) {
            currentNode = currentNode->children[0];
        } else {
            solution = currentNode->data;
            break;
        }
    }
    
    return solution;
}