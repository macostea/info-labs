//
//  LGGraph.m
//  Lab1
//
//  Created by Mihai Costea on 5/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import "LGGraph.h"
#import "NSMutableArray+Queue.h"
#import "LGNode.h"

@interface LGGraph ()

@property (nonatomic, strong) NSSet *vertices;

@end

@implementation LGGraph

#pragma mark - Public methods

- (LGGraph *)initWithVertices:(NSSet *)vertices {
    self = [super init];
    
    if (self) {
        self.vertices = vertices;
    }
    
    return self;
}

- (NSInteger)numberOfVertices {
    return [self.vertices count];
}

- (LGEdge *)edgeBetweenSource:(NSInteger)source destination:(NSInteger)destination {
    LGVertex *sourceVertex = [self vertexWithData:source];
    
    if (!sourceVertex) {
        return nil;
    }
    
    for (LGEdge *edge in sourceVertex.outEdges) {
        if (edge.destination.data == destination) {
            return edge;
        }
    }
    return nil;
}

- (NSInteger)degreeOfVertex:(NSInteger)vertex direction:(LGEdgeDirection)direction {
    LGVertex *vertexToCompute = [self vertexWithData:vertex];
    
    if (!vertexToCompute) {
        return -1;
    }
    
    if (direction == EdgeDirectionInbound) {
        return [vertexToCompute.inEdges count];
    } else {
        return [vertexToCompute.outEdges count];
    }
    
}

- (NSArray *)pathBetweenFirstVertex:(NSInteger)firstVertex secondVertex:(NSInteger)secondVertex {
    NSMutableArray *queue = [[NSMutableArray alloc] init];
    NSMutableSet *visited = [[NSMutableSet alloc] init];
    
    LGVertex *startVertex = [self vertexWithData:firstVertex];
    LGVertex *endVertex = [self vertexWithData:secondVertex];
    
    [queue enqueue:[LGNode nodeWithVertex:startVertex]];
    [visited addObject:startVertex];
    
    while (queue.count != 0) {
        LGNode *element = [queue dequeue];
        if (element.vertex == endVertex) {
            NSMutableArray *result = [[NSMutableArray alloc] init];
            while (element != nil) {
                [result addObject:element.vertex];
                element = element.previous;
            }
            return [[result reverseObjectEnumerator] allObjects];
        }
        
        for (LGEdge *edge in element.vertex.outEdges) {
            LGVertex *destination = edge.destination;
            
            if (![visited containsObject:destination]) {
                LGNode *destinationNode = [LGNode nodeWithVertex:destination];
                destinationNode.previous = element;
                [visited addObject:destination];
                [queue enqueue:destinationNode];
            }
        }
    }
    return nil;
}

#pragma mark - Protected methods

- (LGVertex *)vertexWithData:(NSInteger)data {
    for (LGVertex *vertex in self.vertices) {
        if (vertex.data == data) {
            return vertex;
        }
    }
    return nil;
}

@end
