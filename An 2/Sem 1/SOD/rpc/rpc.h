#ifndef RPC_rpc_h
#define RPC_rpc_h

#include <rpc/rpc.h>
#include <rpc/xdr.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>

#include "utils.h"

#define PROGRAM_EXEC  	((u_long)0x40000000)
#define VERS_EXEC 		((u_long)1)
#define EXEC_RUN    	((u_long)1)


int xdr_str(XDR *xdr, char *s) {
    int i;
    for (i = 0; i <= strlen(s); i++) 
        if (xdr_char(xdr, &s[i]) == 0) return 0;
    return 1;
}

int xdr_message_t(XDR *xdr, message_t *message) {
	if (xdr_float(xdr, &(message->n1)) == 0) return 0;
	if (xdr_float(xdr, &(message->n2)) == 0) return 0;
	if (xdr_float(xdr, &(message->n3)) == 0) return 0;
	
	if (xdr_str(xdr, message->ip) == 0) return 0;
	
	return 1;
}

#endif