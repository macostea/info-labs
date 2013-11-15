assume cs:code, ds:data

data segment
x dw ?
data ends

code segment
start:
mov ax, data
mov ds, ax

mov ax, -7
mov bl, 2
idiv bl

mov x, ax

mov ax, 4c00h
int 21h

code ends
end start