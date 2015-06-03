
#include "medicationController.h"
#include <stdlib.h>
MedicationController* initController(MedicationRepository* repo)
{
	MedicationController* ctrl=malloc(sizeof(MedicationController));
	ctrl->repo=repo;
	return ctrl;

}

void destroyController(MedicationController* ctrl){
	if (ctrl!=0)
	{
		destroyRepository(ctrl->repo);
		free(ctrl);
	}
}

void CAddMedication(MedicationController* ctrl, Medication* m){
	addMedication(ctrl->repo, m);
}

void CRemoveMedication(MedicationController* ctrl, int code){
	removeMedication(ctrl->repo, code);
}

Medication** CGetAll(MedicationController* ctrl, int* count){
	return getAll(ctrl->repo, count);
}

void CUpdateMedication(MedicationController* ctrl, Medication* m){
	updateMedication(ctrl->repo, m);
}

Medication** findMedicationByQuantity(MedicationController* ctrl, int* quantity, int* len){
	return findByQuantity(ctrl->repo,quantity,len);
}

Medication** CsortByNameA(MedicationController* ctrl, int*len){
	return sortByNameA(ctrl->repo, len);
}

Medication** CsortByNameD(MedicationController* ctrl, int*len){
	return sortByNameD(ctrl->repo, len);
}

Medication** CsortByQuantityA(MedicationController* ctrl, int*len){
	return sortByQuantityA(ctrl->repo, len);
}

Medication** CsortByQuantityD(MedicationController* ctrl, int*len){
	return sortByQuantityD(ctrl->repo, len);
}
