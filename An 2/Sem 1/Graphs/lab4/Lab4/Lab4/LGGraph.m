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

@property (nonatomic, strong) NSMutableDictionary *vertices;

@end

@implementation LGGraph

#pragma mark - Public methods

- (LGGraph *)initWithVertices:(NSMutableDictionary *)vertices {
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
    LGVertex *sourceVertex = [self.vertices objectForKey:@(source)];
    
    if (!sourceVertex) {
        return nil;
    }
    
    for (LGEdge *edge in sourceVertex.outEdges) {
        if (edge.destination.data == destination) {
            return edge;
        }
    }
    
    for (LGEdge *edge in sourceVertex.inEdges) {
        if (edge.destination.data == destination) {
            return edge;
        }
    }
    return nil;
}

- (NSInteger)degreeOfVertex:(NSInteger)vertex direction:(LGEdgeDirection)direction {
    LGVertex *vertexToCompute = [self.vertices objectForKey:@(vertex)];
    
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
    // Breadth first search
    NSMutableArray *queue = [[NSMutableArray alloc] init];
    NSMutableSet *visited = [[NSMutableSet alloc] init];
    
    LGVertex *startVertex = [self.vertices objectForKey:@(firstVertex)];
    LGVertex *endVertex = [self.vertices objectForKey:@(secondVertex)];
    
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

- (NSArray *)lowestCostPathBetweenFirstVertex:(NSInteger)firstVertex secondVertex:(NSInteger)secondVertex {
    LGVertex *startVertex = [self.vertices objectForKey:@(firstVertex)];
    
    NSMutableArray *distance = [[NSMutableArray alloc] initWithCapacity:[self numberOfVertices]];
    NSMutableArray *predecessor = [[NSMutableArray alloc] initWithCapacity:[self numberOfVertices]];

    // Init distance array with INT16_MAX and predecessor with null
    for (LGVertex *vertex in [self.vertices allValues]) {
        [distance addObject:@(INT16_MAX)];
        [predecessor addObject:[NSNull null]];
    }
    distance[startVertex.data] = @(0);
    
    NSMutableSet *allEdges = [self allEdges];
    
    // Iterate through all the edges |V|-1 times
    for (int it = 1; it<[[self.vertices allValues] count]; it++) {
        [allEdges enumerateObjectsUsingBlock:^(LGEdge *edge, BOOL *stop) {
            if ([distance[edge.source.data] integerValue] + edge.cost < [distance[edge.destination.data] integerValue]) {
                distance[edge.destination.data] = @([distance[edge.source.data] integerValue] + edge.cost);
                predecessor[edge.destination.data] = edge.source;
            }
        }];
    }
    
    // Check if there are any negative cost cycles
    __block BOOL negativeCost = NO;
    [allEdges enumerateObjectsUsingBlock:^(LGEdge *edge, BOOL *stop) {
        if ([distance[edge.source.data] integerValue] + edge.cost < [distance[edge.destination.data] integerValue]) {
            negativeCost = YES;
            *stop = YES;
        }
    }];
    
    if (negativeCost) {
        NSLog(@"Graph contains a negative-cost cycle!");
        return nil;
    }
    
    // Parse the predecessors and get the path to the vertex
    NSMutableArray *result = [[NSMutableArray alloc] init];
    [result addObject:[self vertexWithData:secondVertex]];
    LGVertex *currentVertex = predecessor[secondVertex];
    while (![currentVertex isKindOfClass:[NSNull class]]) {
        [result addObject:currentVertex];
        currentVertex = predecessor[currentVertex.data];
    }
    
    return [[result reverseObjectEnumerator] allObjects];
}

- (LGVertex *)vertexWithData:(NSInteger)data {
    return [self.vertices objectForKey:@(data)];
}

#pragma mark - Private methods

- (NSMutableSet *)allEdges {
    NSMutableSet *allEdges = [[NSMutableSet alloc] init];
    for (LGVertex *vertex in [self.vertices allValues]) {
        for (LGEdge *edge in vertex.outEdges) {
            [allEdges addObject:edge];
        }
    }
    
    return allEdges;
}

#pragma mark - NSCopying

- (id)copyWithZone:(NSZone *)zone {
    LGGraph *graph = [[LGGraph alloc] init];
    graph.vertices = [self.vertices copyWithZone:zone];
    
    return graph;
}

@end
