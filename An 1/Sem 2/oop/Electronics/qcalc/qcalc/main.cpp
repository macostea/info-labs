//
//  main.cpp
//  qcalc
//
//  Created by Mihai Costea on 21/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "RationalNumber.h"

#include <iostream>

int main(int argc, const char * argv[])
{

    // insert code here...
    int x1, y1, x2, y2;
    std::cin >> x1;
    std::cin >> y1;
    std::cin >> x2;
    std::cin >> y2;
    
    RationalNumber q1 = RationalNumber(x1, y1), q2 = RationalNumber(x2, y2);
    std::cout << (q1 == q2) << "\n";
    std::cout << (q1+q2);
    std::cout << (q1-q2);
    std::cout << (q1*q2);
    std::cout << (q1/q2);
    
    return 0;
}

