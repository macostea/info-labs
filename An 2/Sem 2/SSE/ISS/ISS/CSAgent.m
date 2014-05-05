//
//  CSAgent.m
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSAgent.h"

@implementation CSAgent

+ (CSAgent *)objectFromDictionary:(NSDictionary *)dictionary {
    CSAgent *object = [[self alloc] init];
    
    object.agentId = [dictionary objectForKey:@"id"];
    object.name = [dictionary objectForKey:@"name"];
    
    return object;
}

- (NSDictionary *)dictionaryFromObject {
    NSMutableDictionary *dictionary = [NSMutableDictionary dictionary];
    
    [dictionary setObject:self.agentId forKey:@"id"];
    [dictionary setObject:self.name forKey:@"name"];
    
    return dictionary;
}

#pragma mark - NSCopying

- (id)copyWithZone:(NSZone *)zone {
    CSAgent *copy = [[[self class] alloc] init];
    
    if (copy) {
        copy.agentId = [self.agentId copyWithZone:zone];
        copy.name = [self.name copyWithZone:zone];
    }
    
    return copy;
}

@end
