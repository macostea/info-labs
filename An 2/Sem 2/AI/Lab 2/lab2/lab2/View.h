//
//  View.h
//  lab2
//
//  Created by Mihai Costea on 08/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __lab2__View__
#define __lab2__View__

#include <iostream>

#include "Controller.h"
#include "Graph.h"

class View {
private:
    Controller *controller;
public:
    View(Controller *controller);
    
    void readGraphFromFile(std::string fileName);
    void getSolution();
};

#endif /* defined(__lab2__View__) */
