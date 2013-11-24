//
//  LGGraph+Additions.m
//  Lab1
//
//  Created by Mihai Costea on 5/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import "LGGraph+Additions.h"

@implementation LGGraph (Additions)

+ (LGGraph *)graphFromTextFile:(NSString *)textFile {
    NSString *fileRoot = [[NSBundle mainBundle] pathForResource:textFile ofType:@"txt"];
    NSString *fileContent = [NSString stringWithContentsOfFile:fileRoot encoding:NSUTF8StringEncoding error:nil];
    NSMutableArray *lines = [[fileContent componentsSeparatedByCharactersInSet:[NSCharacterSet newlineCharacterSet]] mutableCopy];
    
    NSArray *firstLineComponents = [lines[0] componentsSeparatedByCharactersInSet:[NSCharacterSet whitespaceCharacterSet]];
    [lines removeObjectAtIndex:0];
    
    NSInteger numberOfVertices = [firstLineComponents[0] integerValue];
    NSMutableDictionary *vertices = [[NSMutableDictionary alloc] init];
    
    for (NSInteger it=0; it<numberOfVertices; it++) {
        LGVertex *vertex = [[LGVertex alloc] init];
        vertex.data = it;
        
        [vertices setObject:vertex forKey:@(it)];
    }
    
    LGGraph *graph = [[LGGraph alloc] initWithVertices:vertices];
    
    for (NSString *line in lines) {
        NSArray *lineComponents = [line componentsSeparatedByCharactersInSet:[NSCharacterSet whitespaceCharacterSet]];
        
        NSInteger sourceData = [lineComponents[0] integerValue];
        NSInteger destinationData = [lineComponents[1] integerValue];
        NSInteger cost = [lineComponents[2] integerValue];
        
        LGVertex *source = [graph vertexWithData:sourceData];
        LGVertex *destination = [graph vertexWithData:destinationData];
        
        LGEdge *outEdge = [[LGEdge alloc] initWithDestination:destination cost:cost];
        LGEdge *inEdge = [[LGEdge alloc] initWithDestination:source cost:cost];
        
        [source.outEdges addObject:outEdge];
        [destination.inEdges addObject:inEdge];
    }
    
    return graph;
}

@end
