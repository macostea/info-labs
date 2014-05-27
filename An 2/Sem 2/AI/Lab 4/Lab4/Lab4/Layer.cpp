//
//  Layer.cpp
//  Lab4
//
//  Created by Mihai Costea on 26/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Layer.h"

Layer::Layer(int noNeurons, int noInputs) {
    this->noNeurons = noNeurons;
    for (int it=0; it<noNeurons; it++) {
        this->neurons.push_back(new Neuron(noInputs));
    }
}

std::vector<Neuron *> Layer::getNeurons() {
    return this->neurons;
}