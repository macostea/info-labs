#include <windows.h>
#include <cstdio>

#define BUFSIZE 512

int main() {
	HANDLE hPipe = INVALID_HANDLE_VALUE;
	char pipeName[] = "\\\\.\\pipe\\lab1";
	int sum = 0;
	int readNumber = 0;
	
	hPipe = CreateNamedPipe(
		pipeName,
		PIPE_ACCESS_DUPLEX,
		PIPE_TYPE_MESSAGE | PIPE_WAIT,
		PIPE_UNLIMITED_INSTANCES,
		BUFSIZE,
		BUFSIZE,
		0,
		NULL);
	
	if (hPipe == INVALID_HANDLE_VALUE) {
		printf("CreateNamedPipe failed with error: %d\n", GetLastError());
		return -1;
	}
	
	ConnectNamedPipe(hPipe, NULL);
	
	while (readNumber >= 0) {
		ReadFile(
			hPipe,
			&readNumber,
			sizeof(int),
			NULL,
			NULL);
		
		if (readNumber >= 0) {
			sum += readNumber;
		}
	}
	
	WriteFile(
		hPipe,
		&sum,
		sizeof(int),
		NULL,
		NULL);
	
	CloseHandle(hPipe);
	
	return 0;
}