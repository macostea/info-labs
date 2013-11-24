//
//  LGNode.h
//  Lab2
//
//  Created by Mihai Costea on 13/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LGVertex.h"

@interface LGNode : NSObject

@property (nonatomic, strong) LGVertex  *vertex;
@property (nonatomic, strong) LGNode    *previous;

+ (LGNode *)nodeWithVertex:(LGVertex *)vertex;

@end
