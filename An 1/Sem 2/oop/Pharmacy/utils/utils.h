

#ifndef UTILS_H_
#define UTILS_H_

#include "../domain/medication.h"

//-----------------------------------------------------------------------------------------------------
/*
 * Create a clone(copy) of the given string
 * prec: s:char*-pointer to the string
 * post: clone:char* -pointer to the created copy
 */
char* copyString(char* s);

//-----------------------------------------------------------------------------------------------------
/*
 * Create a copy of a date type object
 * prec: d:date*-the given date
 * post: clone:date*-pointer to the new created copy
 */


Medication* readMedication();

//-----------------------------------------------------------------------------------------------------
/*
 * Write offer to a string
 * prec: o:offer*- pointer to the offer to be written
 * post: str:char*-string format of the offer
 */
char* writeMedication(Medication*m);

//-----------------------------------------------------------------------------------------------------
Medication* readMedicationUpdate(int code);

#endif /* UTILS_H_ */
