//
//  CSProductValidator.m
//  ISS
//
//  Created by Mihai Costea on 22/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSProductValidator.h"

@implementation CSProductValidator

- (BOOL)validateProduct:(CSProduct *)product {
    if ([product.name length] == 0) {
        return NO;
    }
    if ([product.price floatValue] < 0) {
        return NO;
    }
    
    if ([product.quantity integerValue] < 0) {
        return NO;
    }
    
    return YES;
}

@end
