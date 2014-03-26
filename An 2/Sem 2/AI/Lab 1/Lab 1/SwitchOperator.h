//
//  SwitchOperator.h
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab_1__SwitchOperator__
#define __Lab_1__SwitchOperator__

#include <iostream>
#include <vector>

template <typename T>
void switchElements(std::vector<T> *vector, int pos1, int pos2) {
    T temp = vector[pos1];
    vector[pos1] = vector[pos2];
    vector[pos2] = temp;
}

#endif /* defined(__Lab_1__SwitchOperator__) */
