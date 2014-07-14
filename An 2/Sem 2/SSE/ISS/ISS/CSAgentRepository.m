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
    NSArray *keys = [[self.agents allKeys] sortedArrayUsingSelector:@selector(compare:)];
    NSNumber *lastId = [keys lastObject];
    element.agentId = @([lastId intValue] + 1);
    [self.agents setObject:element forKey:[NSString stringWithFormat:@"%@", element.agentId]];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    NSString *values = [NSString stringWithFormat:@"%@, '%@'", element.agentId, element.name];
    [connection addRow:values table:@"Agents"];
}

- (void)updateElement:(CSAgent *)oldElement newElement:(CSAgent *)newElement {
    [self.agents setObject:newElement forKey:[NSString stringWithFormat:@"%@", oldElement.agentId]];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    
    NSString *value = [NSString stringWithFormat:@"name='%@'", newElement.name];
    [connection updateRow:oldElement.agentId value:value table:@"Agents"];
}

- (void)removeElement:(CSAgent *)element {
    [self.agents removeObjectForKey:element.agentId];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    [connection removeRow:element.agentId table:@"Agents"];
}

- (void)getAllElementsWithCompletionBlock:(void (^)(BOOL, NSArray *))completionBlock {
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    [connection connectWithCompletionBlock:^(BOOL success) {
        [self getAgentsFromDB];
        
        NSMutableArray *array = [NSMutableArray array];
        
        [self.agents enumerateKeysAndObjectsUsingBlock:^(id key, CSAgent *obj, BOOL *stop) {
            [array addObject:[obj copy]];
        }];
        
        completionBlock(YES, array);
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
