#include <stdio.h>

void f(int *x, int y) {
  *x = *x + y;
  printf("%d\n", *x);
  return;
}
int main(void) {
  int x = 0;
  f(&x, 3);
  x = x - 2;
  f(&x, 3);
}
