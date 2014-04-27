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

#import "CSIntegerOnlyValueFormatter.h"

@interface CSAppDelegate ()

@property (nonatomic, strong) CSRepository *repository;
@property (nonatomic, strong) NSArray *degrees;
@property (nonatomic, weak) IBOutlet NSTableView *tableView;
@property (weak) IBOutlet NSComboBox *degreeSelector;
@property (weak) IBOutlet NSTextField *minAgeTextField;
@property (weak) IBOutlet NSTextField *maxAgeTextField;

@property (nonatomic, strong) NSArray *datasource;
@property (nonatomic, strong) NSMutableArray *filteredDatasource;
@property (nonatomic, getter = isFiltered) BOOL filtered;
           
@end

@implementation CSAppDelegate

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification
{
    self.repository = [[CSRepository alloc] init];

    [self readEmployeesFromFile];
    
    CSIntegerOnlyValueFormatter *formatter = [[CSIntegerOnlyValueFormatter alloc] init];
    self.minAgeTextField.formatter = formatter;
    self.maxAgeTextField.formatter = formatter;
    
    [self reloadDatasource];
}

#pragma mark - Private methods

- (void)readEmployeesFromFile {
    NSArray *mainArray = [NSArray arrayWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"Employees" ofType:@"plist"]];
    
    for (NSDictionary *employeeDict in mainArray) {
        [self.repository addElement:[CSEmployee employeeFromDictionary:employeeDict]];
    }
}

- (void)getAllDegrees {
    NSMutableSet *currentDegrees = [NSMutableSet set];
    for (CSEmployee *employee in self.datasource) {
        if (employee.degree) {
            [currentDegrees addObject:employee.degree];
        }
    }
    
    NSMutableArray *degrees = [NSMutableArray array];
    for (NSString *degree in currentDegrees) {
        [degrees addObject:degree];
    }
    
    self.degrees = degrees;
}

- (void)filterDatasourceByDegree:(NSString *)degree {
    self.filteredDatasource = [NSMutableArray array];
    
    for (CSEmployee *employee in self.datasource) {
        if ([employee.degree isEqualToString:degree]) {
            [self.filteredDatasource addObject:employee];
        }
    }
    
    self.filtered = YES;
    [self.tableView reloadData];
}

- (void)filterDatasourceByAgeWithMin:(NSNumber *)min max:(NSNumber *)max {
    self.filteredDatasource = [NSMutableArray array];
    
    for (CSEmployee *employee in self.datasource) {
        if ([employee.age compare:min] == NSOrderedDescending && [employee.age compare:max] == NSOrderedAscending) {
            [self.filteredDatasource addObject:employee];
        }
    }
    
    self.filtered = YES;
    [self.tableView reloadData];
}

- (void)reloadDatasource {
    self.filtered = NO;
    self.datasource = [self.repository getAllElements];
    [self getAllDegrees];
    
    [self.degreeSelector reloadData];
    [self.tableView reloadData];
}

#pragma mark - NSTableView Datasource

- (NSInteger)numberOfRowsInTableView:(NSTableView *)tableView {
    return [self isFiltered] ? [self.filteredDatasource count] : [self.datasource count];
}

- (id)tableView:(NSTableView *)tableView objectValueForTableColumn:(NSTableColumn *)tableColumn row:(NSInteger)row {
    NSTableHeaderCell *header = [tableColumn headerCell];
    CSEmployee *employee = [self isFiltered] ? self.filteredDatasource[row] : self.datasource[row];
    
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

#pragma mark - NSTableView Delegate

- (void)tableView:(NSTableView *)tableView setObjectValue:(id)object forTableColumn:(NSTableColumn *)tableColumn row:(NSInteger)row {
    CSEmployee *oldEmployee = [self isFiltered] ? self.filteredDatasource[row] : self.datasource[row];
    CSEmployee *newEmployee = [oldEmployee copy];
    NSTableHeaderCell *header = [tableColumn headerCell];
    
    if ([header.title isEqualToString:@"Name"]) {
        newEmployee.name = object;
    }
    if ([header.title isEqualToString:@"Age"]) {
        newEmployee.age = object;
    }
    if ([header.title isEqualToString:@"Degree"]) {
        newEmployee.degree = object;
    }
    if ([header.title isEqualToString:@"Salary"]) {
        newEmployee.salary = object;
    }
    
    [self.repository updateElement:oldEmployee withDetailsFromElement:newEmployee];
    [self reloadDatasource];
}

#pragma mark - NSComboBox Datasource

- (NSInteger)numberOfItemsInComboBox:(NSComboBox *)aComboBox {
    return [self.degrees count];
}

- (id)comboBox:(NSComboBox *)aComboBox objectValueForItemAtIndex:(NSInteger)index {
    return self.degrees[index];
}

#pragma mark - NSComboBox Delegate

- (void)comboBoxSelectionDidChange:(NSNotification *)notification {
    [self filterDatasourceByDegree:self.degrees[[self.degreeSelector indexOfSelectedItem]]];
}

#pragma mark - Actions

- (IBAction)filterButtonPressed:(NSButton *)sender {
    NSNumberFormatter *formatter = [[NSNumberFormatter alloc] init];
    formatter.numberStyle = NSNumberFormatterDecimalStyle;
    NSNumber *minNumber = [formatter numberFromString:[self.minAgeTextField stringValue]];
    NSNumber *maxNumber = [formatter numberFromString:[self.maxAgeTextField stringValue]];
    
    if (!minNumber || !maxNumber || [maxNumber compare:minNumber] == NSOrderedAscending) {
        NSAlert *alert = [NSAlert alertWithMessageText:@"Error" defaultButton:@"Ok" alternateButton:nil otherButton:nil informativeTextWithFormat:@"Max and min must be valid integer numbers and max must be greater than min!"];
        [alert runModal];
    } else {
        [self filterDatasourceByAgeWithMin:minNumber max:maxNumber];
    }
}

- (IBAction)addEmployeeButton:(NSButton *)sender {
    CSEmployee *employee = [CSEmployee employee];
    employee.name = @"New employee";
    [self.repository addElement:employee];
    [self reloadDatasource];
}

- (IBAction)removeEmployeeButton:(id)sender {
    CSEmployee *employee = [self isFiltered] ? self.filteredDatasource[self.tableView.selectedRow] : self.datasource[self.tableView.selectedRow];
    [self.repository removeElement:employee];
    [self reloadDatasource];
}

- (IBAction)reloadButtonPressed:(NSButton *)sender {
    [self reloadDatasource];
}

- (IBAction)saveToFilePressed:(NSButton *)sender {
    NSMutableArray *array = [NSMutableArray array];
    
    for (CSEmployee *employee in self.datasource) {
        [array addObject:[employee dictionaryFromEmployee]];
    }
    
    if (![array writeToFile:[@"~/Desktop/employees_out.plist" stringByExpandingTildeInPath] atomically:NO]) {
        NSLog(@"Failed to write to file");
    }
}

@end
