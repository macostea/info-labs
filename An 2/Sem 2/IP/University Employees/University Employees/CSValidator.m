//
//  CSValidator.m
//  University Employees
//
//  Created by Mihai Costea on 27/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSValidator.h"

@implementation CSValidator

+ (NSArray *)validateEmployee:(CSEmployee *)employee {
    NSMutableArray *errors = [NSMutableArray array];
    
    if ([employee.age integerValue] < 0) {
        [errors addObject:@"Age must be a valid integer number greater than 0"];
    }
    
    if ([employee.salary floatValue] < 0) {
        [errors addObject:@"Salary must be a valid number greater then 0"];
    }
    
    return errors;
}

@end
