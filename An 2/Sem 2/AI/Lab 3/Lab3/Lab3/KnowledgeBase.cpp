//
//  KnowledgeBase.cpp
//  Lab3
//
//  Created by Mihai Costea on 24/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "KnowledgeBase.h"


int KnowledgeBase::checkTemp(int temp) {
    if (temp > 30) {
        return 30;
    }
    
    return -1;
}

int KnowledgeBase::checkSky(SkyType skyType) {
    if (skyType == SkyTypeClear) {
        return 49;
    } else {
        return 61;
    }
}

int KnowledgeBase::checkTempAndHumidity(int temp, int humidity) {
    if (temp > 25 && humidity < 50) {
        return 20;
    } if (humidity < 50 && 15 < temp && temp < 25) {
        return 10;
    } if (15 < temp && temp < 25 && humidity > 50) {
        return 3;
    }
    
    return -1;
}

int KnowledgeBase::checkSeason(SeasonType seasonType) {
    if (seasonType == SeasonTypeSummer) {
        return 21;
    } else {
        return 4;
    }
}

std::map<std::string, int> KnowledgeBase::checkSprinklerTime(int time) {
    std::map<std::string, int> map;
    if (time == 10) {
        map.insert(std::pair<std::string, int>("humidity", 49));
        map.insert(std::pair<std::string, int>("temp", 21));
    } else if (time == 20) {
        map.insert(std::pair<std::string, int>("humidity", 49));
        map.insert(std::pair<std::string, int>("temp", 24));
    } else {
        map.insert(std::pair<std::string, int>("humidity", 51));
        map.insert(std::pair<std::string, int>("temp", 20));
    }
    
    return map;
}

SeasonType KnowledgeBase::getSeasonForTemp(int temp) {
    if (temp > 20) {
        return SeasonTypeSummer;
    } else {
        return SeasonTypeWinter;
    }
}

SkyType KnowledgeBase::getSkyTypeForHumidity(int humidity) {
    if (humidity > 60) {
        return SkyTypeCloudy;
    } else {
        return SkyTypeClear;
    }
}