//
//  main.cpp
//  Lab3
//
//  Created by Mihai Costea on 24/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include <iostream>

#include "KnowledgeBase.h"
#include "InferenceEngine.h"

int main(int argc, const char * argv[])
{

    KnowledgeBase *knowledgeBase = new KnowledgeBase();
    InferenceEngine *inferenceEngine = new InferenceEngine(knowledgeBase);
    
    // forward chaining
    int sprinklerTime = inferenceEngine->forwardChaining();
    if (sprinklerTime != -1) {
        std::cout << "Sprinkler will be on for " << sprinklerTime << " minutes" << std::endl;
    } else {
        std::cout << "Rules are conflicting" << std::endl;
    }
    
    delete inferenceEngine;
    
    inferenceEngine = new InferenceEngine(knowledgeBase);
    // backward chaining
    std::cout << inferenceEngine->backwardChaining() << std::endl;
    
    delete knowledgeBase;
    delete inferenceEngine;
    
    return 0;
}

