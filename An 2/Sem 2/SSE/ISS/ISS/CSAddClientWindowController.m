//
//  CSAddClientWindowController.m
//  ISS
//
//  Created by Mihai Costea on 22/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSAddClientWindowController.h"

@interface CSAddClientWindowController ()

@end

@implementation CSAddClientWindowController

- (id)initWithWindow:(NSWindow *)window
{
    self = [super initWithWindow:window];
    if (self) {
        // Initialization code here.
    }
    return self;
}

- (void)windowDidLoad
{
    [super windowDidLoad];
    
    // Implement this method to handle any initialization after your window controller's window has been loaded from its nib file.
}

- (IBAction)addClient:(NSButton *)sender {
    [NSApp stopModal];
}

@end
