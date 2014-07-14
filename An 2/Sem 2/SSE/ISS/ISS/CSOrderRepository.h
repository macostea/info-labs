//
//  CSOrderRepository.h
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSRepository.h"

@class CSAgent;

@interface CSOrderRepository : CSRepository

- (void)getAllElementsForAgent:(CSAgent *)agent completionBlock:(void (^)(BOOL success, NSArray *results))completionBlock;

@end
