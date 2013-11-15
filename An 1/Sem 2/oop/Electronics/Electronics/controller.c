/*
 * controller.c
 *
 *  Created on: Mar 5, 2013
 *      Author: mihai
 */

#include <string.h>

#include "controller.h"

int addProduct(int id,
	char* type,
	char* model,
	char* manufacturer,
	int price,
	int date,
	int quantity){
    
    Product product = initProduct(id, type, model, manufacturer, price, date, quantity);
    if (validateProduct(&product)){
        return -1;
    } else {
        addProductInRepo(product);
        return 0;
    }
}

void getAllProducts(Products *result){
	getAllProductsFromRepo(result);
}

int updatePrice(int id, int price){
	return updatePriceInRepo(id, price);
}

int updateQuantity(int id, int quantity){
	return updateQuantityInRepo(id, quantity);
}

int deleteProduct(int id){
	return deleteProductInRepo(id);
}

void filterByManufacturer(Products* result, char* manufacturer){
	repo_filterByManufacturer(result, manufacturer);
}

void filterByPrice(Products *result ,int price){
	repo_filterByPrice(result, price);
}

void filterByDate(Products *result ,int date){
	repo_filterByDate(result, date);
}

void sortByPrice(Products *result, int direction){
	repo_sortByPrice(result, direction);
}

void sortByDate(Products *result, int direction){
	repo_sortByDate(result, direction);
}
