

#ifndef MEDICATION_H_
#define MEDICATION_H_

#include "../utils/vectorDinamic.h"

typedef struct{
	int code;
	char* name;
	int concentration;
	int quantity;
} Medication;


/*
 * intializeaza atributele unui student  (- joaca rol de constructor (__init__ python))
 * prec: id:string, nume:string; grupa:int
 * post: s:Student*, s->id=id, s->nume=nume, s->grupa=grupa
 */
void  initMedication(Medication*m,int code, char* name, int concentration, int quantity);

/*
 * desc: getter student id
 * pre: s:Student
 * post: <- s.id
 */
int getCode(Medication* m);

/*
 * desc: getter student nume
 * pre: s:Student
 * post: <- s.nume
 */
char* getName(Medication* m);


/*
 * desc: getter student grupa
 * pre: s:Student
 * post: <- s.grupa
 */
int getConcentration(Medication* m);


/*
 * desc: setter student nume
 * pre: *s:Student, newName:string
 * post: s->nume = name
 */

int getQuantity(Medication* m);


void setName(Medication* m, char* newName);

void setCode(Medication* m, int newCode);

void setConcentration(Medication* m, int newConcentration);

void setQuantity(Medication* m, int newQuantity);


/*
 * desc: compara doi studenti
 * prec: s1,s2:Student
 * post: 1, daca s1=s2
 * 		0. altfel
 */
int cmpMedications(Medication* m1, Medication* m2);
Medication* cpyMedication(Medication*);
void delMedication(Medication* m);

int cmpMedicationCode(Medication* m1,int code);
int cmpMedicationName(Medication* m1,char* name);
int cmpMedicationsConcentration(Medication* m1,Medication* m2);
int cmpMedicationsQuantity(Medication* m1,int quantity);




#endif /* MEDICATION_H_ */
