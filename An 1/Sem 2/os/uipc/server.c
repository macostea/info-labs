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
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <string.h>

#include "common.h"

int main(){
    int mq;
    struct m_msg m;

    mq = msgget(KEY, IPC_CREAT|IPC_EXCL|00600);
    if (mq < 0){
        perror("Failed to create MQ");
        exit(1);
    }
    
    while(1){
        msgrcv(mq, &m, sizeof(struct m_msg)-sizeof(long), REQ, 0);
        if (!strcmp(m.filename, EXIT_STRING)){
            break;
        }

        int i;
        for (i=0; i<26; i++){
            m.count[i] = 0;
        }

        FILE *handler = fopen(m.filename, "r");
        int character = '1';
        if (handler == NULL){
            m.count[0] = -1;
        } else {
            while (character != EOF){
                character = fgetc(handler);
                if (character >= 'a' && character <= 'z'){
                    m.count[character-'a']++;
                }
            }
        }
        m.msgType = RES; 
        msgsnd(mq, &m, sizeof(struct m_msg)-sizeof(long), 0);
    
    }

    msgctl(mq, IPC_RMID, 0);
    return 0;
}
