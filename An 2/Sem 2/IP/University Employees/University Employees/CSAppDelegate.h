//
//  CSAppDelegate.h
//  University Employees
//
//  Created by Mihai Costea on 16/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import <Cocoa/Cocoa.h>

#import "CSEmployee.h"

@interface CSAppDelegate : NSObject <NSApplicationDelegate, NSTableViewDataSource, NSTableViewDelegate>

@property (assign) IBOutlet NSWindow *window;

@end
