# Lab Exercise 1 (Imperative Programming Review)

Create a C library that contains the following functions.

-  `void* log_variable(char* var_name, void* var_address, VAR_TYPE var_type)`

    This function prints the current value of the variable at the address `var_address`
    The datatype will be indicated by an enum called `VAR_TYPE` argument called `var_type`.
    The argument `label` can be any string.

    This function should print one log line according to the following format.

    ```
    <label>: <var_adress value>
    ```

    There should be a newline after the logline.

- `void* log_array(char* array_name, void* arr_address, int array_size, VAR_TYPE var_type)`

    This function prints the current value of the array at the address `var_address`
    The datatype will be indicated by an enum called `VAR_TYPE` argument called `var_type`.
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
