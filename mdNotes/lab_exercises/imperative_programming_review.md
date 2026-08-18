# Lab Exercise 1 (Imperative Programming Review)

Create a C library that contains the following functions.

[Link to uvec vpl](https://uvec.upcebu.edu.ph/mod/vpl/view.php?id=53292)

-  `void* log_variable(char* label, void* var_address, VAR_TYPE var_type)`

    This function prints the current value of the variable at the address `var_address`
    The datatype will be indicated by an enum `VAR_TYPE` called `var_type`.
    The argument `label` can be any string.

    This function should print one log line according to the following format.

    ```
    <label>: <var_adress value>
    ```

    There should be a newline after the logline.

- `void* log_array(char* label, void* arr_address, int array_size, VAR_TYPE var_type)`

    This function prints the current value of the array at the address `arr_address`
    The datatype will be indicated by the enum `VAR_TYPE` called `var_type`.
    The argument `label` can be any string.
    The argument `array_size` corresponds to the number of elements in the array.
    The elements should be printed in one line, separated by spaces.
    
    For example, given a three element array, the function should print one log line according to the following format.

    ```
    <label>: <elem1> <elem2> <elem3>
    ```

    If the array passed is a `char` array or string, there should be no spaces between the characters.

    ```
    <label>: <string>
    ```

Here is an example of the function being used in primality testing.

```c
#include <stdio.h>
#include "logger.h"

void main() {
    int x = 187;
    int prime_flag = 0;
    for (int cf = 2; cf * cf <= x; cf++) {
        log_variable("cf", &cf, INT);
        if (x % cf == 0) {
            log_variable("cf inside if", &cf, INT);
            printf("%d is composite!\n", x);
            prime_flag = 1;
            break;
        }
    }
    if (prime_flag == 0) {
        printf("%d is prime!\n", x);
    }
}
```

This program should print:

```
cf: 2
cf: 3
cf: 4
cf: 5
cf: 6
cf: 7
cf: 8
cf: 9
cf: 10
cf: 11
cf inside if: 11
187 is composite!
```

Here's another example with the logger used on a sorting procedure.

```c
#include <stdio.h>
#include "logger.h"

void main() {
    int size = 7;
    int a[7] = {1, -2, 0, -2, 3, 3, 4};
    for (int i = 0; i < size - 1; i++) {
        int j = i + 1;
        log_variable("i", &i, INT);
        while (a[j] < a[j-1]) {
            int temp = a[j-1];
            a[j-1] = a[j];
            a[j] = temp;
            j--;
        }
        log_array("sort progress", a, size, INT);
    }
}
```

```
i: 0
sort progress: -2 1 0 -2 3 3
i: 1
sort progress: -2 0 1 -2 3 3
i: 2
sort progress: -2 -2 0 1 3 3
i: 3
sort progress: -2 -2 0 1 3 3
i: 4
sort progress: -2 -2 0 1 3 3
i: 5
sort progress: -2 -2 0 1 3 3
```

Another example with strings:

```c
#include <stdio.h>
#include "logger.h"

void printTokens(char *str) {
    char *token;
    token = strtok(str, ".");
    while (token != NULL) {
        printf("%s\n", token);
        token = strtok(NULL, ".");
    }
}

void main() {
    char str[100] = "hello.everyone.and.world";
    log_array("str before tokenization", str, 100, CHAR);
    printTokens(str);
    log_array("str after tokenization", str, 100, CHAR);
}
```

```
str before tokenization: hello.everyone.and.world
hello
everyone
and
world
str after tokenization: hello
```
