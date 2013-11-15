;2. Se dau douã siruri de octeti s1 si s2. Dacã sirul s1 este inclus în s2 atunci se va
;reþine în variabila rez pozitia din sirul s2 de unde începe sirul s1,
;altfel rez va contine FFh. 

assume cs:code, ds:data
data segment

s1 db 'fgh'
dims1 dw $-s1-1
s2 db 'asfdfghjkli'
dims2 dw $-s2-1
rez dw 0FFh

data ends

code segment
start:
mov ax, data
mov ds, ax

mov si, offset s2
mov cx, dims2

checks2:
	lodsb
	cmp al, s1[0]
	je firstIsEqual

loop checks2

jmp stop

firstIsEqual:
	mov dx, cx
	mov cx, dims1
	mov bx, 1
	mov rez, si
	sub rez, offset s2
	checks1:
		lodsb
		cmp al, s1[bx]
		jne notGood
		add bx, 1
		
	loop checks1
	jmp stop
	
notGood:
	mov cx, dx
	add rez, offset s2
	mov si, rez
	mov rez, 0FFh
	loop checks2
		
stop:
mov ax, 4c00h
int 21h

code ends
end start