//
//  PQBST.h
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__PQBST__
#define __Labyrinth__PQBST__

#include <iostream>
#include "PriorityQueue.h"
#include "BinarySearchTree.h"

template <class T>
class PQBST: public PriorityQueue<T> {
    BinarySearchTree<T> bst;
    
public:
    PQBST(){};
    ~PQBST(){};
    void insertElementWithPriority(T element, int priority);
    T popMin();
    T popMax();
    bool isEmpty();
};

template <class T>
void PQBST<T>::insertElementWithPriority(T element, int priority) {
    bst.insertEntry(element, priority);
}

template <class T>
T PQBST<T>::popMin() {
    NodeAndPriority<T> *node = bst.root_ptr;
    while (node != NULL){
        if (node->left == NULL) break;
        node = node->left;
    }
    T data = node->data;
    bst.deleteEntry(bst.root_ptr, node->priority);
    return data;
}

template <class T>
T PQBST<T>::popMax() {
    NodeAndPriority<T> *node = bst.root_ptr;
    while (node != NULL){
        if (node->right == NULL) break;
        node = node->right;
    }
    T data = node->data;
    bst.deleteEntry(bst.root_ptr, node->priority);
    return data;
}

template <class T>
bool PQBST<T>::isEmpty() {
    if (bst.root_ptr == NULL) return true;
    return false;
}

#endif /* defined(__Labyrinth__PQBST__) */
