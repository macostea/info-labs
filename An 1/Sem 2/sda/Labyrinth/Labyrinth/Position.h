//
//  Position.h
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__Position__
#define __Labyrinth__Position__

#include <iostream>

class Position {

public:
    Position(){};
    Position(int x, int y, char type, bool visited);
    int x,y;
    char type;
    bool visited;
    Position *previous;
};

#endif /* defined(__Labyrinth__Position__) */
