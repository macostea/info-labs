//
//  CSClientRepository.m
//  ISS
//
//  Created by Mihai Costea on 22/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSClientRepository.h"
#import "CSClient.h"

@interface CSClientRepository ()

@property (strong) NSMutableDictionary *clients;

@end

@implementation CSClientRepository

- (instancetype)init
{
    self = [super init];
    if (self) {
        self.clients = [NSMutableDictionary dictionary];
    }
    return self;
}

- (void)addElement:(CSClient *)element {
    NSArray *keys = [[self.clients allKeys] sortedArrayUsingSelector:@selector(compare:)];
    NSNumber *lastId = [keys lastObject];
    element.clientId = @([lastId intValue] + 1);
    [self.clients setObject:element forKey:[NSString stringWithFormat:@"%@", element.clientId]];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    NSString *values = [NSString stringWithFormat:@"%@, '%@', '%@'", element.clientId, element.name, element.address];
    [connection addRow:values table:@"Clients"];
}

- (void)updateElement:(CSClient *)oldElement newElement:(CSClient *)newElement {
    [self.clients setObject:newElement forKey:[NSString stringWithFormat:@"%@", oldElement.clientId]];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    
    NSString *value = [NSString stringWithFormat:@"name='%@', address='%@'", newElement.name, newElement.address];
    [connection updateRow:oldElement.clientId value:value table:@"Clients"];
}

- (void)removeElement:(CSClient *)element {
    [self.clients removeObjectForKey:element.clientId];
    
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    [connection removeRow:element.clientId table:@"Clients"];
}

- (void)getAllElementsWithCompletionBlock:(void (^)(BOOL, NSArray *))completionBlock {
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    [connection connectWithCompletionBlock:^(BOOL success) {
        [self getClientsFromDB];
        
        NSMutableArray *array = [NSMutableArray array];
        
        [self.clients enumerateKeysAndObjectsUsingBlock:^(id key, CSClient *obj, BOOL *stop) {
            [array addObject:[obj copy]];
        }];
        
        completionBlock(YES, array);

    }];
}

#pragma mark - Private methods

- (void)getClientsFromDB {
    CSDatabaseManager *connection = [CSDatabaseManager manager];
    NSArray *result = [connection rowsForTable:@"Clients"];
    
    for (NSDictionary *clientDict in result) {
        CSClient *client = [CSClient objectFromDictionary:clientDict];
        [self.clients setObject:client forKey:client.clientId];
    }
}

@end
