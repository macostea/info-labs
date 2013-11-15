assume cs:code, ds:data
data segment

data ends

code segment
start:
mov ax,data
mov dx,ax

mov al, 5
sub al, 6

mov ax,4C00h
int 21h
code ends

end start