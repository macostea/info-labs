//
//  CSOrderRepository.m
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSOrderRepository.h"
#import "CSOrder.h"

@interface CSOrderRepository ()

@property (strong) NSMutableDictionary *orders;
@property (strong) NSNumber *lastId;

@end

@implementation CSOrderRepository

- (instancetype)init
{
    self = [super init];
    if (self) {
        self.orders = [NSMutableDictionary dictionary];
        self.lastId = @(0);
    }
    return self;
}

- (void)addElement:(CSOrder *)element {
    element.orderId = @([self.lastId integerValue] + 1);
    [self.orders setObject:element forKey:element.orderId];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    
    NSString *row = [NSString stringWithFormat:@"%@,%@,%@,%@", element.orderId, element.quantity, element.product.productId, element.agent.agentId];
    [connection addRow:row table:@"Orders"];
}

- (void)updateElement:(CSOrder *)oldElement newElement:(CSOrder *)newElement {
    [self.orders setObject:newElement forKey:oldElement.orderId];
}

- (void)removeElement:(CSOrder *)element {
    [self.orders removeObjectForKey:element.orderId];
}

- (void)getAllElementsWithCompletionBlock:(void (^)(BOOL, NSArray *))completionBlock {
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    [connection connectWithCompletionBlock:^(BOOL success) {
        [self getOrdersFromDB];
        NSMutableArray *array = [NSMutableArray array];
        
        [self.orders enumerateKeysAndObjectsUsingBlock:^(id key, CSOrder *obj, BOOL *stop) {
            [array addObject:[obj copy]];
            completionBlock(YES, array);
        }];
    }];
}

#pragma mark - Private methods

- (void)getOrdersFromDB {
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    NSArray *result = [connection rowsForTable:@"Orders"];
    
    for (NSDictionary *orderDict in result) {
        NSMutableDictionary *preparedOrder = [orderDict mutableCopy];
        [preparedOrder setObject:[connection rowForId:[orderDict objectForKey:@"clientId"] table:@"Clients"] forKey:@"client"];
        [preparedOrder setObject:[connection rowForId:[orderDict objectForKey:@"productId"] table:@"Products"] forKey:@"product"];
        [preparedOrder setObject:[connection rowForId:[orderDict objectForKey:@"agentId"] table:@"Agents"] forKey:@"agent"];
        CSOrder *order = [CSOrder objectFromDictionary:preparedOrder];
        
        if ([order.orderId integerValue] > [self.lastId integerValue]) {
            self.lastId = order.orderId;
        }
        
        [self.orders setObject:order forKey:order.orderId];
    }
}

@end
