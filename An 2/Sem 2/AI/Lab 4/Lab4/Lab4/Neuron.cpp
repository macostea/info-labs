//
//  Neuron.cpp
//  Lab4
//
//  Created by Mihai Costea on 26/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Neuron.h"

#define MAX_W 0.01
#define MIN_W -0.01

Neuron::Neuron(int noInputs) {
    this->noInputs = noInputs;
    
    for (int it=0; it<noInputs; it++) {
        this->weights.push_back(MIN_W + arc4random()/(double(RAND_MAX)+1) * (MAX_W - MIN_W));
    }
    this->output = 0.0;
    this->error = 0.0;
}

int Neuron::getNoInputs() {
    return this->noInputs;
}

double Neuron::getError() {
    return this->error;
}

void Neuron::setError(double error) {
    this->error = error;
}

double Neuron::getOutput() {
    return this->output;
}

void Neuron::setOutput(double output) {
    this->output = output;
}

double Neuron::getWeight(int index) {
    return this->weights[index];
}

void Neuron::setWeight(int index, double value) {
    this->weights[index] = value;
}

void Neuron::activate(std::vector<double> *info) {
    double net = 0.0;
    for (int it=0; it<this->noInputs; it++) {
        net+=(*info)[it]*this->weights[it];
    }
    this->output = net;
}