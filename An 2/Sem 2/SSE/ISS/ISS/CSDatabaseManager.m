//
//  CSDBConnection.m
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSDatabaseManager.h"

static SPMySQLConnection *connection = nil;

@interface CSDatabaseManager ()

@end

@implementation CSDatabaseManager

+ (CSDatabaseManager *)manager {
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
    } else {
        completionBlock(YES);
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

- (NSDictionary *)rowForId:(NSNumber *)rowId table:(NSString *)table{
    NSString *query = [NSString stringWithFormat:@"SELECT * FROM %@ WHERE id=%@", table, rowId];
    
    SPMySQLResult *result = [connection queryString:query];
    
    for (NSDictionary *dictionary in result) {
        return dictionary;
    }
    
    return nil;
}

- (void)addRow:(NSString *)row table:(NSString *)table {
    NSString *query = [NSString stringWithFormat:@"INSERT INTO %@ VALUES(%@)", table, row];
    
    SPMySQLResult *res = [connection queryString:query];
}

- (void)updateRow:(NSNumber *)rowId value:(NSString *)value table:(NSString *)table {
    NSString *query = [NSString stringWithFormat:@"UPDATE %@ SET %@ WHERE id=%@", table, value, rowId];
    
    [connection queryString:query];
}

- (void)removeRow:(NSNumber *)rowId table:(NSString *)table {
    NSString *query = [NSString stringWithFormat:@"DELETE FROM %@ WHERE id=%@", table, rowId];
    
    [connection queryString:query];
}

@end
