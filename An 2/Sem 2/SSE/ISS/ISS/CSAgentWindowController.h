//
//  CSAgentWindowController.h
//  ISS
//
//  Created by Mihai Costea on 04/05/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Cocoa/Cocoa.h>

#import "CSAgent.h"

@interface CSAgentWindowController : NSWindowController <NSTableViewDelegate>

@property (strong) CSAgent *currentAgent;

@end
