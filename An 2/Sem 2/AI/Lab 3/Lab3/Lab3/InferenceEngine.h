//
//  InferenceEngine.h
//  Lab3
//
//  Created by Mihai Costea on 24/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab3__InferenceEngine__
#define __Lab3__InferenceEngine__

#include <iostream>

#include "KnowledgeBase.h"

class InferenceEngine {
    KnowledgeBase *knowledgeBase;
    
    SkyType skyType;
    SeasonType season;
    int temp;
    int humidity;
    bool windy;
    int timeSinceRain;
    int clearSkyForecast;
    
public:
    InferenceEngine(KnowledgeBase *knowledgeBase);
    
    int forwardChaining();
    std::string backwardChaining();
};

#endif /* defined(__Lab3__InferenceEngine__) */
