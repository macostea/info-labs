//
//  CSAgentWindowController.m
//  ISS
//
//  Created by Mihai Costea on 04/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSAgentWindowController.h"

#import "CSProductRepository.h"
#import "CSOrderRepository.h"

#import "CSProduct.h"
#import "CSOrder.h"

@interface CSAgentWindowController ()

@property (strong) CSProductRepository *productRepo;
@property (strong) CSOrderRepository *orderRepo;
@property (strong) NSArray *products;
@property (weak) IBOutlet NSArrayController *productArrayController;
@property (weak) IBOutlet NSTableView *tableView;
@property (weak) IBOutlet NSTextField *quantityTextField;

@end

@implementation CSAgentWindowController

- (id)initWithWindow:(NSWindow *)window
{
    self = [super initWithWindow:window];
    if (self) {
        self.productRepo = [[CSProductRepository alloc] init];
        self.orderRepo = [[CSOrderRepository alloc] init];
    }
    return self;
}

- (void)windowDidLoad {
    [super windowDidLoad];
    
    [self refreshProducts];
}

#pragma mark - NSTableView Delegate

- (BOOL)tableView:(NSTableView *)tableView shouldEditTableColumn:(NSTableColumn *)tableColumn row:(NSInteger)row {
    return NO;
}

#pragma mark - NSAlert Delegate

#pragma mark - Private methods

- (void)refreshProducts {
    [self.productRepo getAllElementsWithCompletionBlock:^(BOOL success, NSArray *results) {
        if (success) {
            self.products = results;
            [self.productArrayController setContent:self.products];
        }
    }];
}

#pragma mark - Actions

- (IBAction)orderButtonPressed:(NSButton *)sender {
    NSInteger quantity = [self.quantityTextField intValue];
    if (quantity == 0) {
        NSAlert *alert = [NSAlert alertWithMessageText:@"Error" defaultButton:@"Ok" alternateButton:nil otherButton:nil informativeTextWithFormat:@"Quantity field must be a valid integer number > 0"];
        [alert runModal];
    } else {
        CSProduct *selectedProduct = self.products[[self.tableView selectedRow]];
        if (quantity > [selectedProduct.quantity integerValue]) {
            NSAlert *alert = [NSAlert alertWithMessageText:@"Error" defaultButton:@"Ok" alternateButton:nil otherButton:nil informativeTextWithFormat:@"Requested quantity is not available. Please input a smaller quantity."];
            [alert beginSheetModalForWindow:self.window completionHandler:^(NSModalResponse returnCode) {
                
            }];
        } else {
            NSAlert *alert = [[NSAlert alloc] init];
            [alert setAlertStyle:NSInformationalAlertStyle];
            [alert setMessageText:[NSString stringWithFormat:@"You are about to order %@ units of %@", @(quantity), selectedProduct.name]];
            [alert addButtonWithTitle:@"Ok"];
            [alert addButtonWithTitle:@"Cancel"];
            [alert beginSheetModalForWindow:self.window completionHandler:^(NSModalResponse returnCode) {
                if (returnCode == NSAlertFirstButtonReturn) {
                    CSOrder *order = [[CSOrder alloc] init];
                    order.product = selectedProduct;
                    order.agent = self.currentAgent;
                    order.quantity = @(quantity);
                    
                    [self.orderRepo addElement:order];
                    
                    CSProduct *newProduct = [selectedProduct copy];
                    newProduct.quantity = @([newProduct.quantity integerValue] - quantity);
                    [self.productRepo updateElement:selectedProduct newElement:newProduct];
                    
                    [self refreshProducts];
                }
            }];
        }
    }
}

@end
