//
//  Network.cpp
//  Lab4
//
//  Created by Mihai Costea on 26/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Network.h"

#include <cmath>

#define LEARN_RATE 0.5
#define ERR 0.1

Network::Network(int noInputs, int noOutputs, int noHiddenLayers, int noNeuronsPerHiddenLayer) {
    this->noInputs = noInputs;
    this->noOutputs = noOutputs;
    this->noHiddenLayers = noHiddenLayers;
    
    this->layers.push_back(new Layer(noInputs, 0));
    this->layers.push_back(new Layer(noNeuronsPerHiddenLayer, noInputs));
    for (int it=1; it<noHiddenLayers; it++) {
        this->layers.push_back(new Layer(noNeuronsPerHiddenLayer, noNeuronsPerHiddenLayer));
    }
    this->layers.push_back(new Layer(noOutputs, noNeuronsPerHiddenLayer));
}

void Network::activate(std::vector<double> *inputs) {
    for (int it=0; it<this->layers[0]->getNeurons().size(); it++) {
        Neuron *neuron = this->layers[0]->getNeurons()[it];
        neuron->setOutput((*inputs)[it]);
    }
    
    for (int it=1; it<=this->noHiddenLayers+1; it++) {
        for (int jt=0; jt<this->layers[it]->getNeurons().size(); jt++) {
            Neuron *neuron = this->layers[it]->getNeurons()[jt];
            std::vector<double> *info = new std::vector<double>();
            for (int kt=0; kt<neuron->getNoInputs(); kt++) {
                info->push_back(this->layers[it - 1]->getNeurons()[kt]->getOutput());
            }
            neuron->activate(info);
            delete info;
        }
    }
}

void Network::backpropagate(std::vector<double> *errors) {
    for (int it=this->noHiddenLayers + 1; it>=1; it--) {
        for (int jt=0; jt<this->layers[it]->getNeurons().size(); jt++) {
            Neuron *neuron1 = this->layers[it]->getNeurons()[jt];
            if (it == this->noHiddenLayers + 1) {
                neuron1->setError((*errors)[jt]);
            } else {
                double errorSum = 0.0;
                for (int kt=0; kt<this->layers[it+1]->getNeurons().size(); kt++) {
                    Neuron *neuron2 = this->layers[it+1]->getNeurons()[kt];
                    errorSum += neuron2->getWeight(jt) * neuron2->getError();
                }
                neuron1->setError(errorSum);
            }
            
            for (int j=0; j<neuron1->getNoInputs(); j++) {
                double netWeight = neuron1->getWeight(j) + LEARN_RATE * neuron1->getError() * this->layers[it-1]->getNeurons()[j]->getOutput();
                neuron1->setWeight(j, netWeight);
            }
        }
    }
}

double Network::errorComputationRegression(std::vector<double> *targets, std::vector<double> *errors) {
    double globalError = 0.0;
    for (int it=0; it<this->layers[this->noHiddenLayers+1]->getNeurons().size(); it++) {
        errors->push_back((*targets)[it] - this->layers[this->noHiddenLayers+1]->getNeurons()[it]->getOutput());
        globalError += (*errors)[it] * (*errors)[it];
    }
    return globalError;
}

bool Network::checkGlobalError(std::vector<double> errors) {
    double error = 0.0;
    for (int it=0; it<errors.size(); it++) {
        error+= errors[it];
    }
    if (fabs(error - ERR) < 1.0E-8) {
        return true;
    }
    return false;
}

void Network::learn(std::vector<std::vector<double> *> *input, std::vector<std::vector<double> *> *output) {
    bool stop = false;
    while (!stop) {
        std::vector<double> globalError;
        for (int it=0; it<input->size(); it++) {
            this->activate((*input)[it]);
            std::vector<double> *errors = new std::vector<double>();
            globalError.push_back(this->errorComputationRegression((*output)[it], errors));
            this->backpropagate(errors);
            delete errors;
        }
        stop = this->checkGlobalError(globalError);
    }
}

void Network::test(std::vector<std::vector<double> *> *input, std::vector<std::vector<double> *> *output) {
    std::vector<double> globalError;
    for (int it=0; it<input->size(); it++) {
        this->activate((*input)[it]);
        std::vector<double> *errors = new std::vector<double>();
        globalError.push_back(this->errorComputationRegression((*output)[it], errors));
    }
}