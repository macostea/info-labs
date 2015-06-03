



#ifndef MEDICATIONCONTROLLER_H_
#define MEDICATIONCONTROLLER_H_

#include "../repository/medicationRepository.h"

typedef struct{
	MedicationRepository* repo;

}MedicationController;

MedicationController* initController(MedicationRepository* repo);
void destroyController(MedicationController* ctrl);
void CAddMedication(MedicationController* ctrl, Medication* m);
void CRemoveMedication(MedicationController* ctrl, int code);
Medication** CGetAll(MedicationController* ctrl, int* count);
void CUpdateMedication(MedicationController* ctrl, Medication* m);
Medication** findMedicationByQuantity(MedicationController* ctrl, int* quantity, int*len);
Medication** CsortByNameA(MedicationController* ctrl, int*len);
Medication** CsortByNameD(MedicationController* ctrl, int*len);
Medication** CsortByQuantityA(MedicationController* ctrl, int*len);
Medication** CsortByQuantityD(MedicationController* ctrl, int*len);


#endif /* MEDICATIONCONTROLLER_H_ */
