//
//  LGNode.m
//  Lab2
//
//  Created by Mihai Costea on 13/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import "LGNode.h"

@implementation LGNode

+ (LGNode *)nodeWithVertex:(LGVertex *)vertex {
    LGNode *node = [[LGNode alloc] init];
    node.vertex = vertex;
    
    return node;
}

@end
