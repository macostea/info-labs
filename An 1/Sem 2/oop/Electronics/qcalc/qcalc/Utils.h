//
//  Utils.h
//  qcalc
//
//  Created by Mihai Costea on 21/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#ifndef __qcalc__Utils__
#define __qcalc__Utils__

#include <iostream>
#include "RationalNumber.h"

class Utils{
public:
static int gcd(int, int);
static RationalNumber factorize(RationalNumber* qNr);
};

#endif /* defined(__qcalc__Utils__) */
