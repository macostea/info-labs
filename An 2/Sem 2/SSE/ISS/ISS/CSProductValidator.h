//
//  CSProductValidator.h
//  ISS
//
//  Created by Mihai Costea on 22/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "CSProduct.h"

@interface CSProductValidator : NSObject

- (BOOL)validateProduct:(CSProduct *)product;

@end
