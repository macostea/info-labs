//
//  Network.h
//  Lab4
//
//  Created by Mihai Costea on 26/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab4__Network__
#define __Lab4__Network__

#include <iostream>
#include <vector>

#include "Layer.h"

class Network {
    int noInputs;
    int noOutputs;
    int noHiddenLayers;
    std::vector<Layer *> layers;
    
public:
    Network(int noInputs, int noOutputs, int noHiddenLayers, int noNeuronsPerHiddenLayer);
    void activate(std::vector<double> *inputs);
    void backpropagate(std::vector<double> *errors);
    double errorComputationRegression(std::vector<double> *targets, std::vector<double> *errors);
    bool checkGlobalError(std::vector<double> errors);
    void learn(std::vector<std::vector<double> *> *input, std::vector<std::vector<double> *> *output);
    void test(std::vector<std::vector<double> *> *input, std::vector<std::vector<double> *> *output);
};

#endif /* defined(__Lab4__Network__) */
