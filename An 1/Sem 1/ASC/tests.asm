assume cs:code, ds:data
data segment

string dd 1234A678h, 12345678h, 1AC3B47Dh, 0FEDC9876h
stringSize dw $-string
temp dw ?
rez dw ?
sum db 0
cat db ?
printsum db ?


patru db 4
zece db 10
newline db 10,13,'$'



data ends

code segment

findmax proc
	mov temp, cx ;copy cx
	mov ax, stringSize
	sub ax, cx ;we work with string indexes from 0 to stringSize
	mul patru  ;we work on bytes so we must multiply the indexes by 4 for double word
	mov bx, ax ;use bx for the indirect addressing mode
	
	mov ax, 0 ;cleanup
	
	mov cx, 4 ;we check each byte of the string
	
	max:
		mov di, cx ;use di for indirect addressing mode
		sub di, 1 ;the loop must run 4 times but we can only add a maximum of 3 to get the last byte
		cmp al, byte ptr string[bx] + [di]
		jb newmax ;if al is below the byte then we have a new maximum
		loop max
		
	jcxz over
		
	newmax:
		mov al, byte ptr string[bx] + [di] ;make al the new maximum
		mov rez, di ;keep the position of the byte
		loop max
		
	over:
		add sum, al ;add the bytes
		mov cx, temp ;reinitialise cx with its old value (before the proc call)
		ret
	
findmax endp
		

start:
	mov ax, data
	mov ds, ax
	mov es, ax
	
	mov ax, stringSize
	div patru ;stringSize is represented as a number of bytes but we need a number of double words
	cbw
	mov stringSize, ax
	
	mov cx, ax
	
	check:
		call findmax
		mov dx, 4
		sub dx, rez ;rez is not the "rank" of the byte but rather a position which can also be 0... ranks start from 1
		add dl, '0'  ;prepare for printing on the screen
		mov ah, 02h
		int 21h
		mov ax, 0
		loop check
		
	mov dx, offset newline  ;print a new line character and a return character
	mov ah, 09h
	int 21h
		
	mov di, offset printsum  ;assemble a string to print the sum on the screen 
	mov al, sum
	cmp al, 0
	jge pozitiv ;check if the number if positive or negative
	
	mov dl, '-'  ;if it is negative then print a minus
	mov ah, 02h
	int 21h
	
	mov al, sum
	
	neg al  ;and make it positive
	
	pozitiv:
	cbw
	computesum:
		div zece
		cmp ah, 0
		je zero ;when the remainder is zero we have used all the digits
		mov cat, al
		mov al, ah
		add al, '0'
		stosb
		mov al, cat
		cbw
		jmp computesum
		
	zero:
		mov al, '$'
		stosb
		mov dx, offset printsum
		mov ah, 09h
		int 21h
	
	mov ax, 4c00h
	int 21h
code ends
end start
