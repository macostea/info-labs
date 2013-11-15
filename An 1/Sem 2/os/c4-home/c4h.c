/*
Let be a text file, redirected as standard input, each line from it
hava only an integer. Write a program filter that put to standard
output the same lines, but with numbers in hexadecimal representation.
Must use scanf and printf.
*/

#include <stdio.h>


int main(int argc, char* argv[], char* envp[]){
    if (argc != 2){
        printf("%s", "Usage: c4h filename\n");
        return 0;
    }
    
    FILE *fp;
    int line, result;
    fp = fopen(argv[1], "r");
    if (fp == NULL){
        printf("%s", "File not found!\n");
        return 0;
    }
    result = fscanf(fp, "%d", &line);
    while (result != EOF){
        printf("%X\n", line);
        result = fscanf(fp, "%d", &line);
    } 
    fclose(fp);
    return 0;
}
