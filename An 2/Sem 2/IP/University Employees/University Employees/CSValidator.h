//
//  CSValidator.h
//  University Employees
//
//  Created by Mihai Costea on 27/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "CSEmployee.h"

@interface CSValidator : NSObject

+ (NSArray *)validateEmployee:(CSEmployee *)employee;

@end
