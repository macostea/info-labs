//
//  Evolutive.cpp
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#define ARC4RANDOM_MAX      0x100000000

#include "Evolutive.h"
#include "Utils.h"

Evolutive::Evolutive(int chromosomeSize, double mutationProbability, double crossoverProbability, int numberOfGenerations, int populationSize) {
    this->chromosomeSize = chromosomeSize;
    this->mutationProbability = mutationProbability;
    this->numberOfGenerations = numberOfGenerations;
    this->crossoverProbability = crossoverProbability;
    this->populationSize = populationSize;
}

Evolutive::~Evolutive() {
}

void Evolutive::initPopulation() {
    for (int i=0; i<this->populationSize; i++) {
        Individual *individual = new Individual();
        individual->fitness = 0;
        size_t e1Size = this->chromosomeSize;
        size_t e2Size = this->data->edges.size() - e1Size;
        
        for (int i=0; i<e1Size; i++) {
            bool found = true;
            uint32_t it = 0;
            while (found) {
                found = false;
                it = arc4random_uniform((uint32_t)this->data->edges.size());
                for (int j=0; j<individual->e1.size(); j++) {
                    if (individual->e1.find(this->data->edges[it]) != individual->e1.end()) {
                        found = true;
                        break;
                    }
                }
            }
            
            individual->e1.insert(this->data->edges[it]);
        }
        
        for (int i=0; i<e2Size; i++) {
            bool found = true;
            uint32_t it = 0;
            while (found) {
                found = false;
                it = arc4random_uniform((uint32_t)this->data->edges.size());
                for (int j=0; j<individual->e1.size(); j++) {
                    if (individual->e1.find(this->data->edges[it]) != individual->e1.end()) {
                        found = true;
                        break;
                    }
                }
                
                for (int j=0; j<individual->e2.size(); j++) {
                    if (individual->e2.find(this->data->edges[it]) != individual->e2.end()) {
                        found = true;
                        break;
                    }
                }
            }
            
            individual->e2.insert(this->data->edges[it]);
        }
        for (std::set<Edge *>::iterator it=individual->e1.begin(); it != individual->e1.end(); ++it) {
            Edge *edge = *it;
            std::cout << edge->source << "," << edge->destination << "; ";
        }
        
        std::cout << std::endl;
        
        for (std::set<Edge *>::iterator it=individual->e2.begin(); it != individual->e2.end(); ++it) {
            Edge *edge = *it;
            std::cout << edge->source << "," << edge->destination << "; ";
        }
        
        std::cout << std::endl;
        
        this->population.push_back(individual);
    }
}

void Evolutive::calculateFitness() {
    for (std::vector<Individual *>::iterator it=this->population.begin(); it != this->population.end(); ++it) {
        Individual *individual = *it;
        size_t e1_fitness = numberOfEdgesOutsideTriangle(individual->e1);
        size_t e2_fitness = numberOfEdgesOutsideTriangle(individual->e2);
        
        individual->fitness = (uint32_t)e1_fitness + (uint32_t)e2_fitness;
    }
}

bool fitness_sort(Individual *i1, Individual *i2) {
    return i1->fitness > i2->fitness;
}

void Evolutive::sortByFitness() {
    std::sort(this->population.begin(), this->population.end(), fitness_sort);
}

void elitism(std::vector<Individual *> &population, std::vector<Individual *> &buffer, int elitismSize) {
    for (int it=0; it<elitismSize; it++) {
        buffer.push_back(population[it]);
    }
}

void mutate(Individual *individual) {
    size_t it = arc4random_uniform((uint32_t)individual->e1.size());
    size_t jt = arc4random_uniform((uint32_t)individual->e2.size());
    
    Edge *temp;
    int i = 0;
    for (std::set<Edge *>::iterator iterator = individual->e1.begin(); iterator != individual->e1.end(); ++iterator) {
        if (i == it) {
            temp = *iterator;
            individual->e1.erase(iterator);
            break;
        }
        
        i++;
    }
    
    i = 0;
    
    for (std::set<Edge *>::iterator iterator = individual->e2.begin(); iterator != individual->e2.end(); ++iterator) {
        if (i == jt) {
            individual->e1.insert(*iterator);
            individual->e2.erase(iterator);
            individual->e2.insert(temp);
            break;
        }
        
        i++;
    }
}

inline void swap(std::vector<Individual *> *population, std::vector<Individual *> *buffer) {
    std::vector<Individual *> *temp = population;
    population = buffer;
    buffer = temp;
}

void Evolutive::mate(std::vector<Individual *> &buffer) {
    int elitismSize = this->populationSize * (1 - this->mutationProbability);
    
    elitism(this->population, buffer, elitismSize);
    
    for (int i=elitismSize; i<this->populationSize; i++) {
        int i1 = arc4random_uniform(this->populationSize / 2);
        int i2 = arc4random_uniform(this->populationSize / 2);
        Individual *individual;
        if ((double)arc4random() / ARC4RANDOM_MAX < this->crossoverProbability) {
            individual = new Individual();
            individual->e1 = this->population[i1]->e1;
            individual->e2 = this->population[i2]->e2;
        } else {
            individual = this->population[i];
        }
        
        buffer.push_back(individual);
        
        if ((double)arc4random() / ARC4RANDOM_MAX < this->mutationProbability) {
            mutate(individual);
        }
    }
    
    swap(this->population, buffer);
}

inline void print_best(std::vector<Individual *> &population) {
    std::cout << "Best: " << std::endl << "e1: ";
    for (std::set<Edge *>::iterator it=population[0]->e1.begin(); it != population[0]->e1.end(); ++it) {
        Edge *edge = *it;
        std::cout << edge->source << "," << edge->destination << "; ";
    }
    
    std::cout << std::endl << "e2: ";
    
    for (std::set<Edge *>::iterator it=population[0]->e2.begin(); it != population[0]->e2.end(); ++it) {
        Edge *edge = *it;
        std::cout << edge->source << "," << edge->destination << "; ";
    }
    
    std::cout << "Fitness = " << population[0]->fitness;
    
    std::cout << std::endl;
}

SearchResult Evolutive::findSolution(Graph *graph) {
    this->data = graph;
    this->initPopulation();
    std::vector<Individual *> buffer;
    
    for (int it=0; it<this->numberOfGenerations; it++) {
        this->calculateFitness();
        this->sortByFitness();
        
        print_best(this->population);
        
        if (this->population[0]->fitness == this->data->edges.size()) {
            break;
        }
        
        this->mate(buffer);
    }
    
    SearchResult result;
    result.e1 = this->population[0]->e1;
    result.e2 = this->population[0]->e2;
    
    return result;
}

