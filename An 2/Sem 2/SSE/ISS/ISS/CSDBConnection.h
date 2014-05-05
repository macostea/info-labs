//
//  CSDBConnection.h
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <SPMySQL/SPMySQL.h>

@interface CSDBConnection : NSObject

+ (CSDBConnection *)connection;

- (void)connectWithCompletionBlock:(void (^)(BOOL success))completionBlock;
- (NSArray *)rowsForTable:(NSString *)table;

@end
