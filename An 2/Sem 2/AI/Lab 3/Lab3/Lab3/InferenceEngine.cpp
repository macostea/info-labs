//
//  InferenceEngine.cpp
//  Lab3
//
//  Created by Mihai Costea on 24/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "InferenceEngine.h"

InferenceEngine::InferenceEngine(KnowledgeBase *knowledgeBase) {
    this->knowledgeBase = knowledgeBase;
}

int InferenceEngine::forwardChaining() {
    // summer and windy
    this->windy = true;
    this->season = SeasonTypeSummer;
    
    this->temp = this->knowledgeBase->checkSeason(this->season);

    // temp = 23 and time since rain = 10
    int temp = 23;
    if ((this->season == SeasonTypeSummer && this->temp > temp) || (this->season == SeasonTypeWinter && this->temp < temp)) {
        return -1;
    }
    this->temp = temp;
    this->timeSinceRain = 10;
    int sprinklerTime = this->knowledgeBase->checkTemp(this->temp);
    
    if (sprinklerTime != -1) {
        return sprinklerTime;
    }
    
    // skytype = clear for 3 days
    this->skyType = SkyTypeClear;
    this->clearSkyForecast = 10;
    
    this->humidity = this->knowledgeBase->checkSky(this->skyType);
    
    // final
    return this->knowledgeBase->checkTempAndHumidity(this->temp, this->humidity);
}

std::string InferenceEngine::backwardChaining() {
    std::map<std::string, int> map = this->knowledgeBase->checkSprinklerTime(10);
    this->humidity = map["humidity"];
    this->temp = map["temp"];
    
    this->skyType = this->knowledgeBase->getSkyTypeForHumidity(this->humidity);
    this->season = this->knowledgeBase->getSeasonForTemp(this->temp);
    
    if (this->season == SeasonTypeSummer && this->skyType == SkyTypeClear) {
        return "It's hot out and it hasn't rained in a while";
    }
    
    return "It's chilly out and it might rain";
}