//
//  CSRepository.h
//  University Employees
//
//  Created by Mihai Costea on 16/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CSRepository : NSObject

- (void)addElement:(id)element;
- (void)removeElement:(id)element;
- (NSArray *)getAllElements;
- (void)updateElement:(id)element withDetailsFromElement:(id)updatedElement;
- (void)readFromFile:(NSString *)fileName;
- (void)writeToFile:(NSString *)fileName;

@end
