//
//  Particle.h
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __lab2__Particle__
#define __lab2__Particle__

#include <iostream>
#include <set>
#include <vector>
#include "Edge.h"

class Particle {
public:
    int velocity;
    std::vector<bool> position;
    double fitness;
    
    std::vector<bool> bestPosition;
    double bestFitness;
    
    Particle(int velocity, std::vector<bool> position, double fitness);
};

#endif /* defined(__lab2__Particle__) */
