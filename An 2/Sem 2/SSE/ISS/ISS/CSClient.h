//
//  CSClient.h
//  ISS
//
//  Created by Mihai Costea on 22/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CSClient : NSObject <NSCopying>

@property (nonatomic, strong) NSNumber *clientId;
@property (nonatomic, strong) NSString *name;
@property (nonatomic, strong) NSString *address;

+ (CSClient *)objectFromDictionary:(NSDictionary *)dictionary;
- (NSDictionary *)dictionaryFromObject;

@end
