//
//  LGGraph.h
//  Lab1
//
//  Created by Mihai Costea on 5/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LGVertex.h"
#import "LGEdge.h"

typedef enum {
    EdgeDirectionInbound = 0,
    EdgeDirectionOutbound = 1
} LGEdgeDirection;

@interface LGGraph : NSObject

- (LGGraph *)initWithVertices:(NSSet *)vertices;

- (NSInteger)numberOfVertices;
- (LGEdge *)edgeBetweenSource:(NSInteger)source destination:(NSInteger)destination;
- (NSInteger)degreeOfVertex:(NSInteger)vertex direction:(LGEdgeDirection)direction;
- (NSArray *)pathBetweenFirstVertex:(NSInteger)firstVertex secondVertex:(NSInteger)secondVertex;

- (LGVertex *)vertexWithData:(NSInteger)data; //protected

@end
