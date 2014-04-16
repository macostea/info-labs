//
//  CSEmployee.h
//  University Employees
//
//  Created by Mihai Costea on 16/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CSEmployee : NSObject

@property (strong) NSNumber  *employeeId;
@property (strong) NSString  *name;
@property (strong) NSNumber  *age;
@property (strong) NSString  *degree;
@property (strong) NSNumber  *salary;

+ (CSEmployee *)employee;

@end
