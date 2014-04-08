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

typedef struct {
    std::vector<Edge *> e1; // First partition
    std::vector<Edge *> e2; // Second partition
} Position;

typedef struct {
    std::vector<double> e1_velocity;
    std::vector<double> e2_velocity;
} Velocity;

class Particle {
public:
    Velocity velocity;
    Position position;
    double fitness;
    
    Position bestPosition;
    double bestFitness;
    
    Particle(Velocity velocity, Position position, double fitness);
};

#endif /* defined(__lab2__Particle__) */
