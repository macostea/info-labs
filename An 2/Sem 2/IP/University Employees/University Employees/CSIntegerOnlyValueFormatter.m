//
//  CSIntegerOnlyValueFormatter.m
//  University Employees
//
//  Created by Mihai Costea on 27/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#import "CSIntegerOnlyValueFormatter.h"

@implementation CSIntegerOnlyValueFormatter

- (BOOL)isPartialStringValid:(NSString *)partialString newEditingString:(NSString *__autoreleasing *)newString errorDescription:(NSString *__autoreleasing *)error {
    if ([partialString length] == 0) {
        return YES;
    }
    
    NSScanner *scanner = [NSScanner scannerWithString:partialString];
    
    if (![scanner scanInt:NULL] && [scanner isAtEnd]) {
        NSBeep();
        return NO;
    }
    
    return YES;
}

@end
