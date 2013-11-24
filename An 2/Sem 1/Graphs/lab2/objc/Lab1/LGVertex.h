//
//  LGVertex.h
//  Lab1
//
//  Created by Mihai Costea on 5/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LGVertex : NSObject

@property (nonatomic) NSInteger                         data;
@property (nonatomic, strong) NSMutableSet              *inEdges;
@property (nonatomic, strong) NSMutableSet              *outEdges;

@end
