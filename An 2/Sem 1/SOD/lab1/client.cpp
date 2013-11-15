#include <windows.h>
#include <cstdio>

int main() {
	int inputNumber = 0;
	HANDLE hPipe;
	char pipeName[] = "\\\\.\\pipe\\lab1";
	DWORD dwMode;
	int result = 0;
	hPipe = CreateFile(
		pipeName,
		GENERIC_READ | GENERIC_WRITE,
		0,
		NULL,
		OPEN_EXISTING,
		0,
		NULL);
	dwMode = PIPE_READMODE_MESSAGE;
	SetNamedPipeHandleState(
		hPipe,
		&dwMode,
		NULL,
		NULL);
	while (inputNumber >= 0) {
		scanf("%d", &inputNumber);
		WriteFile(
			hPipe,
			&inputNumber,
			sizeof(int),
			NULL,
			NULL);
	}
	
	ReadFile(
		hPipe,
		&result,
		sizeof(int),
		NULL,
		NULL);

	printf("Received from server: %d", result);
	CloseHandle(hPipe);
	
	return 0;
}