//
//  KnowledgeBase.h
//  Lab3
//
//  Created by Mihai Costea on 24/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#ifndef __Lab3__KnowledgeBase__
#define __Lab3__KnowledgeBase__

#include <iostream>

#include <map>

typedef enum {
    SkyTypeCloudy,
    SkyTypeClear
} SkyType;

typedef enum {
    SeasonTypeSummer,
    SeasonTypeWinter
} SeasonType;

class KnowledgeBase {
    
public:
    int checkTemp(int temp);
    int checkSky(SkyType skyType);
    int checkTempAndHumidity(int temp, int humidity);
    int checkSeason(SeasonType seasonType);
    
    std::map<std::string, int> checkSprinklerTime(int time);
    SeasonType getSeasonForTemp(int temp);
    SkyType getSkyTypeForHumidity(int humidity);
};

#endif /* defined(__Lab3__KnowledgeBase__) */
