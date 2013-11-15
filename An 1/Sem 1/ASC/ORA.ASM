assume cs:code, ds:data
data segment

zece db 10
am db 0

data ends

code segment
start:
mov ax, data
mov ds, ax

	

mov ah, 2ch
int 21h
;CH hours
;CL minutes

cmp ch, 12
js isam
jmp dupa


isam:
	mov am, 1
	
dupa:

mov al, ch
	cbw
	div zece

	mov bl, ah

	mov dl, al
	add dl, '0'
	mov ah, 02h
	int 21h

	mov dl, bl
	add dl, '0'
	int 21h

mov dl, ':'
int 21h

mov al, cl
	cbw
	div zece

	mov bl, ah

	mov dl, al
	add dl, '0'
	mov ah, 02h
	int 21h

	mov dl, bl
	add dl, '0'
	int 21h
	
mov ah, 02h
mov dl, ' '
int 21h

cmp am, 1
je afiseazaA
mov ah, 02h
mov dl, 'P'
int 21h
jmp afiseazaM

afiseazaA:
mov dl, 'A'
int 21h

afiseazaM:
mov dl, 'M'
int 21h

mov ax, 4c00h
int 21h
code ends
end start