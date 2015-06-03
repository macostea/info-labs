

#ifndef MEDICATIONREPOSITORY_H_
#define MEDICATIONREPOSITORY_H_


#include "../utils/vectorDinamic.h"
#include "../utils/utils.h"
#include "../domain/medication.h"

typedef struct{
	vectorDinamic* medList;
	char* fileName;
} MedicationRepository;



MedicationRepository* initRepository(char* filename);
void destroyRepository(MedicationRepository* repo);
void saveMedications(MedicationRepository* repo);
void loadMedications(MedicationRepository* repo);
Medication** getAll(MedicationRepository* repo, int* count);
void addMedication(MedicationRepository* repo, Medication* m);
void removeMedication(MedicationRepository* repo, int code);
void updateMedication(MedicationRepository* repo, Medication* m);
Medication** findByQuantity(MedicationRepository* repo,int* quantity, int* length);
Medication** sortByNameA(MedicationRepository* repo, int* l);
Medication** sortByNameD(MedicationRepository* repo,int* l);
Medication** sortByQuantityA(MedicationRepository* repo,int* l);
Medication** sortByQuantityD(MedicationRepository* repo,int* l);

#endif /* MEDICATIONREPOSITORY_H_ */
