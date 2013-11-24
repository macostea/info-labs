//
//  NSMutableArray+Queue.m
//  Lab1
//
//  Created by Mihai Costea on 13/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import "NSMutableArray+Queue.h"

@implementation NSMutableArray (Queue)

- (id)dequeue {
    id headObj = self[0];
    if (headObj != nil) {
        [self removeObjectAtIndex:0];
    }
    return headObj;
}

- (void)enqueue:(id)obj {
    [self addObject:obj];
}

@end
