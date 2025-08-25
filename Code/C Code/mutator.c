#include <stdio.h>

int x = 4;
int mutator(int* x, int *y);
int main() {
    int y = 5;
    x = mutator(&y,&x);
    printf("%d %d\n",x,y); 
    x = mutator(&y,&x);
    printf("%d %d\n",x,y); 
}
int mutator(int *x, int *y){
    *y = *y + *x;
    return *y;
    
}
