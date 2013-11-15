assume cs: code, ds: data
data segment

a dw 1234h, 5678h

data ends

code segment
start:
	mov ax, data
	mov ds, ax

	les bx, dword ptr a
	xchg bx, es
	
	gata:
		mov ax, 4c00h
		int 21h
	

code ends
end start