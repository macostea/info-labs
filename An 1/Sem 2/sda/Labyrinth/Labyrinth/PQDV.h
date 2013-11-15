//
//  PQDV.h
//  Labyrinth
//
//  Created by Mihai Costea on 26/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//
//  ///////////////////////////////////
//  Priority Queue over Dynamic Vector
//

#ifndef Labyrinth_PQDV_h
#define Labyrinth_PQDV_h

#include <iostream>

#include "PriorityQueue.h"
#include "DynamicVector.h"

template <class T>
class PQDV: public PriorityQueue<T>  {

private:
    DynamicVector<T> elements;
    DynamicVector<int> priorities;
public:
    PQDV(){};
    ~PQDV(){};
    void insertElementWithPriority(T element, int priority);
    T popMin();
    T popMax();
    bool isEmpty();
};

template <class T>
void PQDV<T>::insertElementWithPriority(T element, int priority) {
    this->elements.addElement(element);
    this->priorities.addElement(priority);
}

template <class T>
T PQDV<T>::popMin(){
    int minPos=0;
    T min = this->elements.getElementAtPosition(0);
    for (int i=0; i<this->elements.getSize(); i++){
        if (this->elements.getElementAtPosition(i) < min){
            min = this->elements.getElementAtPosition(i);
            minPos = i;
        }
    }
    T elem = NULL;
    if (this->elements.getSize() != 0){
        elem = this->elements.getElementAtPosition(minPos);
        this->elements.removeElementAtPosition(minPos);
        this->priorities.removeElementAtPosition(minPos);
    }
    return elem;

}

template <class T>
T PQDV<T>::popMax(){
    int maxPos=0;
    T max = this->elements.getElementAtPosition(0);
    for (int i=0; i<this->elements.getSize(); i++){
        if (this->elements.getElementAtPosition(i) > max){
            max = this->elements.getElementAtPosition(i);
            maxPos = i;
        }
    }
    T elem = NULL;
    if (this->elements.getSize() != 0){
        elem = this->elements.getElementAtPosition(maxPos);
        this->elements.removeElementAtPosition(maxPos);
        this->priorities.removeElementAtPosition(maxPos);
    }
    return elem;
}

template <class T>
bool PQDV<T>::isEmpty() {
    if (this->elements.getSize() == 0){
        return true;
    }
    return false;
}

#endif
