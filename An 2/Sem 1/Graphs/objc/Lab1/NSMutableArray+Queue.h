//
//  NSMutableArray+Queue.h
//  Lab1
//
//  Created by Mihai Costea on 13/11/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSMutableArray (Queue)

- (id)dequeue;
- (void)enqueue:(id)obj;

@end
