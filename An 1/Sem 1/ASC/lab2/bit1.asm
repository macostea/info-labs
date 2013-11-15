assume cs:code, ds:data
data segment
a dw 1100111100110001b
b dw 0010111000101111b
c dw ?
data ends

code segment
start:
mov ax, data
mov ds, ax

mov bx, 0

mov ax, a
and ax, 0000001110000000b
mov cl, 7
ror ax, cl
mov bx, ax

mov ax, b
and ax, 0001111000000000b
mov cl, 6
ror ax, cl
or bx, ax

and bx, 0000000001111111b

mov c, bx

mov ax, 4c00h
int 21h

code ends
end start