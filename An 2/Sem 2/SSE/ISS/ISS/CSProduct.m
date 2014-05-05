//
//  CSProduct.m
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSProduct.h"

@implementation CSProduct

+ (CSProduct *)objectFromDictionary:(NSDictionary *)dictionary {
    CSProduct *object = [[self alloc] init];
    
    object.productId = [dictionary objectForKey:@"id"];
    object.name = [dictionary objectForKey:@"name"];
    object.price = [dictionary objectForKey:@"price"];
    object.quantity = [dictionary objectForKey:@"quantity"];
    
    return object;
}

- (NSDictionary *)dictionaryFromObject {
    NSMutableDictionary *dictionary = [NSMutableDictionary dictionary];
    
    [dictionary setObject:self.productId forKey:@"id"];
    [dictionary setObject:self.name forKey:@"name"];
    [dictionary setObject:self.price forKey:@"price"];
    [dictionary setObject:self.quantity forKey:@"quantity"];
    
    return dictionary;
}

#pragma mark - NSCopying

- (id)copyWithZone:(NSZone *)zone {
    CSProduct *copy = [[[self class] alloc] init];
    if (copy) {
        copy.productId = [self.productId copyWithZone:zone];
        copy.name = [self.name copyWithZone:zone];
        copy.price = [self.price copyWithZone:zone];
        copy.quantity = [self.quantity copyWithZone:zone];
    }
    
    return copy;
}

@end
