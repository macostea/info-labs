//
//  DynamicVector.h
//  Labyrinth
//
//  Created by Mihai Costea on 26/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__DynamicVector__
#define __Labyrinth__DynamicVector__

#include <iostream>
#include <vector>

using namespace std;

template <class T>
class DynamicVector {
    
public:
    DynamicVector();
    ~DynamicVector(){};
    void addElement(T element);
    void removeElementAtPosition(int position);
    T getElementAtPosition(int position);
    int getSize();
    
private:
    int capacity; //capacity of the vector
    int size; //number of elements in the vector
    vector<T> elements; //data container
};

template<class T>
DynamicVector<T>::DynamicVector() {
    this->size = 0;
    this->capacity = 50;
}

template<class T>
void DynamicVector<T>::addElement(T element){
    if (this->capacity == this->size){
        this->capacity*=2;
    }
    this->elements.push_back(element);
    this->size++;
}

template<class T>
void DynamicVector<T>::removeElementAtPosition(int position){
    this->elements.erase(this->elements.begin()+position);
    this->size--;
}

template<class T>
T DynamicVector<T>::getElementAtPosition(int position){
    return this->elements[position];
}

template<class T>
int DynamicVector<T>::getSize(){
    return this->size;
}

#endif /* defined(__Labyrinth__DynamicVector__) */
