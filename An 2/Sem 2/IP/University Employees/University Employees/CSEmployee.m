//
//  CSEmployee.m
//  University Employees
//
//  Created by Mihai Costea on 16/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSEmployee.h"

@implementation CSEmployee

+ (CSEmployee *)employee {
    return [[self alloc] init];
}

- (id)copyWithZone:(NSZone *)zone {
    CSEmployee *newEmployee = [CSEmployee employee];
    
    if (newEmployee) {
        newEmployee.name = [self.name copyWithZone:zone];
        newEmployee.age = [self.age copyWithZone:zone];
        newEmployee.degree = [self.degree copyWithZone:zone];
        newEmployee.salary = [self.salary copyWithZone:zone];
    }
    
    return newEmployee;
}

+ (CSEmployee *)employeeFromDictionary:(NSDictionary *)dictionary {
    CSEmployee *employee = [CSEmployee employee];
    employee.name = [dictionary objectForKey:@"Name"];
    employee.age = [dictionary objectForKey:@"Age"];
    employee.degree = [dictionary objectForKey:@"Degree"];
    employee.salary = [dictionary objectForKey:@"Salary"];
    
    return employee;
}

- (NSDictionary *)dictionaryFromEmployee {
    return @{@"Name":self.name,
             @"Age":self.age,
             @"Degree":self.degree,
             @"Salary":self.salary};
}

@end
