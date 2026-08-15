# Lab Exercise 1 (Imperative Programming Review)

Create a C library that contains the following functions.

-  `void* log_variable(char* var_name, void* var_address, VAR_TYPE var_type)`

    This function prints the current value of variable `var_name`.
    The address of the value must be passed as `var_address`.
    The datatype will be indicated by an enum called `VAR_TYPE` argument called `var_type`.

    This function should print one log line according to the following format.

    ```
    <varname>: <value>
    ```

    There should be a newline after the logline.
