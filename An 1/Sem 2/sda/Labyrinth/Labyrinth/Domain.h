//
//  Domain.h
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __Labyrinth__Domain__
#define __Labyrinth__Domain__

#include <iostream>
#include "Utils.h"
#include "Position.h"
#include "PQDV.h"
#include "PQBST.h"

using namespace std;

namespace domain {
    vector<Position> getShortestPath(matrix labyrinth);

}
    

#endif /* defined(__Labyrinth__Domain__) */
