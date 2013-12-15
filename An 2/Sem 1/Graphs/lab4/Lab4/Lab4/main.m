//
//  main.m
//  Lab4
//
//  Created by Mihai Costea on 10/12/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LGGraph+Additions.h"

NSArray* TopologicalSort(LGGraph *graph, NSError **error) {
    NSMutableArray *sortedElements = [[NSMutableArray alloc] init];
    NSMutableSet *verticesWithNoInboundEdges = [[NSMutableSet alloc] init];
    
    NSInteger numberOfVertices = [graph numberOfVertices];
    
    // Search for all vertices which don't have any inbound edges
    for (int it=0; it<numberOfVertices; it++) {
        LGVertex *vertex = [graph vertexWithData:it];
        if ([vertex.inEdges count] == 0) {
            [verticesWithNoInboundEdges addObject:vertex];
        }
    }
    
    while ([verticesWithNoInboundEdges count] != 0) {
        LGVertex *vertex = [verticesWithNoInboundEdges anyObject];
        
        for (int it=0; it<numberOfVertices; it++) {
            LGVertex *dest = [graph vertexWithData:it];
            LGEdge *outEdge = [graph edgeBetweenSource:vertex.data destination:dest.data];
            
            if (outEdge) {
                // Remove edges from the graph and add vertices to the set
                [vertex.outEdges removeObject:outEdge];
                [dest.inEdges removeObject:[graph edgeBetweenSource:dest.data destination:vertex.data]];
                
                if ([dest.inEdges count] == 0) {
                    [verticesWithNoInboundEdges addObject:dest];
                }
            }
        }
        
        [sortedElements addObject:vertex];
        
        [verticesWithNoInboundEdges removeObject:vertex];
    }
    
    // Verify the number of edges remianing in the graph
    NSInteger numberOfEdges = 0;
    for (int it=0; it<numberOfVertices; it++) {
        LGVertex *vertex = [graph vertexWithData:it];
        numberOfEdges += [vertex.outEdges count];
    }
    
    if (numberOfEdges != 0) {
        // Edges were still found so we return an error
        NSDictionary *errorDetails = @{NSLocalizedDescriptionKey: @"Graph is not acyclic"};
        *error = [NSError errorWithDomain:@"graph" code:200 userInfo:errorDetails];
        
        return nil;
    }
    
    return sortedElements;
}

int main(int argc, const char * argv[])
{

    @autoreleasepool {
        LGGraph *graph = [LGGraph graphFromTextFile:@"graph"];
        
        NSError *error;
        NSArray *topSort = TopologicalSort([graph copy], &error);
        if (!topSort) {
            NSLog(@"%s: %@", __PRETTY_FUNCTION__, [error localizedDescription]);
        } else {
            for (LGVertex *vertex in topSort) {
                NSLog(@"%ld", vertex.data);
            }
        }
        
        
        
    }
    return 0;
}

