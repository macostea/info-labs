//
//  UI.h
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__UI__
#define __Labyrinth__UI__

#include <iostream>
#include <vector>
#include "Controller.h"
#include "Utils.h"

class UI {
private:
    Controller *controller;
    char menu();
    void defineLabyrinth();
    void getShortestPath();
    void prettyPrintLabyrinth();
    
public:
    UI(Controller *controller);
    void run();
};

#endif /* defined(__Labyrinth__UI__) */
