#include <windows.h>
#include <cstdlib>
#include <cstdio>

#include "sharedMemory.h"

int main(int argc, char* argv[]) {
	int position = atoi(argv[1]);
	char semaphoreName[100];
	sprintf(semaphoreName, "subprocesssemaphore%i", position);
	
	HANDLE request = CreateSemaphore(NULL, 0, 1000, semaphoreName);
	
	openSharedMemory();
	
	int sum = 0, receivedNumber = 0;
	while (receivedNumber >= 0) {
		WaitForSingleObject(request, INFINITE);
		readFromSharedMemory(&receivedNumber, position);
		if (receivedNumber >= 0) {
			sum += receivedNumber;
		} else {
			break;
		}
	}
	
	writeToSharedMemory(sum, position);
	if (!ReleaseSemaphore(request, 1, NULL)) {
		printf("Release semaphore error %d\n", GetLastError());
	}
	
	CloseHandle(request);
	
	return 0;
}