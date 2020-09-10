# Python Introduction

## starting python from the command line

To start python repl on the command line, use the `python` command. Make sure you have the path to python saved in your OS's `PATH` environment variable. Some python distributions have to be started with the specific version specified. For these distributions use the command `python2` or `python3`.

```
> python
Python 3.8.5 (tags/v3.8.5:580fbb0, Jul 20 2020, 15:43:08) [MSC v.1926 32 bit (Intel)] on win32
Type "help", "copyright", "credits" or "license" for more information.
>>>
```

To run a python script (`*.py`), use the same `python` command followed by the path to the script

```
> python script.py
```

## python syntax

Lets start by discussing simple python syntax. Python code looks like this

```python
x = 5
value = 6
print(x + value)
```

The first thing you notice is that python doesn't use semi-colons to separate statements, python finds the semi-colon redundant since programmers separate statements using newlines. Here python interprets three separate statements, `x = 5` and `value = 6`, and `print(x + value)`. The last line is pythons way of printing to an output stream.

## python atomic types

#### Integers

An `int` in python is of course an implementation of an integer in math. There is no limit to the size of a python integer (except for system memory).

```python
9999999999999999999999999999 + 1
```

To  check the type of a python object, you can use the function `type`.

```python
type(9999999999999999999999999999 + 1)
```

```python
int
```

#### Floating-Point Numbers

A python `float` is an implementation of a floating point number. Numbers written in scientific notation are also floating point numbers:

```python
type(4.2)
```

```python
float
```

```python
type(4.2e5)
```

```python
float
```

#### Complex numbers 

Complex numbers exist in python. The complex number $a + bi$ is represented as `a + bj` where `a` and `b` are integer literals.

```python
type(4 + 3j)
```

```python
complex
```

#### Strings

A python `string` is a sequence of characters. Strings can be enclosed by single-quotes or double quotes (but you must pair single-quotes with single-quotes and double-quotes with double quotes). 

```python
type(`This`)
```

```python
string
```

```python
type("That")
```

```python
string
```

#### Boolean

A `bool` in python is either `True` or `False`.

```python
type(True)
```

```python
bool
```

```python
type(False)
```

```python
bool
```

#### Python typing

Python is a type inferred, mostly strongly typed, dynamically checked language.

There are no explicit declarations in python. The first assignment to a python identifier is its declaration. By assigning a value to that identifier python infers the type based on the value assigned to it. Changing the value assigned to  an identifier changes its type as well.

```python
x = 4
print(type(x))
x = 'this'
print(type(x))
```

```python
<class 'int'>
<class 'str'>
```

Python is mostly strongly typed, which means that most type conversions result in a type error.

> **Exercise (No submission but try to do this on your own)**
>
> **Type Coercions**
>
> What do you think would happen if you try to add different types in python? Without actually executing anything, predict the expected results of the following type surveys,
>
> 1. `type(3 + 3.0)`
> 2. `type(3 + '3')`
> 3. `type(3 + True)`
> 4. `type(4/2)`
> 5. `type(4/0)`
>
> After writing the expected results, write the actual results and compare them to your expectations.

Python is dynamically checked, this means that it checks for type safety during runtime. This means that code that cannot be reached is not type checked. Which means that the following results in a type error:

```python
print("this"+True)
```

But the following doesn't:

```python
if 1 == 0:
	print("this"+True)
```

If you choose to do so you can annotate the type of an identifier using the following syntax:

```python
x:int = 3
```

## Iterable types

#### List

A python list, is a vector of any mix of python objects:

```python
type([1,2,3,"4",True,[]])
```

```python
list
```

The length of a list can be found using the built-in function `len` which accepts an iterable type and returns an integer which is the length:

```python
len(l)
```

```python
6
```

Lists can be concatenated using the `+` operator:

```python
[1,2,3]+[4,5]
```

```python
[1, 2, 3, 4, 5]
```

You can check if an element exists in an iterable using the `in` operator:

```python
2 in [1,2,3,"4",True,[]]
```

```python
True
```

List elements are accessed similar to c arrays.

```python
l = [1,2,3,"4",True,[]]
l[2]
```

```python
3
```

Negative indices count from the right

```python
l[-1]
```

```python
[]
```

You can extract a copy of a sublist using the following indexing methods

- `list[n:m]` produces a sublist from index n to m (including the nth element but excluding the mth element).
- `list[:m]` equivalent to `list[0:m]`.
- `list[n:]` equivalent to`list[n:-1]`.

```python
l[1:3]
```

```python
[2,3]
```

> **Exercise (No submission but try to do this on your own) **
>
> **Advanced list slicing**
>
> Play around with lists and test the behavior of the list access operator `::`. What is the result of the expression `list[a::b]` where `list` is a `list` and `a` and `b` are `int`s? What about `list[a::]` and `list[::b]`.

A python list is mutable, you can change the value of a specific element or range:

```python
l[1] = 0
l
```

```python
[1, 0, 3, '4', True, []]
```

You can delete an element on a specific index or range:

```python
del l[2]
l
```

```python
[1, 0, '4', True, []]
```

```python
del l[2:4]
l
```

```python
[1, 0, []]
```

#### Tuples

A python `tuple` is an immutable collection of objects. Tuples are written surrounded by parentheses instead of square brackets. 

```python
t = (1,2,True,5,6)
```

Tuple elements and subtuples can be accessed the same way with lists. 

```python
t[2]
```

```python
True
```

```python
t[1:4]
```

```python
(2, True, 5)
```

Tuples can also be concatenated similar to lists.

```python
(1,2,3) + (5,6)
```

```python
(1, 2, 3, 5, 6)
```

Tuples are immutable so you cannot change the elements of a tuple.

```python
t[1] = 2
```

```python
...
TypeError: 'tuple' object does not support item assignment
```

#### Dictionaries

A python dictionary is a collection of key-value pairs. It is written surrounded by curly braces.  Each pair is written, `<key>:<value>`

```python
d = {"a":1,0:"this","b":True}
```

The elements of a dictionary can be accessed using the keys. Here the value associated to the key "a" is accessed.

```python
d["a"]
```

```python
1
```

Here the value associated to the key `0` is accessed.

```python
d[0]
```

```python
"this"
```

To check if a specific key exists in the dictionary, use the `in` operator in the same way you use it in lists:

```python
"b" in d
```

```
True
```

You can add new entries to the dictionary using the following syntax: Notice how the dictionary has new entries after the second line

```python
print(d)
d["newKey"]="newValue"
print(d)
```

```
{'a': 1, 0: 'this', 'b': True}
{'a': 1, 0: 'this', 'b': True, 'newKey': 'newValue'}
```

Key-value associations are injective meaning, a key can only be associated to exactly one value. If you attempt to "add" an entry for a key that already exists, it will not create a new entry, it will instead overwrite the old value:

```python
print(d)
d["newKey"]="newerValue"
print(d)
```

```
{'a': 1, 0: 'this', 'b': True, 'newKey': 'newValue'}
{'a': 1, 0: 'this', 'b': True, 'newKey': 'newerValue'}
```

To remove entries in the dictionary use `del` in the same way you use it on lists:

```python
del d["a"]
print(d)
```

```
{0: 'this', 'b': True, 'newKey': 'newerValue'}
```



## Selection expressions

Python's if else statements follow this pattern (the else part can be omitted if the execution does not need an alternative). The colon and whitespace are part of the syntax and are mandatory. Every tabulated line is inside the scope of the `if` part or the `else` part.

```python
if boolean:
    truePart
else:
    alternativePart
```

Nesting if else statements in python has a shortcut. The following code:

```python
if False:
    print("won't print")
else:
    if False:
        print("won't print too")
    else:
        print("will print")
```

Can be summarized to this:

```python
if False:
    print("won't print")
elif False:
    print("won't print too")
else:
    print("will print")
```

Python also understands ternary expressions:

```python
x = "this" if True else "That"
print(x)
```

```python
this
```

The value of `x` will depend on the value of the condition. Since this is true, the whole ternary expression reduces to `"this"`

## Iteration

Python's `while` loop is similar to C's while loop. The block of code inside the while loop will be executed repeatedly until the condition becomes false.

```python
i = 0
while i < 5:
    print(i)
    i+=1
```

```python
0
1
2
3
4
```

Python's `for` loop is different from C. Python's `for` loop is a collections loop similar to Java's `foreach` expression.

```python
for i in ["this",2,True,4]:
    print(i)
```

```
this
2
True
4
```

This for loop can be interpreted in common language as, *For every element $i$ in ["this",2,True,4], print $i$*. Inside the for loop, the value of `i` refers to the elements inside the list. At the first execution of `print(i)`, `i` refers to the first element of the list. At the second execution `i` refers to the 2nd element of the list. and so on until it exhausts the list.

A common pattern for a `for` loop is something like this:

```python
for i in range(0,5):
    print(i)
```

```python
0
1
2
3
4
```

The function `range` produces a list of integers, starting from `0` until the `4`. `range(0,5)` can even be shortened to `range(5)`. 

## Python functions

Python functions are written using the following syntax:

```python
def f(parameters):
	body
```

For example creating the add function:

```python
def add(x,y):
    return x * y
```

## Python file reading and writing

### Opening a file

To open file in python

```python
f = open("input.in","a") 
```

The first parameter is the path to the file and the second parameter is the mode the file opening

- `"a"` - append mode
- `"a+"` - append mode but if the file being opened does not exist, create the file and append
- `"w"` - for write mode (overwrites the contents of the file)
- `"w+"` - write mode but if the file being opened does not exist, create the file and write
- `"r"` - read mode
- `"r+"` - read mode but if the file being opened does not exist, create the file and read

### Writing to a  file

To write a single line in the file use the method `write()`. If the file is opened using `"a"` and `"a+"` mode the string is appended to the file. If it is opened using `w` and `w+` modes, the files contents are overwritten by this new line. Will not work on `"r"` and `"r"+` modes.

```python
f.write("foo")
```

### Reading from a file

To read an entire file use the method `read()`. This function returns the whole file as a string

```python
f.read()
```

### Closing a file

```python
f.close()
```

## Formatted Strings

When working with multiple string concatenations you can use formatted strings. For example, the following string

```python
s = "this"
t = "is"
u = "tedious"
v = "times"
w = 100

c = s + " " + t + " " + u + " " + v + " " + str(w)
print(c)
```

```
this is tedious times 100
```

Can be concatenated similar to C's formatting using the `%` operator

```python
s = "this"
t = "is"
u = "tedious"
v = "times"
w = 100

c = "%s %s %s %s %d" % (s,t,u,v,w)
print(c)
```

```
this is tedious times 100
```

## Type Annotations

Type annotations mark the type of identifiers.

```python
x:int = 1
s:str = "Hey"
```

You can also annotations to set the parameter types and the return type of a function

```python
def add(x:int,y:int) -> int:
    return x * y
```

Type annotations are only annotations, you don't have to write them. But writing them will help you make sense of a complicated system

## Python library import

To import python libraries use the keyword math followed by the python library name. As much as possible write imports at the topmost part of your python files

```python
import math
math.ceil(1.5)
```

```
2
```

You can give the library name a shorter alias for convenience

```python
import math as m
m.ceil(1.5)
```

```
2
```

You can also choose not to import the whole library, just specific classes and functions. When you do this you can directly access the class or function without the dot reference.

```python
from math import floor,ceil #importing floor,and ceil only
floor(1.2) + ceil(1.5)
```

```
3
```

If you are importing your own files, the same syntax will work as long as you are in the same directory as the python file you are importing.

```python
from myPythonFile import someMethod, someFile
```

> Assuming `myPythonFile.py` exists in the same directory you are currently in

## Python comments

Comments are made using the octothorpe/pound/hash/sharp symbol (`#`)

``` python
1 + 1 #comments are made using the octothorpe/pound/hash/sharp symbol
```

