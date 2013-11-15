/*
 * main.c
 *
 *  Created on: Mar 5, 2013
 *      Author: mihai
 */

#include <string.h>

#include "product.h"
#include "ui.h"
#include "repository.h"
#include "test_repository.h"

int main(){
	test_repo();
	char filename[30];
	strcpy(filename, "electronics.dat");
	initRepo(filename);
	run();
	return 0;
}
