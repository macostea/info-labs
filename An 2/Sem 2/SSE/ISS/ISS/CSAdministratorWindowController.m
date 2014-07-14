//
//  CSAdministratorWindowController.m
//  ISS
//
//  Created by Mihai Costea on 04/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSAdministratorWindowController.h"

#import "CSProductRepository.h"
#import "CSAgentRepository.h"
#import "CSProduct.h"
#import "CSAgent.h"
#import "CSProductValidator.h"
#import "CSAgentValidator.h"

@interface CSAdministratorWindowController ()
@property (weak) IBOutlet NSArrayController                 *productsArrayController;
@property (weak) IBOutlet NSArrayController                 *salespeopleArrayController;
@property (nonatomic, strong) CSProductRepository           *productRepo;
@property (nonatomic, strong) CSAgentRepository             *agentRepo;

@property (nonatomic, strong) NSMutableArray                *products;
@property (nonatomic, strong) NSMutableArray                *agents;
@property (weak) IBOutlet NSTableView *productsTableView;
@property (weak) IBOutlet NSTableView *salespeopleTableView;
@end

@implementation CSAdministratorWindowController

- (id)initWithWindow:(NSWindow *)window
{
    self = [super initWithWindow:window];
    if (self) {
        self.productRepo = [[CSProductRepository alloc] init];
        self.agentRepo = [[CSAgentRepository alloc] init];
    }
    return self;
}

- (void)windowDidLoad
{
    [super windowDidLoad];
    
    [self refreshProducts];
    [self refreshAgents];
}

#pragma mark - NSTableViewDelegate

- (void)tableView:(NSTableView *)tableView setObjectValue:(id)object forTableColumn:(NSTableColumn *)tableColumn row:(NSInteger)row {
    if (tableView == self.productsTableView) {
        CSProduct *oldProduct = self.products[row];
        CSProduct *newProduct = [oldProduct copy];
        
        NSTableHeaderCell *header = [tableColumn headerCell];
        
        if ([header.title isEqualToString:@"Product"]) {
            newProduct.name = object;
        }
        
        if ([header.title isEqualToString:@"Quantity"]) {
            newProduct.quantity = @([object integerValue]);
        }
        
        if ([header.title isEqualToString:@"Price"]) {
            newProduct.price = @([object floatValue]);
        }
        
        CSProductValidator *validator = [[CSProductValidator alloc] init];
        if ([validator validateProduct:newProduct]) {
            [self.productRepo updateElement:oldProduct newElement:newProduct];
            [self refreshProducts];
        } else {
            NSAlert *alert = [NSAlert alertWithMessageText:@"Product is not valid" defaultButton:@"Ok" alternateButton:nil otherButton:nil informativeTextWithFormat:@"The details you entered are not valid"];
            [alert beginSheetModalForWindow:[self window] completionHandler:^(NSModalResponse returnCode) {
            }];
        }
        
    } else if (tableView == self.salespeopleTableView) {
        CSAgent *oldAgent = self.agents[row];
        CSAgent *newAgent = [oldAgent copy];
        
        NSTableHeaderCell *header = [tableColumn headerCell];
        
        if ([header.title isEqualToString:@"Name"]) {
            newAgent.name = object;
        }
        
        CSAgentValidator *validator = [[CSAgentValidator alloc] init];
        
        if ([validator validateAgent:newAgent]) {
            [self.agentRepo updateElement:oldAgent newElement:newAgent];
            [self refreshAgents];
        } else {
            NSAlert *alert = [NSAlert alertWithMessageText:@"Agent is not valid" defaultButton:@"Ok" alternateButton:nil otherButton:nil informativeTextWithFormat:@"The details you entered are not valid"];
            [alert beginSheetModalForWindow:[self window] completionHandler:^(NSModalResponse returnCode) {
            }];
        }
    }
}

#pragma mark - Private methods

- (void)refreshProducts {
    [self.productRepo getAllElementsWithCompletionBlock:^(BOOL success, NSArray *results) {
        if (success) {
            self.products = [results mutableCopy];
            [self.productsArrayController setContent:self.products];
        }
    }];
}

- (void)refreshAgents {
    [self.agentRepo getAllElementsWithCompletionBlock:^(BOOL success, NSArray *results) {
        if (success) {
            self.agents = [results mutableCopy];
            [self.salespeopleArrayController setContent:self.agents];
        }
    }];
}

#pragma mark - Actions

- (IBAction)addProductsPressed:(NSButton *)sender {
    CSProduct *product = [[CSProduct alloc] init];
    product.name = @"New product";
    product.price = @(0);
    product.quantity = @(0);
    
    [self.productRepo addElement:product];
    [self refreshProducts];
}

- (IBAction)removeProductsPressed:(NSButton *)sender {
    CSProduct *product = self.products[self.productsTableView.selectedRow];
    [self.productRepo removeElement:product];
    
    [self refreshProducts];
}

- (IBAction)addSalespeoplePressed:(id)sender {
    CSAgent *agent = [[CSAgent alloc] init];
    agent.name = @"New agent";
    
    [self.agentRepo addElement:agent];
    [self refreshAgents];
}

- (IBAction)removeSalespeoplePressed:(NSButton *)sender {
    CSProduct *agent = self.agents[self.salespeopleTableView.selectedRow];
    [self.agentRepo removeElement:agent];
    
    [self refreshAgents];
}

@end
