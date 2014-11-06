//
//  Iterative.cpp
//  PKCLab2
//
//  Created by Mihai Costea on 05/11/14.
//  Copyright (c) 2014 Mihai Costea. All rights reserved.
//

#include "Iterative.h"

unsigned long Iterative::gcd(unsigned long a, unsigned long b) {
    unsigned long result = 1;
    
    for (unsigned long i=2; i <= a && i <= b; i++) {
        if (a % i == 0 && b % i == 0) {
            result = i;
        }
    }
    
    return result;
}
