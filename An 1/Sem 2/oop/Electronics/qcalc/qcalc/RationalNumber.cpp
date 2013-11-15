//
//  rationalNumber.cpp
//  qcalc
//
//  Created by Mihai Costea on 21/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "RationalNumber.h"


RationalNumber::RationalNumber(int numerator, int denominator){
    RationalNumber::numerator = numerator;
    RationalNumber::denominator = denominator;
}

RationalNumber::RationalNumber(){
    RationalNumber::numerator = 0;
    RationalNumber::denominator = 0;
}

int RationalNumber::getNumerator(){
    return RationalNumber::numerator;
}

int RationalNumber::getDenominator(){
    return RationalNumber::denominator;
}

void RationalNumber::setNumerator(int x){
    RationalNumber::numerator = x;
}

void RationalNumber::setDenominator(int x){
    RationalNumber::denominator = x;
}

bool RationalNumber::operator==(RationalNumber qNr){
    if (numerator == qNr.numerator && denominator == qNr.denominator){
        return true;
    }
    else {
        return false;
        
    }
}

RationalNumber RationalNumber::operator+(RationalNumber& qNr){
    RationalNumber temp;
    int gcd = Utils::gcd(denominator, qNr.denominator);
    int factor = qNr.denominator/gcd;
    
    temp.denominator = denominator * factor;
    temp.numerator = numerator * factor;
    
    factor = denominator/gcd;
    
    temp.numerator+=qNr.numerator * factor;
    
    Utils::factorize(&temp);
    
    return temp;
}

RationalNumber RationalNumber::operator-(RationalNumber& qNr){
    RationalNumber temp;
    int gcd = Utils::gcd(denominator, qNr.denominator);
    int factor = qNr.denominator/gcd;
    
    temp.denominator = denominator * factor;
    temp.numerator = numerator * factor;
    
    factor = denominator/gcd;
    
    temp.numerator-=qNr.numerator * factor;
    
    return temp;
}

RationalNumber RationalNumber::operator*(RationalNumber& qNr){
    RationalNumber temp;
    
    temp.denominator = denominator * qNr.denominator;
    temp.numerator = numerator * qNr.numerator;
    
    return temp;
}

RationalNumber RationalNumber::operator/(RationalNumber& qNr){
    RationalNumber temp;
    
    temp.denominator = denominator / qNr.denominator;
    temp.numerator = numerator / qNr.numerator;
    
    return temp;
}

std::ostream& operator<<(std::ostream& s, RationalNumber qNr){
    s << qNr.numerator << "/" << qNr.denominator << "\n";
    return s;
}
