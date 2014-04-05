//
//  main.cpp
//  Lab 1
//
//  Created by Mihai Costea on 26/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include <iostream>

#include <vector>
#include "Controller.h"
#include "View.h"

#include "GBFS.h"

using namespace std;

int main(int argc, const char * argv[])
{
    SearchMethod *searchMethod = new GBFS();
    View *view = new View(new Controller(searchMethod));
    
    view->readElements();
    view->generateTree();
    
    view->printTree();
    
    delete (view);
    return 0;
}

