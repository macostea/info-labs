assume cs:code, ds:data
data segment
s db 'mihai'
d db 5 dup (?)
data ends

code segment
start:
mov ax, data
mov ds, ax
mov es, ax
mov cx, 5

mov si, offset s + 4
mov di, offset d


nume:
std
lodsb
cld
stosb
loop nume

mov al, 36
stosb

mov dx, offset d
mov ah, 09h
int 21h

mov ax, 4c00h
int 21h

code ends
end start