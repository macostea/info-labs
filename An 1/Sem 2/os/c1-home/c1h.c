#include <stdio.h>

int isPrime(int x){
    /*
    Function that checks if an int is prime
    x is the int to be checked
    Returns 1 if x is prime, 0 otherwise
    */
    int i;
    if (x==2){
        return 1;
    }
    for (i=2; i<(x/2) + 1; i++){
        if (x%i == 0){
            return 0;
        }
    }
    return 1;
}

int main(){
    int n;
    printf("n=");
    scanf("%d", &n);
    int count=0;
    int number=2;
    while (count!=n){
        if (isPrime(number)){
            printf("%d, ", number);
            count++; //we found a prime number so increase the prime number count
        }
        number++;
    }
    printf("\n");
    return 0;
}
