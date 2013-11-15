/*
 * ui.c
 *
 *  Created on: Mar 5, 2013
 *      Author: mihai
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#include "ui.h"

static char* menu = "(1)Add new product\n(2)Update product price\n(3)Update product quantity\n(4)Delete a product\n(5)Filter\n(6)Sort\n(7)Get all products\n";
static char* separator = "------------------\n";
static char* exitString = "(x)Exit\n";
static char* backString = "(x)Back\n";
static char* exitChar = "x";

static char* filterMenu = "(1)By manufacturer\n(2)By price\n(3)By date\n";
static char* sortMenu = "(1)By price\n(2)By date\n";
static char* sortSubMenu = "(1)Ascending\n(2)Descending\n";

void run(){
	char user_input[2] = "a";

	while (strcmp(user_input, exitChar)){
		printf("%s", menu);
		printf("%s", separator);
		printf("%s", exitString);
		scanf("%s", user_input);

		if (!strcmp(user_input, "1")){
			char *type, *model, *manufacturer;
			type = (char*) malloc(30);
			model = (char*) malloc(30);
			manufacturer = (char*) malloc(30);
			int id, price, quantity, date;
			id = 0; // We don't really use this. The repository takes care of the id. TODO: Stop being lazy and remove id altogether!
			printf("Type: ");
            fpurge(stdin);
            fgets(type, 30, stdin);
            type = strtok(type, "\n");
			printf("Model: ");
            fpurge(stdin);
            fgets(model, 30, stdin);
            model = strtok(model, "\n");
			printf("Manufacturer: ");
            fpurge(stdin);
            fgets(manufacturer, 30, stdin);
            manufacturer = strtok(manufacturer, "\n");
			printf("Price: ");
			int result;
			result = scanf("%d", &price);
			if (!result){
				printf("Price must be an int value!\n");
				continue;
			}
			printf("Date: ");
			scanf("%d", &date);
			printf("Quantity: ");
			result = scanf("%d", &quantity);
			if (!result){
				printf("Quantity must be an int value!\n");
				continue;
			}

			result = addProduct(id, type, model, manufacturer, price, date, quantity);
            if (result == -1){
                printf("Date must be an int between 1 and 31!\n");
            }

			free(type);
			free(model);
			free(manufacturer);

		}

		if (!strcmp(user_input, "2")){
			int id, newPrice;
			printf("Id of the product: ");
			scanf("%d", &id);
			printf("New price: ");
			scanf("%d", &newPrice);
			int result = updatePrice(id, newPrice);
			if (result == 0){
				printf("Price was updated!");
			}
			else{
				printf("Unknown id!");
			}
		}

		if (!strcmp(user_input, "3")){
			int id, newQuantity;
			printf("Id of the product: ");
			scanf("%d", &id);
			printf("New quantity: ");
			scanf("%d", &newQuantity);
			int result = updateQuantity(id, newQuantity);
			if (result == 0){
				printf("Quantity was updated!");
			}
			else{
				printf("Unknown id!");
			}
		}

		if (!strcmp(user_input, "4")){
			int id;
			printf("Id of the product: ");
			scanf("%d", &id);
			int result = deleteProduct(id);
			if (result == 0){
				printf("Product was deleted successfully!");
			}
			else{
				printf("Unknown id!");
			}
		}

		if (!strcmp(user_input, "5")){
			strcpy(user_input, "a");
			while (strcmp(user_input, exitChar)){
				printf("%s", filterMenu);
				printf("%s", separator);
				printf("%s", backString);
				scanf("%s", user_input);
				Products result;
				result.size = 0;
				if (!strcmp(user_input, "1")){
					char manufacturer[30];
					printf("%s", "Manufacturer: ");
					scanf("%s", manufacturer);
					filterByManufacturer(&result, manufacturer);
				}
				if (!strcmp(user_input, "2")){
					int price;
					printf("%s", "Price: ");
					scanf("%d", &price);
					filterByPrice(&result, price);
				}
				if (!strcmp(user_input, "3")){
					int date;
					printf("%s", "Date: ");
					scanf("%d", &date);
					filterByDate(&result, date);
				}

				int i;
				for (i=0; i<result.size; i++){
					printf("%d | ", result.products[i].id);
					printf("%s | ", result.products[i].type);
					printf("%s | ", result.products[i].model);
					printf("%s | ", result.products[i].manufacturer);
					printf("%d | ", result.products[i].price);
					printf("%d | ", result.products[i].date);
					printf("%d\n", result.products[i].quantity);
				}
				if (!strcmp(user_input, exitChar)){
					strcpy(user_input, "a");
					break;
				}
			}
		}

		if (!strcmp(user_input, "6")){
			strcpy(user_input, "a");
			while (strcmp(user_input, exitChar)){
				printf("%s", sortMenu);
				printf("%s", separator);
				printf("%s", backString);
				scanf("%s", user_input);
				Products result;
				result.size = 0;
				if (!strcmp(user_input, "1")){
					while (strcmp(user_input, exitChar)){
						printf("%s", sortSubMenu);
						printf("%s", separator);
						printf("%s", backString);
						scanf("%s", user_input);
						if (!strcmp(user_input, "1")){
							sortByPrice(&result, ASC);
							strcpy(user_input, "a");
							break;
						}
						if (!strcmp(user_input, "2")){
							sortByPrice(&result, DSC);
							strcpy(user_input, "a");
							break;
						}
						if (!strcmp(user_input, exitChar)){
							strcpy(user_input, "a");
							break;
						}
					}
				}
				if (!strcmp(user_input, "2")){
					while (strcmp(user_input, exitChar)){
						printf("%s", sortSubMenu);
						printf("%s", separator);
						printf("%s", backString);
						scanf("%s", user_input);
						if (!strcmp(user_input, "1")){
							sortByDate(&result, ASC);
							strcpy(user_input, "a");
							break;
						}
						if (!strcmp(user_input, "2")){
							sortByDate(&result, DSC);
							strcpy(user_input, "a");
							break;
						}
						if (!strcmp(user_input, exitChar)){
							strcpy(user_input, "a");
							break;
						}
					}
				}

				int i;
				for (i=0; i<result.size; i++){
					printf("%d | ", result.products[i].id);
					printf("%s | ", result.products[i].type);
					printf("%s | ", result.products[i].model);
					printf("%s | ", result.products[i].manufacturer);
					printf("%d | ", result.products[i].price);
					printf("%d | ", result.products[i].date);
					printf("%d\n", result.products[i].quantity);
				}

				if (!strcmp(user_input, exitChar)){
					strcpy(user_input,"a");
					break;
				}
			}

		}

		if (!strcmp(user_input, "7")){
			Products result;
			getAllProducts(&result);
			int i;
			for (i=0; i<result.size; i++){
				printf("%d | ", result.products[i].id);
				printf("%s | ", result.products[i].type);
				printf("%s | ", result.products[i].model);
				printf("%s | ", result.products[i].manufacturer);
				printf("%d | ", result.products[i].price);
				printf("%d | ", result.products[i].date);
				printf("%d\n", result.products[i].quantity);
			}
		}

	}


}
