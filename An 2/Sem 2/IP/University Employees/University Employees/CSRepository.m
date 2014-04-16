//
//  CSRepository.m
//  University Employees
//
//  Created by Mihai Costea on 16/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSRepository.h"

@interface CSRepository ()

@property (strong) NSMutableArray *array;

@end

@implementation CSRepository

- (instancetype)init
{
    self = [super init];
    if (self) {
        self.array = [NSMutableArray array];
    }
    return self;
}

- (void)addElement:(id)element {
    [self.array addObject:element];
}

- (void)removeElement:(id)element {
    [self.array removeObject:element];
}

- (NSArray *)getAllElements {
    return [self.array copy];
}

- (void)updateElement:(id)element withDetailsFromElement:(id)updatedElement {
    [self.array replaceObjectAtIndex:[self.array indexOfObject:element] withObject:updatedElement];
}

- (void)readFromFile:(NSString *)fileName {
    
}

- (void)writeToFile:(NSString *)fileName {
    
}

@end
