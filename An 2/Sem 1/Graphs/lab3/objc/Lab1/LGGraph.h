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

/*
 * Constructor
 */
- (LGGraph *)initWithVertices:(NSMutableDictionary *)vertices;

/*
 * Returns the number of vertices in the graph
 */
- (NSInteger)numberOfVertices;

/*
 * Returns the edge between 2 given vertices
 */
- (LGEdge *)edgeBetweenSource:(NSInteger)source destination:(NSInteger)destination;

/*
 * Returns the degree of a given vertex in a given direction
 */
- (NSInteger)degreeOfVertex:(NSInteger)vertex direction:(LGEdgeDirection)direction;

/*
 * Returns the shortest path between two given vertices using breadth first search
 */
- (NSArray *)pathBetweenFirstVertex:(NSInteger)firstVertex secondVertex:(NSInteger)secondVertex;

/*
 * Returns the lowest cost path between two given vertices using the Bellman Ford Algorithm
 */
- (NSArray *)lowestCostPathBetweenFirstVertex:(NSInteger)firstVertex secondVertex:(NSInteger)secondVertex;

/*
 * Returns an LGVertex from the supplied data
 */
- (LGVertex *)vertexWithData:(NSInteger)data;

@end
