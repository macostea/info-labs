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

@property (strong) NSMutableDictionary  *products;

@end

@implementation CSProductRepository

- (instancetype)init
{
    self = [super init];
    if (self) {
        self.products = [NSMutableDictionary dictionary];
    }
    return self;
}

- (void)addElement:(CSProduct *)element {
    NSArray *keys = [[self.products allKeys] sortedArrayUsingSelector:@selector(compare:)];
    NSNumber *lastId = [keys lastObject];
    element.productId = @([lastId intValue] + 1);
    [self.products setObject:element forKey:[NSString stringWithFormat:@"%@", element.productId]];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    NSString *values = [NSString stringWithFormat:@"%@, '%@', %@, %@", element.productId, element.name, element.price, element.quantity];
    [connection addRow:values table:@"Products"];
}

- (void)updateElement:(CSProduct *)oldElement newElement:(CSProduct *)newElement {
    [self.products setObject:newElement forKey:[NSString stringWithFormat:@"%@", oldElement.productId]];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    
    NSString *value = [NSString stringWithFormat:@"name='%@', price=%@, quantity=%@", newElement.name, newElement.price, newElement.quantity];
    [connection updateRow:oldElement.productId value:value table:@"Products"];
}

- (void)removeElement:(CSProduct *)element {
    [self.products removeObjectForKey:element.productId];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    [connection removeRow:element.productId table:@"Products"];
}

- (void)getAllElementsWithCompletionBlock:(void (^)(BOOL, NSArray *))completionBlock {
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    [connection connectWithCompletionBlock:^(BOOL success) {
        [self getProductsFromDB];
        NSMutableArray *array = [NSMutableArray array];
        
        [self.products enumerateKeysAndObjectsUsingBlock:^(id key, CSProduct *obj, BOOL *stop) {
            [array addObject:[obj copy]];
            completionBlock(YES, array);
        }];
    }];
}

#pragma mark - Private methods

- (void)getProductsFromDB {
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    NSArray *result = [connection rowsForTable:@"Products"];
    
    for (NSDictionary *productDict in result) {
        CSProduct *product = [CSProduct objectFromDictionary:productDict];
        [self.products setObject:product forKey:product.productId];
    }
}

@end
