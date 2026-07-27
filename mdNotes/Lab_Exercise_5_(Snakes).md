# Lab Exercise 5 (Snakes)

Implement the following functions:

#### doubledInt
accepts an int and return the double of that int

```python
def doubledInt(x:int) -> int:
    pass
```

#### largest
accepts two floats and returns the larger value

```python
def largest(x:float,y:float) -> float:
    pass
```

#### verticalLine
accepts two (float,float) tuples which represent two points in a cartesian plane (x,y) and returns true if the points describe a vertical line and false otherwise

```python
def verticalLine(a:tuple[float,float],b:[float,float] -> bool:
    pass
```

#### primes
accepts an integer n and returns the first n primes

```python
def primes(n:int) -> list[int]:
    pass
```

#### fibonacci
accepts an integer n and returns a the list containing the first n elements of fibonacci sequence (starting with 0 and 1)
```python
def fibonacci(n:int) -> list[int]:
    pass
```

#### sortedIntegers
accepts a list of integers and sorts it from smallest to highest, please do not use python's builtin sort, implement your own sort function
```python
def sortedIntegers(l:[int]) -> list[int]:
    pass
```

#### sublists
accepts a list of integers and returns all the sublists of the list. Sublists are contigous chunks of a list (including an empty list and the list itself). `[1,2]`, `[2]`, `[]`, `[2,3,4]`, and `[1,2,3,4,5]` are sublists of `[1,2,3,4,5]` but `[3,5]` and `[1,2,3,4,6]` are not.

```python
def sublists(l:list[int]) -> list[list[int]]:
    pass
```