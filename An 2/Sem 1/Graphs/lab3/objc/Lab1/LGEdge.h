//
//  LGEdge.h
//  Lab1
//
//  Created by Mihai Costea on 5/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LGVertex.h"

@interface LGEdge : NSObject

@property (nonatomic, weak) LGVertex        *source;
@property (nonatomic, strong) LGVertex      *destination;
@property (nonatomic) NSInteger             cost;

- (LGEdge *)initWithDestination:(LGVertex *)destination cost:(NSInteger)cost;

@end
