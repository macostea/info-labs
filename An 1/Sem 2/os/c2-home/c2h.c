/*
Display, from the files given as arguments, the name of the file that has
the maximum number of words and also this number of the words.
*/

#include <stdio.h>

void removeNegativeNumbers(int* seq, int* size){
    int i;
    for (i=0; i<*size; i++){
        if (seq[i]<0){
            if (i == *size - 1){
                *size = *size - 1;
            }
            else {
                int j;
                for (j=i; j<*size; j++){
                    seq[j] = seq[j+1];
                }
                *size = *size - 1;
                i--;
            }
        }
    }

}



int main(){
    int seq[100];
    int n,i;

    printf("Number of elements: ");
    scanf("%d", &n);
    for (i=0; i<n; i++){
        printf("seq[%d]=", i);
        scanf("%d", &seq[i]);
    }
    removeNegativeNumbers(seq, &n);
    for (i=0; i<n; i++){
        printf("%d, ", seq[i]);
    }
    printf("\n");
    return 0;
}
