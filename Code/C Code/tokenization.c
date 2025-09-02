#include <stdio.h>
#include <string.h>

void printTokens(char *str) {
  char *token;
  token = strtok(str, ".");
  while (token != NULL) {
    printf("%s\n", token);
    token = strtok(NULL, ".");
  }
  printf("\n");
}

int main() {
  char str[100] = "hello.everyone.and.world";
  printf("str:%s\n", str);
  printTokens(str);
  printf("str:%s\n", str);
  return 0;
}