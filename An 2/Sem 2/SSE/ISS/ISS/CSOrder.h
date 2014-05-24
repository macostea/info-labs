//
//  CSOrder.h
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "CSProduct.h"
#import "CSAgent.h"
#import "CSClient.h"

@interface CSOrder : NSObject <NSCopying>

@property (strong) NSNumber     *orderId;
@property (strong) CSProduct    *product;
@property (strong) CSAgent      *agent;
@property (strong) CSClient     *client;
@property (strong) NSNumber     *quantity;
@property (strong) NSString     *status;

+ (CSOrder *)objectFromDictionary:(NSDictionary *)dictionary;
- (NSDictionary *)dictionaryFromObject;

@end
