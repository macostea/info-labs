//
//  CSAppDelegate.m
//  ISS
//
//  Created by Mihai Costea on 17/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSAppDelegate.h"

#import "CSProductRepository.h"
#import "CSAgentRepository.h"
#import "CSAdministratorWindowController.h"
#import "CSAgentWindowController.h"

#import "CSAgent.h"

@interface CSAppDelegate ()

@property (strong) CSProductRepository *productRepo;
@property (strong) CSAgentRepository *agentRepo;

@property (weak) IBOutlet NSPopUpButton *agentPicker;
@property (strong) NSArray *agents;
@property (weak) IBOutlet NSArrayController *agentsController;

@property (strong) CSAdministratorWindowController *administratorWindowController;
@property (strong) CSAgentWindowController *agentWindowController;

@end

@implementation CSAppDelegate

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification
{
    self.agentRepo = [[CSAgentRepository alloc] init];
    [self populateAgentsButton];
}

- (void)awakeFromNib {
    
}

- (IBAction)loginAsAdministratorPressed:(NSButton *)sender {
    [self.window orderOut:self];
    self.administratorWindowController = [[CSAdministratorWindowController alloc] initWithWindowNibName:@"CSAdministratorWindow"];
    [self.administratorWindowController showWindow:self];
}

- (IBAction)loginAsAgentPicked:(NSPopUpButton *)sender {
}

#pragma mark - Private methods

- (void)populateAgentsButton {
    [self.agentRepo getAllElementsWithCompletionBlock:^(BOOL success, NSArray *results) {
        if (success) {
            self.agents = results;
            [self.agentsController setContent:self.agents];
        }
    }];
}

- (IBAction)didSelectAgent:(NSPopUpButton *)sender {
    NSInteger index = [sender indexOfSelectedItem];
    CSAgent *selectedAgent = self.agents[index];
    
    NSLog(@"Selected agent: %@", selectedAgent.name);
    
    [self.window orderOut:self];
    self.agentWindowController = [[CSAgentWindowController alloc] initWithWindowNibName:@"CSAgentWindow"];
    [self.agentWindowController showWindow:self];
}

@end
