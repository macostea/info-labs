#include <windows.h>
#include <cstdio>
#include <csignal>

#include "sharedMemory.h"

PROCESS_INFORMATION pi[100];
HANDLE semaphore;
int numberOfChildren;

void int_handler(int x) {
	for (int it=0; it<numberOfChildren; it++) {
		WaitForSingleObject(pi[it].hProcess, INFINITE);
	}
	destroySharedMemory();
}

int main() {
	signal(SIGINT, int_handler);
	int sum = 0;
	int readNumber = 0;
	char command[1000];
	
	strcpy(command, "serverSubprocess.exe");
	
	semaphore = CreateSemaphore(NULL, 0, 1000, "semaphore");
	DWORD dwSemaphoreWait;
	createSharedMemory();
	
	STARTUPINFO si = { sizeof(si) };
	numberOfChildren=0;
	while (true) {
		dwSemaphoreWait = WaitForSingleObject(semaphore, INFINITE);
		 //signals that a client is connected
			writeToSharedMemory(++numberOfChildren, 0);
			sprintf(command, "serverSubprocess.exe %i", numberOfChildren);
			BOOL b = CreateProcess("C:\\Developer\\SOD\\lab2\\serverSubprocess.exe", command, NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi[numberOfChildren]);

			if (!ReleaseSemaphore(semaphore, 1, NULL)) {
				printf("Release semaphore error %d\n", GetLastError());
			}
		
	}
	
	CloseHandle(semaphore);
	destroySharedMemory();
	return 0;
}