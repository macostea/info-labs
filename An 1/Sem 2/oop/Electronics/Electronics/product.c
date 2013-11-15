/*
 * product.c
 *
 *  Created on: Mar 5, 2013
 *      Author: mihai
 */

#include <string.h>

#include "product.h"


Product initProduct(int id, char* type, char* model, char* manufacturer, int price, int date, int quantity){
    Product product;
    product.id = id;
    strcpy(product.type, type);
    strcpy(product.model, model);
    strcpy(product.manufacturer, manufacturer);
    product.price = price;
    product.date = date;
    product.quantity = quantity;
    
    return product;
}