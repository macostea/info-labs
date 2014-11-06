//
//  Stein.h
//  PKCLab2
//
//  Created by Mihai Costea on 05/11/14.
//  Copyright (c) 2014 Mihai Costea. All rights reserved.
//

#ifndef __PKCLab2__Stein__
#define __PKCLab2__Stein__

#include <stdio.h>
#include "GCDAlgorithm.h"

class Stein: public GCDAlgorithm {
    
public:
    virtual unsigned long gcd(unsigned long a, unsigned long b);
};

#endif /* defined(__PKCLab2__Stein__) */
