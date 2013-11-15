#include <windows.h>
#include <cstdlib>
#include <cstdio>

#include "sharedMemory.h"

HANDLE hFile;
LPINT hView, aux;

void createSharedMemory() {
	hFile = CreateFileMapping(INVALID_HANDLE_VALUE, NULL, PAGE_READWRITE, 0, 1024*sizeof(int), "Lab2");
	if (hFile == NULL) {
		printf("Unable to create shared memory.");
		exit(1);
	}
	
	hView = (LPINT) MapViewOfFile(hFile, FILE_MAP_ALL_ACCESS, 0, 0, 0);
	if (hView == NULL) {
		printf("Unable to map the memory segment.");
		exit(1);
	}
	
	aux = hView;
}

void openSharedMemory() {
	hFile = OpenFileMapping(FILE_MAP_ALL_ACCESS, FALSE, "Lab2");
	if (hFile == NULL) {
		printf("Unable to open shared memory.");
		exit(1);
	}
	
	hView = (LPINT) MapViewOfFile(hFile, FILE_MAP_ALL_ACCESS, 0, 0, 0);
	if (hView == NULL) {
		printf("Unable to map the memory segment.");
		exit(1);
	}
	
	aux = hView;
}

void destroySharedMemory() {
	if (!UnmapViewOfFile(hView)) {
		printf("Could not unmap memory segment.");
	}
	CloseHandle(hFile);
}

void writeToSharedMemory(int data, int position) {
	aux[position] = data;
}

void readFromSharedMemory(int *data, int position) {
	*data = aux[position];
}