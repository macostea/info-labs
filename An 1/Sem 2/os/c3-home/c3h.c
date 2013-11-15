/*
Solve the problem assigned to C2-H.
But instead of static arrays, 
will be use dynamic space allocation for arrays.
At finish, must release all dynamic spaces.
*/

#include <stdio.h>
#include <stdlib.h>

void removeNegativeNumbers(int* seq, int* size){
    int i;
    for (i=(*size)-1; i>=0; i--){
        if (seq[i]<0){
            if (i == *size - 1){
                *size = *size - 1;
            }
            else {
                int j;
                for (j=i; j<(*size)-1; j++){
                    seq[j] = seq[j+1];
                }
                *size = *size - 1;
            }
        }
    }

}



int main(){
    int n,i;
    int *seq;
    printf("Number of elements: ");
    scanf("%d", &n);
    seq = (int*) malloc (n*sizeof(int));
    for (i=0; i<n; i++){
        printf("seq[%d]=", i);
        scanf("%d", &seq[i]);
    }
    removeNegativeNumbers(seq, &n);
    for (i=0; i<n; i++){
        printf("%d, ", seq[i]);
    }
    printf("\n");
    free(seq);
    return 0;
}
