//
//  CSRepository.h
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "CSDBConnection.h"

@interface CSRepository : NSObject

#pragma mark - Protected
- (void)addElement:(id)element;
- (void)updateElement:(id)oldElement newElement:(id)newElement;
- (void)removeElement:(id)element;
- (void)getAllElementsWithCompletionBlock:(void (^)(BOOL success, NSArray *results))completionBlock;

@end
