//
//  Utils.cpp
//  qcalc
//
//  Created by Mihai Costea on 21/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "Utils.h"

int Utils::gcd(int x, int y){
    while (x != y) {
        if (x>y){
            x-=y;
        }
        else{
            y-=x;
        }
    }
    return x;
}

RationalNumber factorize(RationalNumber* qNr){
    RationalNumber temp;
    int gcd = Utils::gcd(qNr->getDenominator(), qNr->getNumerator());
    temp.setDenominator(qNr->getDenominator());
    temp.setNumerator(qNr->getNumerator());
    
    for (int i=1; i<=gcd; i++){
        if (temp.getNumerator() % temp.getDenominator() == 0){
            temp.setNumerator(temp.getNumerator() / i);
            i = 1;
        }
    }
    
    return temp;

}