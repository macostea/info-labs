//
//  Euclid.cpp
//  PKCLab2
//
//  Created by Mihai Costea on 05/11/14.
//  Copyright (c) 2014 Mihai Costea. All rights reserved.
//

#include "Euclid.h"

void Euclid::gcd(mpz_t a, mpz_t b, mpz_t result) {
    mpz_t zero;
    mpz_init_set_str(zero, "0", 10);
    
    
    while (mpz_cmp(b, zero) > 0) {
        mpz_mod(result, a, b);
        mpz_set(a, b);
        mpz_set(b, result);
    }

    mpz_set(result, a);
}
