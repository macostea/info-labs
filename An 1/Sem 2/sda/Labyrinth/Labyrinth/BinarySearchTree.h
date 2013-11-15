//
//  BinaryTree.h
//  Labyrinth
//
//  Created by Mihai Costea on 28/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__BinaryTree__
#define __Labyrinth__BinaryTree__

#include <iostream>

#include "Node.h"


using namespace std;

template <class T>
class BinarySearchTree{
public:
    BinarySearchTree(void);
    ~BinarySearchTree(void);
    void insertEntry(T value, int priority);
    void deleteEntry(NodeAndPriority<T>* ptr, int priority);
    bool isThere(int value);
    NodeAndPriority<T> *root_ptr;
    
};

template <class T>
BinarySearchTree<T>::BinarySearchTree(void) {
    root_ptr = NULL;
}

template <class T>
BinarySearchTree<T>::~BinarySearchTree(void) {

}

template <class T>
void BinarySearchTree<T>::insertEntry(T value, int priority) {
    
    // create the new node to be inserted and populate it with data
    NodeAndPriority<T> *temp, *prev, *curr;
    temp = new NodeAndPriority<T>;
    temp->data = value;
    temp->left = NULL;
    temp->right = NULL;
    temp->priority = priority;
    
    // insert the node into its proper place in the tree
    if (root_ptr == NULL) {
        // the tree is empty so make the new node the root
        root_ptr = temp;
    } else {
        // discover the new nodes proper place in a binary search fashion
        curr = root_ptr;
        while (curr != NULL) {
            prev = curr;
            if (temp->priority < curr->priority)
                curr = curr->left;
            else
                curr = curr->right;
        }
        // the proper place has been found at this point.  insert the new node
        if (temp->priority < prev->priority)
            prev->left = temp;
        else if (temp->priority > prev->priority)
            prev->right = temp;
    }
    return;
}

template <class T>
void BinarySearchTree<T>::deleteEntry(NodeAndPriority<T>* ptr, int priority) {
    if (ptr==nullptr)
        return ;   // item not in BST
    
    if (priority < ptr->priority)
        deleteEntry(ptr->left, priority);
    else if (priority > ptr->priority)
        deleteEntry(ptr->right, priority);
    else
    {
        NodeAndPriority<T> *temp;
        
        if (ptr->left==nullptr)
        {
            temp = ptr->right;
            delete ptr;
            ptr = temp;
        }
        else if (ptr->right==nullptr)
        {
            temp = ptr->left;
            delete ptr;
            ptr = temp;
        }
        else    //2 children
        {
            temp = ptr->right;
            NodeAndPriority<T> *parent = nullptr;
            
            while(temp->left!=nullptr)
            {
                parent = temp;
                temp = temp->left;
            }
            ptr->data = temp->data;
            ptr->priority = temp->priority;
            if (parent != NULL)
                deleteEntry(parent->left, parent->left->priority);
            else
                deleteEntry(ptr->right, ptr->right->priority);
        }
    }
}

template <class T>
bool BinarySearchTree<T>::isThere(int value) {
    NodeAndPriority<T> *prev, *curr;
    
    curr = root_ptr;
    
    // find the right spot in an iterative fashion
    // recursion should be avoided whenever possible for safety and performance
    while (curr != NULL) {
        prev = curr;
        if (value < curr->priority)
            curr = curr->left;
        else if (value > curr->priority)
            curr = curr->right;
        else if (prev->priority == value)
            return true; // value was found
    }
    return false; //value wasn't found
}

#endif /* defined(__Labyrinth__BinaryTree__) */
