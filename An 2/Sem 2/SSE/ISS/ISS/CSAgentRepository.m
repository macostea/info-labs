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

@property (strong) NSMutableArray *agents;

@end

@implementation CSAgentRepository

- (instancetype)init
{
    self = [super init];
    if (self) {
        self.agents = [NSMutableArray array];
    }
    return self;
}

- (void)addElement:(id)element {
    [self.agents addObject:element];
}

- (void)updateElement:(id)oldElement newElement:(id)newElement {
    [self.agents replaceObjectAtIndex:[self.agents indexOfObject:oldElement] withObject:newElement];
}

- (void)removeElement:(id)element {
    [self.agents removeObject:element];
}

- (void)getAllElementsWithCompletionBlock:(void (^)(BOOL, NSArray *))completionBlock {
    CSDBConnection *connection = [CSDBConnection connection];
    [connection connectWithCompletionBlock:^(BOOL success) {
        [self getAgentsFromDB];
        completionBlock(YES, [self.agents copy]);
    }];
}

#pragma mark - Private methods

- (void)getAgentsFromDB {
    CSDBConnection *connection = [CSDBConnection connection];
    NSArray *result = [connection rowsForTable:@"Agents"];
    
    for (NSDictionary *agentDict in result) {
        [self.agents addObject:[CSAgent objectFromDictionary:agentDict]];
    }
}

@end
