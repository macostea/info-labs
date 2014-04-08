//
//  Particle.cpp
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Particle.h"

Particle::Particle(Velocity velocity, Position position, double fitness) {
    this->velocity = velocity;
    this->position = position;
    this->fitness = fitness;
    
    this->bestFitness = fitness;
    this->bestPosition = position;
}