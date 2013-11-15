//
//  Repository.h
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__Repository__
#define __Labyrinth__Repository__

#include <iostream>
#include <vector>
#include "Utils.h"

class Repository {
private:
    matrix labyrinth;
    
    
public:
    void storeLabyrinth(matrix labyrinth);
    matrix getLabyrinth();
    
};

#endif /* defined(__Labyrinth__Repository__) */
