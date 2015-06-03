

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utils.h"



char* copyString(char* s){
	if (s!=0){
		char* clone=malloc((strlen(s)+1)*sizeof(char));
		strcpy(clone,s);
		return clone;
	}
	else
		return 0;
}


Medication* readMedication(){
	Medication* m=malloc(sizeof(Medication));
	char* name=malloc(20*sizeof(char));
	int code,concentration,quantity;

	printf("Code: ");
	scanf("%d",&code);
	printf("Name: ");
	scanf("%20s",name);
	printf("Concentration: ");
	scanf("%d",&concentration);
	printf("Quantity: ");
	scanf("%d",&quantity);

	initMedication(m,code,name,concentration,quantity);
	free(name);
	return m;
}
//-----------------------------------------------------------------------------------------------------
Medication* readMedicationUpdate(int code){
	Medication* m=malloc(sizeof(Medication));
	char* name=malloc(20*sizeof(char));
	int concentration,quantity;

	printf("Name: ");
	scanf("%20s",name);
	printf("Concentration: ");
	scanf("%d",&concentration);
	printf("Quantity: ");
	scanf("%d",&quantity);

	initMedication(m,code,name,concentration,quantity);
	free(name);
	return m;
}

char* writeMedication(Medication*m){
	char* str=malloc((4+3+20+3+4+3+4+3)*sizeof(char));
	sprintf(str,"%4d | %20s | %4d | %4d\n", m->code, m->name, m->concentration, m->quantity);
	return str;
}
