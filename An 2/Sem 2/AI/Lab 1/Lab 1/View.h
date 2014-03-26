//
//  View.h
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab_1__View__
#define __Lab_1__View__

#include <iostream>
#include <vector>

#include "Controller.h"

class View {
    Controller *controller;
    std::vector<int> vector;
public:
    View(Controller *controller); // Constructor
    
    void readElements();
    void generateTree();
};

#endif /* defined(__Lab_1__View__) */
