//
//  CSOrder.m
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSOrder.h"

@implementation CSOrder

+ (CSOrder *)objectFromDictionary:(NSDictionary *)dictionary {
    CSOrder *object = [[self alloc] init];
    
    object.orderId = [dictionary objectForKey:@"id"];
    object.product = [CSProduct objectFromDictionary:[dictionary objectForKey:@"product"]];
    object.agent = [CSAgent objectFromDictionary:[dictionary objectForKey:@"agent"]];
    object.client = [CSClient objectFromDictionary:[dictionary objectForKey:@"client"]];
    object.quantity = [dictionary objectForKey:@"quantity"];
    object.status = [dictionary objectForKey:@"status"];
    
    return object;
}

- (NSDictionary *)dictionaryFromObject {
    NSMutableDictionary *dictionary = [NSMutableDictionary dictionary];
    
    [dictionary setObject:self.orderId forKey:@"id"];
    [dictionary setObject:[self.product dictionaryFromObject] forKey:@"product"];
    [dictionary setObject:[self.agent dictionaryFromObject] forKey:@"agent"];
    [dictionary setObject:[self.client dictionaryFromObject] forKey:@"client"];
    [dictionary setObject:self.quantity forKey:@"quantity"];
    [dictionary setObject:self.status forKey:@"status"];
    
    return dictionary;
}

#pragma mark - NSCopying

- (id)copyWithZone:(NSZone *)zone {
    CSOrder *copy = [[[self class] alloc] init];
    
    if (copy) {
        copy.orderId = [self.orderId copyWithZone:zone];
        copy.product = [self.product copyWithZone:zone];
        copy.agent = [self.agent copyWithZone:zone];
        copy.client = [self.client copyWithZone:zone];
        copy.quantity = [self.quantity copyWithZone:zone];
        copy.status = [self.status copyWithZone:zone];
    }
    
    return copy;
}

@end
