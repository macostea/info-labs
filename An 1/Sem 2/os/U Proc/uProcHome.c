/*
Create a parent process that gets from the command line
n arguments arg1, arg2, ... , argn.
The parent will create  n son processes, each of them
will create a file with the name argi.ZERO with argi bytes 0.
*/

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]){
    int i;
    FILE *fp;
    for (i=1; i<argc; i++){
        int son=fork();
        if (son == 0){
            char *filename = (char*) malloc (20*sizeof(char));
            sprintf(filename, "arg%d.ZERO", i);
            fp = fopen(filename, "w");
            int j;
            for (j=0; j<atoi(argv[i]); j++){
                fprintf(fp, "%c", '\0');
            }
            fclose(fp);
            free(filename);
            exit(0);
        }
    
    }

    wait(0);
    return 0;
}
