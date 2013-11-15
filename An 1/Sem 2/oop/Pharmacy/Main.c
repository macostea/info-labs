/*
 ============================================================================
 Name        : Sem2Final.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */



#include "repository/medicationRepository.h"
#include "controller/MedicationController.h"
#include "ui/consoleUI.h"
#include<stdio.h>

int main() {

	MedicationRepository* repo=initRepository("Medication.txt");
	MedicationController* ctrl=initController(repo);
	ConsoleUI* con=initConsoleUI(ctrl);
	runMenu(con);
	return 0;
}

