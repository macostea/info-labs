/*
 * product.h
 *
 *  Created on: Mar 5, 2013
 *      Author: mihai
 */

#ifndef PRODUCT_H_
#define PRODUCT_H_

/*
 * The product struct holds all the information necessary to store products in memory
 */
typedef struct{
	int id;
	char type[30];
	char model[30];
	char manufacturer[30];
	int price;
	int date;
	int quantity;
}Product;

/*
 * This method creates and returns a Product with the neccessary characteristics
 */
Product initProduct(int id, char* type, char* model, char* manufacturer, int price, int date, int quantity);

#endif /* PRODUCT_H_ */
