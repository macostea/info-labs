//
//  CSProductRepository.m
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSProductRepository.h"

#import "CSProduct.h"

@interface CSProductRepository ()

@property (strong) NSMutableArray *products;

@end

@implementation CSProductRepository

- (instancetype)init
{
    self = [super init];
    if (self) {
        self.products = [NSMutableArray array];
        
        [self getProductsFromDB];
    }
    return self;
}

- (void)addElement:(id)element {
    [self.products addObject:element];
}

- (void)updateElement:(id)oldElement newElement:(id)newElement {
    [self.products replaceObjectAtIndex:[self.products indexOfObject:oldElement] withObject:newElement];
}

- (void)removeElement:(id)element {
    [self.products removeObject:element];
}

- (void)getAllElementsWithCompletionBlock:(void (^)(BOOL, NSArray *))completionBlock {
    CSDBConnection *connection = [CSDBConnection connection];
    [connection connectWithCompletionBlock:^(BOOL success) {
        [self getProductsFromDB];
        completionBlock(YES, [self.products copy]);
    }];
}

#pragma mark - Private methods

- (void)getProductsFromDB {
    CSDBConnection *connection = [CSDBConnection connection];
    NSArray *result = [connection rowsForTable:@"Products"];
    
    for (NSDictionary *productDict in result) {
        [self.products addObject:[CSProduct objectFromDictionary:productDict]];
    }
}

@end
