/*
It is required a server and one or more clients.
Each client sends to the server a name of a file.
The server reads the content of the file with the 
filename received from client and returns to the client
the frequencies of 26 lowercase chars [a-z] from the file
or an error message if the file does not exist on the server.
*/



#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>


int main(){
    int count[26] = {0};
    int pc[2], cp[2];
    char filename[20];

    pipe(pc);
    pipe(cp);
    
    int child = fork();

    if (child == 0){
        int occourance[26];
        char readFilename[20]={'\0'};
        
        scanf("%s", readFilename);
        write(cp[1], readFilename, 20*sizeof(char));
        int i;
        read(pc[0], &occourance[0], sizeof(int));
        if (occourance[0] == -1){
            printf("File not found!\n");
            exit(0);
        }else{
            printf("%c -> %d\n", 'a', occourance[0]);
            for (i=1; i<26; i++){
                read(pc[0], &occourance[i], sizeof(int));
                printf("%c -> %d\n", i+'a', occourance[i]);
            }
        }
                

        close(cp[0]);
        close(cp[1]);
        close(pc[0]);
        close(pc[1]);
    }else{
        read(cp[0], filename, 20*sizeof(char));
        FILE *handler = fopen(filename, "r");
        int character = '1';
        int error = -1;
        if (handler == NULL){
            write(pc[1], &error, sizeof(int));
        }else{
            while (character != EOF){
                character = fgetc(handler);
                if (character >= 'a' && character <= 'z'){
                    count[character-'a']++;
                }
            }
            int j;
            for (j=0; j<26; j++){
                write(pc[1], &count[j], sizeof(int));
            }
        }
        fclose(handler);
        close(cp[0]);
        close(cp[1]);
        close(pc[0]);
        close(pc[1]);
        
    }

    return 0;
}
