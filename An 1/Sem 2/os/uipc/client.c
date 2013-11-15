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
    mq = msgget(KEY, 0);
    if (mq < 0){
        perror("Failed to get MQ");
        exit(1);
    }

    while (1){
        scanf ("%s", m.filename);
        m.msgType = REQ;
        msgsnd(mq, &m, sizeof(struct m_msg)-sizeof(long), 0);
        if (!strcmp(m.filename, EXIT_STRING)){
            break;
        }
        
        msgrcv(mq, &m, sizeof(struct m_msg)-sizeof(long), RES, 0);
        
        if (m.count[0] == -1){
            printf("File not found!\n");
        } else {
            int i;
            for (i=0; i<26; i++){
                printf("%c - > %d\n", i+'a', m.count[i]);
            }
        }
    }

    return 0;
}
