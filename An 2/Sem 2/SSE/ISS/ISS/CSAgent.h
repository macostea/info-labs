//
//  CSAgent.h
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CSAgent : NSObject <NSCopying>

@property (strong) NSNumber *agentId;
@property (strong) NSString *name;

+ (CSAgent *)objectFromDictionary:(NSDictionary *)dictionary;
- (NSDictionary *)dictionaryFromObject;

@end
