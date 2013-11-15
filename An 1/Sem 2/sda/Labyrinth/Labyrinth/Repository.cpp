//
//  Repository.cpp
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "Repository.h"


void Repository::storeLabyrinth(matrix labyrinth){
    this->labyrinth = labyrinth;
}

matrix Repository::getLabyrinth(){
    return this->labyrinth;
    
    // Test labyrinth
    // Unreachable code
    matrix labyrinth(7, vector<Position>(7));
    labyrinth[0][0] = Position(0,0,'S',false);
    labyrinth[0][1] = Position(1,0,'0',false);
    labyrinth[0][2] = Position(2,0,'1',false);
    labyrinth[0][3] = Position(3,0,'1',false);
    labyrinth[0][4] = Position(4,0,'0',false);
    labyrinth[0][5] = Position(5,0,'0',false);
    labyrinth[0][6] = Position(6,0,'0',false);
    labyrinth[1][0] = Position(0,1,'0',false);
    labyrinth[1][1] = Position(1,1,'1',false);
    labyrinth[1][2] = Position(2,1,'0',false);
    labyrinth[1][3] = Position(3,1,'0',false);
    labyrinth[1][4] = Position(4,1,'1',false);
    labyrinth[1][5] = Position(5,1,'0',false);
    labyrinth[1][6] = Position(6,1,'0',false);
    labyrinth[2][0] = Position(0,2,'0',false);
    labyrinth[2][1] = Position(1,2,'0',false);
    labyrinth[2][2] = Position(2,2,'0',false);
    labyrinth[2][3] = Position(3,2,'0',false);
    labyrinth[2][4] = Position(4,2,'0',false);
    labyrinth[2][5] = Position(5,2,'0',false);
    labyrinth[2][6] = Position(6,2,'0',false);
    labyrinth[3][0] = Position(0,3,'0',false);
    labyrinth[3][1] = Position(1,3,'1',false);
    labyrinth[3][2] = Position(2,3,'0',false);
    labyrinth[3][3] = Position(3,3,'1',false);
    labyrinth[3][4] = Position(4,3,'0',false);
    labyrinth[3][5] = Position(5,3,'0',false);
    labyrinth[3][6] = Position(6,3,'1',false);
    labyrinth[4][0] = Position(0,4,'0',false);
    labyrinth[4][1] = Position(1,4,'1',false);
    labyrinth[4][2] = Position(2,4,'0',false);
    labyrinth[4][3] = Position(3,4,'0',false);
    labyrinth[4][4] = Position(4,4,'0',false);
    labyrinth[4][5] = Position(5,4,'0',false);
    labyrinth[4][6] = Position(6,4,'1',false);
    labyrinth[5][0] = Position(0,5,'0',false);
    labyrinth[5][1] = Position(1,5,'0',false);
    labyrinth[5][2] = Position(2,5,'0',false);
    labyrinth[5][3] = Position(3,5,'0',false);
    labyrinth[5][4] = Position(4,5,'1',false);
    labyrinth[5][5] = Position(5,5,'0',false);
    labyrinth[5][6] = Position(6,5,'G',false);
    labyrinth[6][0] = Position(0,6,'0',false);
    labyrinth[6][1] = Position(1,6,'1',false);
    labyrinth[6][2] = Position(2,6,'0',false);
    labyrinth[6][3] = Position(3,6,'1',false);
    labyrinth[6][4] = Position(4,6,'0',false);
    labyrinth[6][5] = Position(5,6,'0',false);
    labyrinth[6][6] = Position(6,6,'0',false);

    return labyrinth;
}