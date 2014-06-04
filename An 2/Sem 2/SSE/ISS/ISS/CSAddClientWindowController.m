//
//  CSAddClientWindowController.m
//  ISS
//
//  Created by Mihai Costea on 22/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSAddClientWindowController.h"
#import "CSClientRepository.h"
#import "CSClient.h"

@interface CSAddClientWindowController ()

@property (strong) CSClientRepository *clientRepo;
@property (weak) IBOutlet NSTextField *name;
@property (weak) IBOutlet NSTextField *address;

@end

@implementation CSAddClientWindowController

- (id)initWithWindow:(NSWindow *)window
{
    self = [super initWithWindow:window];
    if (self) {
        self.clientRepo = [[CSClientRepository alloc] init];
    }
    return self;
}

- (void)windowDidLoad
{
    [super windowDidLoad];
    
}

- (IBAction)addClient:(NSButton *)sender {
    CSClient *client = [[CSClient alloc] init];
    client.name = [self.name stringValue];
    client.address = [self.name stringValue];
    [self.clientRepo addElement:client];
    
    [[NSNotificationCenter defaultCenter] postNotificationName:@"clientAdded" object:nil];
    
    [NSApp endSheet:[self window]];
}

@end
