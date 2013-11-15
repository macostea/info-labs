//
//  Controller.h
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__Controller__
#define __Labyrinth__Controller__

#include <iostream>
#include <vector>
#include "Repository.h"
#include "Domain.h"
#include "Utils.h"

class Controller {
private:
    Repository* repo;
public:
    Controller(Repository* repo);
    void storeLabyrinth(matrix labyrinth);
    matrix getLabyrinth();
    vector<Position> getShortestPath();

};

#endif /* defined(__Labyrinth__Controller__) */
