//
//  Position.cpp
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "Position.h"


Position::Position(int x, int y, char type, bool visited){
    this->x = x;
    this->y = y;
    this->type = type;
    this->visited = visited;
}