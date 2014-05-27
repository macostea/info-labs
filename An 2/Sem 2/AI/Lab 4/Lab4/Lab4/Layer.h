//
//  Layer.h
//  Lab4
//
//  Created by Mihai Costea on 26/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab4__Layer__
#define __Lab4__Layer__

#include <iostream>
#include <vector>

#include "Neuron.h"

class Layer {
    int noNeurons;
    std::vector<Neuron *> neurons;
public:
    Layer(int noNeurons, int noInputs);
    std::vector<Neuron *> getNeurons();
};

#endif /* defined(__Lab4__Layer__) */
