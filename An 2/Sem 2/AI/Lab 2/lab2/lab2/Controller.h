//
//  Controller.h
//  lab2
//
//  Created by Mihai Costea on 08/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __lab2__Controller__
#define __lab2__Controller__

#include <iostream>

#include "SearchMethod.h"

class Controller {
private:
    SearchMethod *searchMethod;
public:
    Graph *graph;
    
    Controller(SearchMethod *searchMethod);
    SearchResult findSolution();
};

#endif /* defined(__lab2__Controller__) */
