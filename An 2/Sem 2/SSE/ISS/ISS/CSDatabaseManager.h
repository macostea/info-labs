//
//  CSDBConnection.h
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <SPMySQL/SPMySQL.h>

@interface CSDatabaseManager : NSObject

+ (CSDatabaseManager *)manager;

- (void)connectWithCompletionBlock:(void (^)(BOOL success))completionBlock;
- (NSArray *)rowsForTable:(NSString *)table;
- (void)addRow:(NSString *)row table:(NSString *)table;
- (void)updateRow:(NSNumber *)rowId value:(NSString *)value table:(NSString *)table;
- (void)removeRow:(NSNumber *)rowId table:(NSString *)table;

@end
