assume cs: code, ds: data

data segment
data ends

code segment
start:

mov ax, data
mov ds, ax

MOV ax,-7

MOV bl,2

DIV bl


mov ax, 4c00h
int 21h

code ends
end start