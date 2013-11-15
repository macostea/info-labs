

#include <stdio.h>
#include <stdlib.h>

#include "consoleUI.h"
#include "../domain/medication.h"
#include "../controller/medicationController.h"
#include "../utils/vectorDinamic.h"


ConsoleUI* initConsoleUI(MedicationController* ctrl)
{
	ConsoleUI* con=(ConsoleUI*)malloc(sizeof(ConsoleUI));
	con->ctrl=ctrl;
	return con;
}

void destroyConsoleUI(ConsoleUI* con){
	if(con!=0)
	{
		destroyController(con->ctrl);
		free(con);
	}
}


void getAllMedications(ConsoleUI* ui){
	Medication** m;
	int all;
	m=CGetAll(ui->ctrl, &all);
	int i;
	for(i=0;i<all;i++){
		if (m[i]!=0){
			char* str=writeMedication(m[i]);
			printf("%s",str);
			free(str);
		}
	}
}

void removeExistMedication(ConsoleUI* ui){
	int code;
	printf("Code: ");
	scanf("%d",&code);
	CRemoveMedication(ui->ctrl, code);
}

void updateExistMedication(ConsoleUI* ui){
	int code;
	printf("Code: ");
	scanf("%d",&code);
	Medication* m=readMedicationUpdate(code);
	CUpdateMedication(ui->ctrl, m);
}

void UIfindByQuantity(ConsoleUI* ui){
	int len;
	int a;
	printf("Quantity: ");
	scanf("%4d",&a);
	Medication** found=findMedicationByQuantity(ui->ctrl,&a,&len);
	int i;
	for(i=0;i<len;i++){
		if (found[i]!=0){
			char* str=writeMedication(found[i]);
			printf("%s",str);
			free(str);
		}
	}
	free(found);
}

void sortByNameAsc(ConsoleUI* ui){
	int len;
	Medication** sort=CsortByNameA(ui->ctrl,&len);
	int i;
	for(i=0;i<len;i++){
		if (sort[i]!=0){
			char* str=writeMedication(sort[i]);
			printf("%s",str);
			free(str);
		}
	}
	free(sort);
}

void sortByNameDesc(ConsoleUI* ui){
	int len;
	Medication** sort=CsortByNameD(ui->ctrl,&len);
	int i;
	for(i=0;i<len;i++){
		if (sort[i]!=0){
			char* str=writeMedication(sort[i]);
			printf("%s",str);
			free(str);
		}
	}
	free(sort);
}

void sortByQuantityAsc(ConsoleUI* ui){
	int len;
	Medication** sort=CsortByQuantityA(ui->ctrl,&len);
	int i;
	for(i=0;i<len;i++){
		if (sort[i]!=0){
			char* str=writeMedication(sort[i]);
			printf("%s",str);
			free(str);
		}
	}
	free(sort);
}

void sortByQuantityDesc(ConsoleUI* ui){
	int len;
	Medication** sort=CsortByQuantityD(ui->ctrl,&len);
	int i;
	for(i=0;i<len;i++){
		if (sort[i]!=0){
			char* str=writeMedication(sort[i]);
			printf("%s",str);
			free(str);
		}
	}
	free(sort);
}


void addNewMedication(ConsoleUI* ui){
		Medication* m;
		m=readMedication();
		CAddMedication(ui->ctrl, m);
}

void runMenu(ConsoleUI* con)
{
	setbuf(stdout,NULL);
	int option=0;
	int o;
	while (1)
	{
		printf("\n");
		printf("Pharmacy Management");
		printf("\n");
		printf("\n ----------------------------------------------");
		printf("\n 1. Add medication");
		printf("\n 2. Medication list");
		printf("\n 3. Delete medication");
		printf("\n 4. Update medication");
		printf("\n 5. List of medications sorted by name: ");
		printf("\n         1 - ascending");
		printf("\n         2 - descending");
		printf("\n 6. List of medications sorted by quantity: ");
		printf("\n         1 - ascending");
		printf("\n         2 - descending");
		printf("\n 7. List of medications with quantity less than: ");
		printf("\n 0. Exit ");
		printf("\n ----------------------------------------------");
		printf("\n");
		printf("\n Option = ");
		scanf("%d",&option);
		if (option==1) addNewMedication(con);
		if (option==2) getAllMedications(con);
		if (option==3) removeExistMedication(con);
		if (option==4) updateExistMedication(con);
		if (option==5) {
			printf("			> ");
			scanf("%d",&o);
			if(o==1){
				sortByNameAsc(con);
			}
			if(o==2){
				sortByNameDesc(con);
			}
		}
		if(option==6) {
			printf("			> ");
			scanf("%d",&o);
			if(o==1){
				sortByQuantityAsc(con);
			}
			if(o==2){
				sortByQuantityDesc(con);
			}
		}
		if(option==7)UIfindByQuantity(con);
		if (option==0) {
			printf(" ______________________________________________");
			return;
		}
	}
}
