//
//  rationalNumber.h
//  qcalc
//
//  Created by Mihai Costea on 21/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __qcalc__rationalNumber__
#define __qcalc__rationalNumber__

#include <iostream>

#include "Utils.h"


class RationalNumber {
    int numerator, denominator;
public:
    RationalNumber(int, int);
    RationalNumber();
    bool operator==(RationalNumber);
    int getNumerator();
    int getDenominator();
    void setNumerator(int x);
    void setDenominator(int x);
    RationalNumber operator+(RationalNumber&);
    RationalNumber operator-(RationalNumber&);
    RationalNumber operator*(RationalNumber&);
    RationalNumber operator/(RationalNumber&);
    friend std::ostream& operator<<(std::ostream&, RationalNumber);
};

#endif /* defined(__qcalc__rationalNumber__) */
