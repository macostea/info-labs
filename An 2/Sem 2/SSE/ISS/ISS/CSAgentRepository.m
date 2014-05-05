//
//  CSAgentRepository.m
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSAgentRepository.h"
#import "CSAgent.h"

@interface CSAgentRepository ()

@property (strong) NSMutableDictionary *agents;

@end

@implementation CSAgentRepository

- (instancetype)init
{
    self = [super init];
    if (self) {
        self.agents = [NSMutableDictionary dictionary];
    }
    return self;
}

- (void)addElement:(CSAgent *)element {
    [self.agents setObject:element forKey:element.agentId];
}

- (void)updateElement:(CSAgent *)oldElement newElement:(CSAgent *)newElement {
    [self.agents setObject:newElement forKey:oldElement.agentId];
}

- (void)removeElement:(CSAgent *)element {
    [self.agents removeObjectForKey:element.agentId];
}

- (void)getAllElementsWithCompletionBlock:(void (^)(BOOL, NSArray *))completionBlock {
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    [connection connectWithCompletionBlock:^(BOOL success) {
        [self getAgentsFromDB];
        
        NSMutableArray *array = [NSMutableArray array];
        
        [self.agents enumerateKeysAndObjectsUsingBlock:^(id key, CSAgent *obj, BOOL *stop) {
            [array addObject:[obj copy]];
            completionBlock(YES, array);
        }];
    }];
}

#pragma mark - Private methods

- (void)getAgentsFromDB {
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    NSArray *result = [connection rowsForTable:@"Agents"];
    
    for (NSDictionary *agentDict in result) {
        CSAgent *agent = [CSAgent objectFromDictionary:agentDict];
        [self.agents setObject:agent forKey:agent.agentId];
    }
}

@end
