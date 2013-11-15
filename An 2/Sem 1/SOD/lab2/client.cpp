#include <windows.h>
#include <cstdio>

#include "sharedMemory.h"

int main() {
	openSharedMemory();
	int position = -1;
	DWORD dwSemaphoreWait;
	
	HANDLE semaphore = OpenSemaphore(EVENT_ALL_ACCESS, TRUE, "semaphore");
	if (!ReleaseSemaphore(semaphore, 1, NULL)) {
		printf("Release semaphore error %d\n", GetLastError());
	}
	dwSemaphoreWait = WaitForSingleObject(semaphore, INFINITE);
	readFromSharedMemory(&position, 0);
	int inputNumber = 0, result = 0;
	
	char semaphoreName[100];
	sprintf(semaphoreName, "subprocesssemaphore%i", position);
	HANDLE request = NULL;
	while (request == NULL) {
		request = OpenSemaphore(EVENT_ALL_ACCESS, TRUE, semaphoreName);
	}
	while (inputNumber >= 0) {
		scanf("%d", &inputNumber);
		writeToSharedMemory(inputNumber, position);
		if (!ReleaseSemaphore(request, 1, NULL)) {
			printf("Release semaphore error %d\n", GetLastError());
		}
	}
	
	WaitForSingleObject(request, INFINITE);
	readFromSharedMemory(&result, position);
	printf("Got back: %i", result);
	
	return 0;
}