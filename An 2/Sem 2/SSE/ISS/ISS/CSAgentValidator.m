//
//  CSAgentValidator.m
//  ISS
//
//  Created by Mihai Costea on 22/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSAgentValidator.h"

@implementation CSAgentValidator

- (BOOL)validateAgent:(CSAgent *)agent {
    if ([agent.name length] == 0) {
        return NO;
    }
    
    return YES;
}

@end
