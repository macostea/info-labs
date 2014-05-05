//
//  CSDBConnection.m
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSDBConnection.h"

static SPMySQLConnection *connection = nil;

@interface CSDBConnection ()

@end

@implementation CSDBConnection

+ (CSDBConnection *)connection {
    return [[self alloc] init];
}

- (void)connectWithCompletionBlock:(void (^)(BOOL))completionBlock {
    if (!connection) {
        static dispatch_once_t onceToken;
        dispatch_once(&onceToken, ^{
            dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
                SPMySQLConnection *conn = [[SPMySQLConnection alloc] init];
                conn.host = @"127.0.0.1";
                conn.port = 3306;
                conn.username = @"root";
                
                [conn connect];
                if ([conn isConnected]) {
                    [conn selectDatabase:@"Sales-Agency"];
                    connection = conn;
                    completionBlock(YES);
                } else {
                    completionBlock(NO);
                }
            });
        });
    }
}

- (NSArray *)rowsForTable:(NSString *)table {
    NSMutableArray *array = [NSMutableArray array];
    
    NSString *query = [NSString stringWithFormat:@"SELECT * FROM %@", table];
    SPMySQLResult *result = [connection queryString:query];
    
    for (NSDictionary *dictionary in result) {
        [array addObject:dictionary];
    }
    
    return array;
}

@end
