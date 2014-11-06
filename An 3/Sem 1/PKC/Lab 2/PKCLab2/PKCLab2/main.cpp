//
//  main.cpp
//  PKCLab2
//
//  Created by Mihai Costea on 05/11/14.
//  Copyright (c) 2014 Mihai Costea. All rights reserved.
//

#include <iostream>
#include <gmpxx.h>
#include <chrono>

#include "Euclid.h"
#include "Stein.h"
#include "Iterative.h"

int main(int argc, const char * argv[]) {
    unsigned long a = 9577852862;
    unsigned long b = 6245332424;
    
    GCDAlgorithm *algorithm = new Stein();
//    GCDAlgorithm *algorithm = new Iterative();
    
    auto start = std::chrono::system_clock::now();
    unsigned long result = algorithm->gcd(a, b);
    auto duration = std::chrono::system_clock::now() - start;
    
    std::cout << result << std::endl << "Duration: " << duration.count() << std::endl;
    
    delete algorithm;
    
    /*
     Euclid
     */
    
//    Euclid *euclid = new Euclid();
//    mpz_t a, b, result;
//    
//    mpz_init_set_str(a, "556865944325412521512590217589127581297521897512", 10);
//    mpz_init_set_str(b, "3495454252509127509127558912759821651289765127856218951289751289759812", 10);
//    mpz_init(result);
//    
//    gmp_printf("a=%Zd\n", a);
//    gmp_printf("b=%Zd\n", b);
//    
//    auto start = std::chrono::system_clock::now();
//    euclid->gcd(a, b, result);
//    auto duration = std::chrono::system_clock::now() - start;
//    
//    
//    gmp_printf("%Zd, duration: %ld\n", result, duration.count());
//    
//    delete euclid;
    
    
    return 0;
}

