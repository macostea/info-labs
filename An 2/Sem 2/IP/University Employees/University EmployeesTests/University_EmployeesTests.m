//
//  University_EmployeesTests.m
//  University EmployeesTests
//
//  Created by Mihai Costea on 16/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <XCTest/XCTest.h>

#import "CSRepository.h"
#import "CSEmployee.h"

@interface University_EmployeesTests : XCTestCase

@property (nonatomic, strong) CSRepository *repo;

@end

@implementation University_EmployeesTests

- (void)setUp
{
    [super setUp];
}

- (void)tearDown
{
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void)testCreateRepository {
    self.repo = [[CSRepository alloc] init];
    XCTAssertNotNil(self.repo, @"");
}

- (void)testAddEmployee {
    self.repo = [[CSRepository alloc] init];
    CSEmployee *employee = [CSEmployee employee];
    employee.name = @"newName";
    employee.age = @(10);
    employee.salary = @(11.10);
    [self.repo addElement:employee];
    XCTAssertTrue([[self.repo getAllElements] count] == 1, @"");
}

- (void)testGetAllEmployees {
    self.repo = [[CSRepository alloc] init];
    CSEmployee *employee = [CSEmployee employee];
    employee.name = @"newName";
    employee.age = @(10);
    employee.salary = @(11.10);
    [self.repo addElement:employee];
    NSArray *employees = [self.repo getAllElements];
    CSEmployee *employee1 = employees[0];
    
    XCTAssertEqual(employee1.name, @"newName", @"");
    XCTAssertEqual([employee1.age integerValue], 10, @"");
    XCTAssertEqual([employee1.salary doubleValue], 11.10, @"");
}

- (void)testRemoveEmployee {
    self.repo = [[CSRepository alloc] init];
    CSEmployee *employee = [CSEmployee employee];
    employee.name = @"newName";
    employee.age = @(10);
    employee.salary = @(11.10);
    [self.repo addElement:employee];
    NSArray *employees = [self.repo getAllElements];
    CSEmployee *employee1 = employees[0];
    
    [self.repo removeElement:employee1];
    
    XCTAssertEqual([[self.repo getAllElements] count], 0, @"");
}

@end
