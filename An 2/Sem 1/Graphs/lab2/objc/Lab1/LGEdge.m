//
//  LGEdge.m
//  Lab1
//
//  Created by Mihai Costea on 5/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import "LGEdge.h"

@implementation LGEdge

- (LGEdge *)initWithDestination:(LGVertex *)destination cost:(NSInteger)cost {
    self = [super init];
    if (self) {
        self.destination = destination;
        self.cost = cost;
    }
    
    return self;
}

@end
