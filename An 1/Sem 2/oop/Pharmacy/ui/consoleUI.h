

#ifndef CONSOLEUI_H_
#define CONSOLEUI_H_

#include "../controller/MedicationController.h"

typedef struct{
	MedicationController* ctrl;
}ConsoleUI;

ConsoleUI* initConsoleUI(MedicationController* ctrl);

void addNewMedication(ConsoleUI* ui);
void runMenu(ConsoleUI* ui);
void destroyConsoleUI(ConsoleUI* ui);

void getAllMedications(ConsoleUI* ui);
void removeExistMedication(ConsoleUI* ui);
void updateExistMedication(ConsoleUI* ui);
void UIfindByQuantity(ConsoleUI* ui);
void sortByNameAsc(ConsoleUI* ui);
void sortByNameDesc(ConsoleUI* ui);
void sortByQuantityAsc(ConsoleUI* ui);
void sortByQuantityDesc(ConsoleUI* ui);


#endif /* CONSOLEUI_H_ */
