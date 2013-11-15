assume cs:code, ds:data
data segment

a dw 3
b dw 4
c dw 5
d dw 2
e dw 1
f db 3
g db 1
h db 1

rezultat db ?


data ends

code segment
start:

mov ax, data
mov ds, ax

mov ax, b
imul c

mov bx, ax

add bx, a
sub bx, d

mov ax, bx

idiv f

mov bl, al

add bl, h

mov ax, bx

idiv g

mov rezultat, al


mov ax, 4c00h
int 21h

code ends
end start