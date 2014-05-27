//
//  Neuron.h
//  Lab4
//
//  Created by Mihai Costea on 26/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab4__Neuron__
#define __Lab4__Neuron__

#include <iostream>
#include <vector>

class Neuron {
    int noInputs;
    std::vector<double> weights;
    double output;
    double error;
    
public:
    Neuron(int noInputs);
    int getNoInputs();
    double getOutput();
    void setOutput(double output);
    double getError();
    void setError(double error);
    double getWeight(int index);
    void setWeight(int index, double value);
    
    void activate(std::vector<double> *info);
};

#endif /* defined(__Lab4__Neuron__) */
