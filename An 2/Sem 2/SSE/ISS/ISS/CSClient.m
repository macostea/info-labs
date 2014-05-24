//
//  CSClient.m
//  ISS
//
//  Created by Mihai Costea on 22/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSClient.h"

@implementation CSClient

+ (CSClient *)objectFromDictionary:(NSDictionary *)dictionary {
    CSClient *object = [[self alloc] init];
    
    object.clientId = [dictionary objectForKey:@"id"];
    object.name = [dictionary objectForKey:@"name"];
    object.address = [dictionary objectForKey:@"address"];
    
    return object;
}

- (NSDictionary *)dictionaryFromObject {
    NSMutableDictionary *dictionary = [NSMutableDictionary dictionary];
    
    [dictionary setObject:self.clientId forKey:@"id"];
    [dictionary setObject:self.name forKey:@"name"];
    [dictionary setObject:self.address forKey:@"address"];
    
    return dictionary;
}

#pragma mark - NSCopying

- (id)copyWithZone:(NSZone *)zone {
    CSClient *copy = [[[self class] alloc] init];
    
    if (copy) {
        copy.clientId = [self.clientId copyWithZone:zone];
        copy.name = [self.name copyWithZone:zone];
        copy.address = [self.address copyWithZone:zone];
    }
    
    return copy;
}
@end
