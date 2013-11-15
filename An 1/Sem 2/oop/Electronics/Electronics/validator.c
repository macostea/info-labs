//
//  validator.c
//  Electronics
//
//  Created by Mihai Costea on 19/3/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <stdio.h>

#include "validator.h"

int validateProduct(Product *product){
    if ((product->date>31) || (product->date<1)){
        return -1;
    }
    return 0;
}