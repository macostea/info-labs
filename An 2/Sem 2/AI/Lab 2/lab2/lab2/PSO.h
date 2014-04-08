//
//  PSO.h
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __lab2__PSO__
#define __lab2__PSO__

#include <iostream>
#include <vector>
#include "Graph.h"
#include "Particle.h"

typedef enum {
    VICINITY_TYPE_LOCAL,
    VICINITY_TYPE_GLOBAL
} VicinityType;

class PSO {
private:
    void initializeSwarm();
    uint32_t calculateFitness(Position &position);
    void updateParticles();
    
public:
    Velocity initialVelocity;
    int numberOfParticles;
    double inertiaWeight;
    double cognitiveWeight;
    double socialWeight;
    VicinityType vicinityType;
    int max;
    int min;
    int numberOfIterations;
    
    Position bestGlobalPosition;
    double bestGlobalFitness;
    
    std::vector<Particle *> swarm;
    
    Graph *data;
    
    PSO(Velocity initialVelocity, int numberOfParticles, double inertiaWeight, double cognitiveWeight, VicinityType vicinityType, Graph *data);
    void findSolution();
};

#endif /* defined(__lab2__PSO__) */
