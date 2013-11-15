#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include "medication.h"
#include "../utils/utils.h"


void initMedication(Medication* m,int code, char* name, int concentration, int quantity)
{
	m->code=code;
	m->name=copyString(name);
	m->name=0;
	m->concentration=concentration;
	m->quantity=quantity;
}

int getCode(Medication* m)
{

	//return s->id; nu e ok, se poate modifica id-l in afara (se returneaza adresa)
	return m->code;

}

char* getName(Medication* m)
{
	//return s->nume; nu e ok
	return copyString(m->name);
}

int getConcentration(Medication* m)
{
	return m->concentration;  //ok, grupa e int - se returneaza valoarea
}

int getQuantity(Medication* m)
{
	return m->quantity;
}


void setName(Medication* m, char* newName)
{
	if (m->name!=0)
	{
		free(m->name);
	}
	m->name=copyString(newName);
}

void setCode(Medication* m, int newCode)
{
	m->code=newCode;
}

void setConcentration(Medication* m, int newConcentration){
	m->concentration=newConcentration;
}

void setQuantity(Medication* m, int newQuantity){
	m->quantity=newQuantity;
}


int cmpMedications(Medication* m1, Medication* m2){
	if ((strcmp(m1->name, m2->name) ==0 ) && (m1->code==m2->code) && (m1->concentration==m2->concentration) && (m1->quantity==m2->quantity))
		return 1;
    return 0;
}

int cmpMedicationCode(Medication* m,int code){
	if(m->code==code)
		return 1;
	return 0;
}

int cmpMedicationName(Medication* m,char* name){
	return strcmp(m->name,name);
}

int cmpMedicationsCocentration(Medication* m1,Medication* m2){
	return m1->concentration-m2->concentration;
}
int cmpMedicationsQuantity(Medication* m1,int quantity){
	return m1->quantity-quantity;
}


Medication* cpyMedication(Medication*m){
	Medication* m1=malloc(sizeof(Medication));
	m1->code=m->code;
	m1->name=copyString(m->name);
	m1->concentration=m->concentration;
	m1->quantity=m->quantity;
	return m1;
}

void delMedication(Medication* m)
{
	free(m->name);
	free(m);
	m=0;
}
