//
//  CSAppDelegate.m
//  University Employees
//
//  Created by Mihai Costea on 16/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSAppDelegate.h"
#import "CSRepository.h"
#import "CSEmployee.h"

@interface CSAppDelegate ()

@property (strong) CSRepository *repository;
@property (weak) IBOutlet NSTableView *tableView;

@property (strong) NSArray *datasource;

@end

@implementation CSAppDelegate

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification
{
    self.repository = [[CSRepository alloc] init];
    
    CSEmployee *employee = [CSEmployee employee];
    employee.name = @"ION";
    employee.age = @(50);
    employee.degree = @"Professor";
    employee.salary = @(5000);
    
    [self.repository addElement:employee];
    
    CSEmployee *employee1 = [CSEmployee employee];
    employee1.name = @"ION";
    employee1.age = @(50);
    employee1.degree = @"Professor";
    employee1.salary = @(5000);
    
    [self.repository addElement:employee1];
    
    CSEmployee *employee2 = [CSEmployee employee];
    employee2.name = @"ION";
    employee2.age = @(50);
    employee2.degree = @"Professor";
    employee2.salary = @(5000);
    
    [self.repository addElement:employee2];
    
    self.datasource = [self.repository getAllElements];
    [self.tableView reloadData];
}

#pragma mark - NSTableView Datasource

- (NSInteger)numberOfRowsInTableView:(NSTableView *)tableView {
    return [self.datasource count];
}

- (id)tableView:(NSTableView *)tableView objectValueForTableColumn:(NSTableColumn *)tableColumn row:(NSInteger)row {
    NSTableHeaderCell *header = [tableColumn headerCell];
    CSEmployee *employee = self.datasource[row];
    
    if ([header.title isEqualToString:@"Name"]) {
        return employee.name;
    }
    if ([header.title isEqualToString:@"Age"]) {
        return employee.age;
    }
    if ([header.title isEqualToString:@"Degree"]) {
        return employee.degree;
    }
    if ([header.title isEqualToString:@"Salary"]) {
        return employee.salary;
    }
    
    return nil;
}



@end
