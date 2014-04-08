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

PSO::PSO(Velocity initialVelocity, int numberOfParticles, double inertiaWeight, double cognitiveWeight, VicinityType vicinityType, Graph *data) {
    this->initialVelocity = initialVelocity;
    this->numberOfParticles = numberOfParticles;
    this->inertiaWeight = inertiaWeight;
    this->cognitiveWeight = cognitiveWeight;
    this->socialWeight = cognitiveWeight;
    this->vicinityType = vicinityType;
    this->data = data;
    
    this->min = 0;
    this->max = (int)this->data->edges.size() - 1;
    
    this->numberOfIterations = 1000;
    
    this->bestGlobalFitness = 0.0;
    
    this->initializeSwarm();
}

void PSO::initializeSwarm() {
    for (int it=0; it<this->numberOfParticles; it++) {
        Position position;
        
        for (int i=0; i<this->data->edges.size() / 2; i++) {
            bool found = true;
            uint32_t it = 0;
            while (found) {
                found = false;
                it = arc4random_uniform((uint32_t)this->data->edges.size());
                for (int j=0; j<position.e1.size(); j++) {
                    for (int k=0; k<position.e1.size(); k++) {
                        if (position.e1[k] == this->data->edges[it]) {
                            found = true;
                            break;
                        }
                    }
                    
                    if (found) {
                        break;
                    }
                }
            }
            
            position.e1.push_back(this->data->edges[it]);
        }
        
        for (int i=0; i<this->data->edges.size() / 2; i++) {
            bool found = true;
            uint32_t it = 0;
            while (found) {
                found = false;
                it = arc4random_uniform((uint32_t)this->data->edges.size());
                for (int j=0; j<position.e1.size(); j++) {
                    for (int k=0; k<position.e1.size(); k++) {
                        if (position.e1[k] == this->data->edges[it]) {
                            found = true;
                            break;
                        }
                    }
                    
                    if (found) {
                        break;
                    }

                }
                
                for (int j=0; j<position.e2.size(); j++) {
                    for (int k=0; k<position.e2.size(); k++) {
                        if (position.e2[k] == this->data->edges[it]) {
                            found = true;
                            break;
                        }
                    }
                    
                    if (found) {
                        break;
                    }

                }
            }
            
            position.e2.push_back(this->data->edges[it]);
        }
        
        Particle *particle = new Particle(this->initialVelocity, position, this->calculateFitness(position));
        
        if (particle->fitness > this->bestGlobalFitness) {
            bestGlobalFitness = particle->fitness;
            this->bestGlobalPosition = particle->position;
        }
        this->swarm.push_back(particle);
    }
}

uint32_t PSO::calculateFitness(Position &position) {
    size_t e1_fitness = numberOfEdgesOutsideTriangle(position.e1);
    size_t e2_fitness = numberOfEdgesOutsideTriangle(position.e2);
    
    return (uint32_t)e1_fitness + (uint32_t)e2_fitness;
}

void PSO::updateParticles() {
    for (int it=0; it<this->swarm.size(); it++) {
        Particle *particle = this->swarm[it];
        
        for (int j=0; j < particle->velocity.e1_velocity.size(); j++) {
            double r1 = (double)arc4random() / ARC4RANDOM_MAX;
            double r2 = (double)arc4random() / ARC4RANDOM_MAX;
            
            int e1_bestIndex = 0, e2_bestIndex = 0;
            int e1_globalBestIndex = 0, e2_globalBestIndex = 0;
            int e1_currentIndex = 0, e2_currentIndex = 0;
            for (int k=0; k<this->data->edges.size(); k++) {
                if (this->data->edges[k] == particle->bestPosition.e1[j]) {
                    e1_bestIndex = k;
                }
                
                if (this->data->edges[k] == particle->bestPosition.e2[j]) {
                    e2_bestIndex = k;
                }
                
                if (this->data->edges[k] == this->bestGlobalPosition.e1[j]) {
                    e1_globalBestIndex = k;
                }
                
                if (this->data->edges[k] == this->bestGlobalPosition.e2[j]) {
                    e2_globalBestIndex = k;
                }
                
                if (this->data->edges[k] == particle->position.e1[j]) {
                    e1_currentIndex = k;
                }
                
                if (this->data->edges[k] == particle->position.e2[j]) {
                    e2_currentIndex = k;
                }
            }
            
            particle->velocity.e1_velocity[j] = (this->inertiaWeight * particle->velocity.e1_velocity[j]) + (this->cognitiveWeight * r1 * (e1_bestIndex - e1_currentIndex)) + (this->socialWeight * r2 * (e1_globalBestIndex - e1_currentIndex));
            particle->velocity.e2_velocity[j] = (this->inertiaWeight * particle->velocity.e2_velocity[j]) + (this->cognitiveWeight * r1 * (e2_bestIndex - e2_currentIndex)) + (this->socialWeight * r2 * (e2_globalBestIndex - e2_currentIndex));
            
            if (particle->velocity.e1_velocity[j] < 1 - this->max) {
                particle->velocity.e1_velocity[j] = 1 - this->max;
            } else if (particle->velocity.e1_velocity[j] > this->max) {
                particle->velocity.e1_velocity[j] = this->max;
            }
            
            if (particle->velocity.e2_velocity[j] < 1 - this->max) {
                particle->velocity.e2_velocity[j] = 1 - this->max;
            } else if (particle->velocity.e2_velocity[j] > this->max) {
                particle->velocity.e2_velocity[j] = this->max;
            }
        }
        
        for (int j=0; j < particle->position.e1.size(); j++) {
            int e1_index = 0, e2_index;
            for (int k=0; k<this->data->edges.size(); k++) {
                if (particle->position.e1[j] == this->data->edges[k]) {
                    e1_index = k;
                    break;
                }
            }
            
            for (int k=0; k<this->data->edges.size(); k++) {
                if (particle->position.e2[j] == this->data->edges[k]) {
                    e2_index = k;
                    break;
                }
            }
            
            int newIndex = e1_index + particle->velocity.e1_velocity[j];
            if (newIndex > this->max) {
                newIndex = this->max;
            } else if (newIndex < this->min) {
                newIndex = this->min;
            }
            particle->position.e1[j] = this->data->edges[newIndex];
            newIndex = e2_index + particle->velocity.e2_velocity[j];
            if (newIndex > this->max) {
                newIndex = this->max;
            } else if (newIndex < this->min) {
                newIndex = this->min;
            }
            particle->position.e2[j] = this->data->edges[newIndex];
        }
        
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

void PSO::findSolution() {
    std::cout << "Best position: " << std::endl;
    for (int it=0; it<this->bestGlobalPosition.e1.size(); it++) {
        std::cout << this->bestGlobalPosition.e1[it]->source << "," << this->bestGlobalPosition.e1[it]->destination << " ; ";
    }
    
    std::cout << std::endl;
    
    for (int it=0; it<this->bestGlobalPosition.e2.size(); it++) {
        std::cout << this->bestGlobalPosition.e2[it]->source << "," << this->bestGlobalPosition.e2[it]->destination << " ; ";
    }
    
    std::cout << "Fitness = " << this->bestGlobalFitness << std::endl;
    
    for (int it=0; it<this->numberOfIterations; it++) {
        this->updateParticles();
        std::cout << "Best position: " << std::endl;
        for (int it=0; it<this->bestGlobalPosition.e1.size(); it++) {
            std::cout << this->bestGlobalPosition.e1[it]->source << "," << this->bestGlobalPosition.e1[it]->destination << " ; ";
        }
        
        std::cout << std::endl;
        
        for (int it=0; it<this->bestGlobalPosition.e2.size(); it++) {
            std::cout << this->bestGlobalPosition.e2[it]->source << "," << this->bestGlobalPosition.e2[it]->destination << " ; ";
        }
        
        std::cout << std::endl;
        
        std::cout << "Fitness = " << this->bestGlobalFitness << std::endl;
        
        if (this->bestGlobalFitness == this->data->edges.size()) {
            break;
        }
    }
}
