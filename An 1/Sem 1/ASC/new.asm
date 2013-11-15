ASSUME cs: code, ds:data 
data SEGMENT 

a db 7
b db 9
data ENDS 

code SEGMENT 
start: 
mov ax,data 
mov ds,ax 

mov al,a
add al,b

mov ax,4C00h 
int 21h 
code ENDS 
END start