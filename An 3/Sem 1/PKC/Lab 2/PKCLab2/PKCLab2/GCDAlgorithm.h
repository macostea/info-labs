//
//  GCDAlgorithm.h
//  PKCLab2
//
//  Created by Mihai Costea on 05/11/14.
//  Copyright (c) 2014 Mihai Costea. All rights reserved.
//

#ifndef PKCLab2_GCDAlgorithm_h
#define PKCLab2_GCDAlgorithm_h

class GCDAlgorithm {
    
    
public:
    virtual unsigned long gcd(unsigned long a, unsigned long b) = 0;
    
    virtual ~GCDAlgorithm(){};
};

#endif
