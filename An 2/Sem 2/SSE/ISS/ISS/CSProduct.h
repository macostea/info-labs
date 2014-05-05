//
//  CSProduct.h
//  ISS
//
//  Created by Mihai Costea on 05/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CSProduct : NSObject <NSCopying>

@property (strong) NSNumber *productId;
@property (strong) NSString *name;
@property (strong) NSNumber *price;
@property (strong) NSNumber *quantity;

+ (CSProduct *)objectFromDictionary:(NSDictionary *)dictionary;
- (NSDictionary *)dictionaryFromObject;

@end
