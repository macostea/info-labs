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
#include "SearchMethod.h"
#include "Graph.h"
#include "Particle.h"

typedef enum {
    VICINITY_TYPE_LOCAL,
    VICINITY_TYPE_GLOBAL
} VicinityType;

class PSO : public SearchMethod {
private:
    void initializeSwarm();
    uint32_t calculateFitness(std::vector<bool> &position);
    void updateParticles();
    
public:
    int initialVelocity;
    int numberOfParticles;
    double inertiaWeight;
    double cognitiveWeight;
    double socialWeight;
    VicinityType vicinityType;
    int max;
    int min;
    int numberOfIterations;
    
    std::vector<bool> bestGlobalPosition;
    double bestGlobalFitness;
    
    std::vector<Particle *> swarm;
    
    Graph *data;
    
    PSO(int initialVelocity, int numberOfParticles, double inertiaWeight, double cognitiveWeight, VicinityType vicinityType);
    
    virtual SearchResult findSolution(Graph *graph);
};

#endif /* defined(__lab2__PSO__) */
