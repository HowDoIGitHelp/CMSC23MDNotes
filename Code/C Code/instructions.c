#include <stdio.h>

int main(){
    int x;
    scanf("%d",&x);
    while(x > 7){
        x = x - 7;
    }
    printf("Answer: %d\n",x);
}