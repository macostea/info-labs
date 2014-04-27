//
//  PSO.cpp
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "PSO.h"
#include "Utils.h"

#define ARC4RANDOM_MAX      0x100000000

int valueFromVector(std::vector<bool> &vec) {
    int retval = 0;
    int i = 0;
    
    for (std::vector<bool>::iterator it = vec.end(); it!=vec.begin(); --it, i++) {
        if (*it) {
            retval |= 1<<i;
        }
    }
    
    return retval;
}

std::vector<bool> vectorFromValue(int value) {
    std::vector<bool> retVector;
    
    while (value != 0) {
        retVector.push_back(value % 2);
        value = value / 2;
    }
    
    std::reverse(retVector.begin(), retVector.end());
    
    return retVector;
}

PSO::PSO(int initialVelocity, int numberOfParticles, double inertiaWeight, double cognitiveWeight, VicinityType vicinityType) {
    this->initialVelocity = initialVelocity;
    this->numberOfParticles = numberOfParticles;
    this->inertiaWeight = inertiaWeight;
    this->cognitiveWeight = cognitiveWeight;
    this->socialWeight = cognitiveWeight;
    this->vicinityType = vicinityType;
    
    this->numberOfIterations = 1000;
    
    this->bestGlobalFitness = 0.0;
}

void PSO::initializeSwarm() {
    this->min = 0;
    std::vector<bool> maxVector;
    for (int it=0; it<this->data->edges.size(); it++) {
        maxVector.push_back(true);
    }
    
    this->max = valueFromVector(maxVector);
    
    for (int it=0; it<this->numberOfParticles; it++) {
        std::vector<bool> position;
        
        for (int i=0; i<this->data->edges.size(); i++) {
            bool it = arc4random_uniform(2);
            
            position.push_back(it);
        }
        
        Particle *particle = new Particle(this->initialVelocity, position, this->calculateFitness(position));
        
        if (particle->fitness > this->bestGlobalFitness) {
            bestGlobalFitness = particle->fitness;
            this->bestGlobalPosition = particle->position;
        }
        this->swarm.push_back(particle);
    }
}

uint32_t PSO::calculateFitness(std::vector<bool> &position) {
    return (uint32_t)numberOfEdgesOutsideTriangle(position, this->data);
}

void PSO::updateParticles() {
    for (int it=0; it<this->swarm.size(); it++) {
        Particle *particle = this->swarm[it];
        double r1 = (double)arc4random() / ARC4RANDOM_MAX;
        double r2 = (double)arc4random() / ARC4RANDOM_MAX;
        
        std::vector<bool> best_curr;
        for (int it=0; it<particle->bestPosition.size(); it++) {
            best_curr.push_back(particle->bestPosition[it] xor particle->position[it]);
        }
        
        std::vector<bool> globalBest_curr;
        for (int it=0; it<this->bestGlobalPosition.size(); it++) {
            globalBest_curr.push_back(this->bestGlobalPosition[it] xor particle->position[it]);
        }
        
        particle->velocity = this->inertiaWeight * particle->velocity + this->cognitiveWeight * r1 * valueFromVector(best_curr) + this->socialWeight * r2 * valueFromVector(globalBest_curr);
            
        if (particle->velocity < 1 - this->max) {
            particle->velocity = 1 - this->max;
        } else if (particle->velocity > this->max) {
            particle->velocity = this->max;
        }
        
        particle->position = vectorFromValue(valueFromVector(particle->position) + particle->velocity);
        
        int newPositionValue = valueFromVector(particle->position);
        if (newPositionValue > this->max) {
            newPositionValue = this->max;
        } else if (newPositionValue < this->min) {
            newPositionValue = this->min;
        }
        
        particle->position = vectorFromValue(newPositionValue);
        
        particle->fitness = this->calculateFitness(particle->position);
        if (particle->fitness > particle->bestFitness) {
            particle->bestPosition = particle->position;
            particle->bestFitness = particle->fitness;
        }
        
        if (particle->fitness > this->bestGlobalFitness) {
            this->bestGlobalFitness = particle->fitness;
            this->bestGlobalPosition = particle->position;
        }
    }
}

SearchResult PSO::findSolution(Graph *graph) {
    this->data = graph;
    this->initializeSwarm();
    
    std::cout << "Best position: " << std::endl;
    for (int it=0; it<this->bestGlobalPosition.size(); it++) {
        if (!this->bestGlobalPosition[it]) {
            std::cout << this->data->edges[it]->source << "," << this->data->edges[it]->destination << " ; ";
        }
    }
    
    std::cout << std::endl;
    
    for (int it=0; it<this->bestGlobalPosition.size(); it++) {
        if (this->bestGlobalPosition[it]) {
            std::cout << this->data->edges[it]->source << "," << this->data->edges[it]->destination << " ; ";
        }
    }
    
    std::cout << "Fitness = " << this->bestGlobalFitness << std::endl;
    
    for (int it=0; it<this->numberOfIterations; it++) {
        this->updateParticles();
        std::cout << "Best position: " << std::endl;
        for (int it=0; it<this->bestGlobalPosition.size(); it++) {
            if (!this->bestGlobalPosition[it]) {
                std::cout << this->data->edges[it]->source << "," << this->data->edges[it]->destination << " ; ";
            }
        }
        
        std::cout << std::endl;
        
        for (int it=0; it<this->bestGlobalPosition.size(); it++) {
            if (this->bestGlobalPosition[it]) {
                std::cout << this->data->edges[it]->source << "," << this->data->edges[it]->destination << " ; ";
            }
        }
        
        std::cout << std::endl;
        
        std::cout << "Fitness = " << this->bestGlobalFitness << std::endl;
        
        if (this->bestGlobalFitness == this->data->edges.size()) {
            break;
        }
    }
    
    SearchResult result;
    std::set<Edge *> e1, e2;
    
    for (int i=0; i<this->bestGlobalPosition.size(); i++) {
        if (!this->bestGlobalPosition[i]) {
            e1.insert(this->data->edges[i]);
        } else {
            e2.insert(this->data->edges[i]);
        }
    }
    
    result.e1 = e1;
    result.e2 = e2;
    
    return result;
}
