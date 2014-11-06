//
//  ViewController.m
//  PKC-Lab1
//
//  Created by Mihai Costea on 04/11/14.
//  Copyright (c) 2014 Mihai Costea. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@property (weak) IBOutlet NSTextField *alphabet;
@property (weak) IBOutlet NSTextField *plainText;
@property (weak) IBOutlet NSTextField *cipherText;

@property (strong) NSMutableArray *alphabetArray;
@property (strong) NSArray *initialAlphabetArray;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (void)setRepresentedObject:(id)representedObject {
    [super setRepresentedObject:representedObject];

    // Update the view, if already loaded.
}

#pragma mark - Private

- (void)prepareAlphabet {
    self.initialAlphabetArray = @[@" ", @"a", @"b", @"c", @"d", @"e", @"f", @"g", @"h", @"i", @"j", @"k", @"l", @"m", @"n", @"o", @"p", @"q", @"r", @"s", @"t", @"u", @"v", @"w", @"x", @"y", @"z"];
    self.alphabetArray = [NSMutableArray array];
    for (int i=0; i<[self.alphabet.stringValue length]; i++) {
        NSRange range;
        range.location = i;
        range.length = 1;
        NSString *character = [self.alphabet.stringValue substringWithRange:range];
        
        [self.alphabetArray addObject:character];
    }
}

#pragma mark - Actions

- (IBAction)encrypt:(id)sender {
    [self prepareAlphabet];
    
    [self.cipherText setStringValue:@""];
    
    for (int i=0; i<[self.plainText.stringValue length]; i++) {
        NSRange range;
        range.location = i;
        range.length = 1;
        NSString *character = [self.plainText.stringValue substringWithRange:range];
        
        NSInteger j = [self.initialAlphabetArray indexOfObject:character];
        NSString *encryptedChar = self.alphabetArray[j];
        
        NSMutableString *currentCypherText = [self.cipherText.stringValue mutableCopy];
        [currentCypherText appendString:encryptedChar];
        [self.cipherText setStringValue:currentCypherText];
    }
}

- (IBAction)decrypt:(id)sender {
    [self prepareAlphabet];
    
    [self.plainText setStringValue:@""];
    
    for (int i=0; i<[self.cipherText.stringValue length]; i++) {
        NSRange range;
        range.location = i;
        range.length = 1;
        NSString *character = [self.cipherText.stringValue substringWithRange:range];
        
        NSInteger j = [self.alphabetArray indexOfObject:character];
        NSString *decryptedChar = self.initialAlphabetArray[j];
        
        NSMutableString *currentPlainText = [self.plainText.stringValue mutableCopy];
        [currentPlainText appendString:decryptedChar];
        [self.plainText setStringValue:currentPlainText];
    }
}

@end
