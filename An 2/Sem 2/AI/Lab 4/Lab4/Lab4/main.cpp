//
//  main.cpp
//  Lab4
//
//  Created by Mihai Costea on 26/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include <iostream>
#include <vector>
#include <fstream>

#include "Network.h"

void readTrainData(int noExamples, int noFeatures, int noOutputs, std::vector<std::vector<double> *> *input, std::vector<std::vector<double> *> *output, std::string fileName) {
    std::ifstream file;
    file.open(fileName);
    
    if (file.is_open()) {
        for (int it=0; it<noExamples; it++) {
            std::vector<double> *features = new std::vector<double>();
            for (int jt=0; jt<noFeatures; jt++) {
                double data;
                file >> data;
                features->push_back(data);
            }
            input->push_back(features);
            int outputData;
            file >> outputData;
            std::vector<double> *outputVector = new std::vector<double>();
            if (outputData == 1) {
                outputVector->push_back(1);
                outputVector->push_back(0);
                outputVector->push_back(0);
            } else if (outputData == 2) {
                outputVector->push_back(0);
                outputVector->push_back(1);
                outputVector->push_back(0);
            } else {
                outputVector->push_back(0);
                outputVector->push_back(0);
                outputVector->push_back(1);
            }
            output->push_back(outputVector);
        }
    }
    file.close();
}

void readTestData(int noTests, int noFeatures, std::vector<std::vector<double> *> *testData, std::string fileName) {
    std::ifstream file;
    file.open(fileName);
    
    if (file.is_open()) {
        for (int it=0; it<noTests; it++) {
            std::vector<double> *features = new std::vector<double>();
            for (int jt=0; jt<noFeatures; jt++) {
                double data;
                file >> data;
                features->push_back(data);
            }
            testData->push_back(features);
        }
    }
    file.close();
}

int main(int argc, const char * argv[])
{
    std::vector<std::vector<double> *> *input = new std::vector<std::vector<double> *>();
    std::vector<std::vector<double> *> *output = new std::vector<std::vector<double> *>();
    readTrainData(2123, 21, 3, input, output, "data.train");
    std::vector<std::vector<double> *> *testData = new std::vector<std::vector<double> *>();
    std::vector<std::vector<double> *> *testOutput = new std::vector<std::vector<double> *>();
    readTrainData(3, 21, 3, testData, testOutput, "data.test");
    Network net = Network(21, 3, 15, 3);
    net.learn(input, output);
    net.test(testData, testOutput);
    
    return 0;
}

