//
//  LGVertex.m
//  Lab1
//
//  Created by Mihai Costea on 5/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import "LGVertex.h"

@implementation LGVertex

- (NSMutableSet *)inEdges {
    if (!_inEdges) {
        _inEdges = [[NSMutableSet alloc] init];
    }
    
    return _inEdges;
}

- (NSMutableSet *)outEdges {
    if (!_outEdges) {
        _outEdges = [[NSMutableSet alloc] init];
    }
    
    return _outEdges;
}

@end
