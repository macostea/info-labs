//
//  Euclid.h
//  PKCLab2
//
//  Created by Mihai Costea on 05/11/14.
//  Copyright (c) 2014 Mihai Costea. All rights reserved.
//

#ifndef __PKCLab2__Euclid__
#define __PKCLab2__Euclid__

#include <stdio.h>
#include <gmpxx.h>

class Euclid{
    
    
public:
    void gcd(mpz_t a, mpz_t b, mpz_t result);
    
};

#endif /* defined(__PKCLab2__Euclid__) */
