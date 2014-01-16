//
//  main.m
//  Lab1
//
//  Created by Mihai Costea on 5/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LGGraph+Additions.h"

int main(int argc, const char * argv[])
{

    @autoreleasepool {
        LGGraph *graph = [LGGraph graphFromTextFile:@"graph"];
        
        NSLog(@"Number of vertices: %ld", (long)[graph numberOfVertices]);
        NSLog(@"Cost between 0  and 1 is: %ld", (long)[graph edgeBetweenSource:0 destination:1].cost);
        
        LGVertex *vertex = [graph vertexWithData:1];
        
        for (LGEdge *edge in vertex.outEdges) {
            NSLog(@"Out edge from vertex 1 with cost: %ld", edge.cost);
        }
        
        NSArray *path = [graph pathBetweenFirstVertex:0 secondVertex:3];
        NSLog(@"The path from 0 to 3 is: ");
        for (LGVertex *vertex in path) {
            NSLog(@"%ld", vertex.data);
        }
        
        NSArray *lowCost = [graph lowestCostPathBetweenFirstVertex:0 secondVertex:3];
        NSLog(@"The lowest cost path from 0 to 3 is: ");
        for (LGVertex *vertex in lowCost) {
            NSLog(@"%ld", vertex.data);
        }
        
        NSArray *array = [graph maximumSizeCliques];
        __block NSUInteger it;
        __block NSUInteger maxsize = -1;
        [array enumerateObjectsUsingBlock:^(NSMutableSet *obj, NSUInteger idx, BOOL *stop) {
            if ([obj count] > maxsize) {
                maxsize = [obj count];
                it = idx;
            }
        }];
        
        NSLog(@"A maximum size clique is: ");
        for (LGVertex *vertex in array[it]) {
                NSLog(@"%ld", vertex.data);
        }
        
    }
    
    return 0;
}

