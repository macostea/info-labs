//
//  Evolutive.h
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __lab2__Evolutive__
#define __lab2__Evolutive__

#include <iostream>
#include <list>
#include "SearchMethod.h"

#include "Graph.h"
#include "Individual.h"

class Evolutive : public SearchMethod {
private:
    void initPopulation();
    void calculateFitness();
    void sortByFitness();
    void mate(std::vector<Individual *> &buffer);
    
public:
    int populationSize;
    int numberOfGenerations;
    int chromosomeSize;
    double mutationProbability;
    double crossoverProbability;
    
    Graph *data;
    std::vector<Individual *> population;
    
    Evolutive(int chromosomeSize, double mutationProbability, double crossoverProbability, int numberOfGenerations, int populationSize);
    
    virtual SearchResult findSolution(Graph *graph);
    
    virtual ~Evolutive();
};

#endif /* defined(__lab2__Evolutive__) */
