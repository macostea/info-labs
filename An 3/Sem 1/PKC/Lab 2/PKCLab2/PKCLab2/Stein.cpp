//
//  Stein.cpp
//  PKCLab2
//
//  Created by Mihai Costea on 05/11/14.
//  Copyright (c) 2014 Mihai Costea. All rights reserved.
//

#include "Stein.h"

unsigned long Stein::gcd(unsigned long a, unsigned long b) {
    int shift;
    
    if (a == 0) return b;
    if (b == 0) return b;
    
    for (shift = 0; ((a | b) & 1) == 0; ++shift) {
        a >>= 1;
        b >>= 1;
    }
    
    while ((a & 1) == 0)
        a >>= 1;
    
    do {
        while ((b & 1) == 0)
            b >>= 1;
        if (a > b) {
            unsigned long t = b; b = a; a = t;
        }
        b = b - a;
    } while (b != 0);
    
    return a << shift;
}