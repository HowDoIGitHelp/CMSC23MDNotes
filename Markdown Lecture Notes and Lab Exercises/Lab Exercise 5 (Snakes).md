# Lab Exercise 5 (Snakes)

## Task

To familiarize yourselves with how python syntax works, create the python library (".py") and **write the following functions inside it:**

##### double

```python
def doubledInt(x:int) -> int:
	#accepts an in and return the double of that int
```

##### largest

```python
def largest(x:float,y:float) -> float:
    #accepts two floats and returns the larger value
```

##### vertical line

```python
def isVertical(a:(float,float),b:(float,float)) -> bool:
    #accepts two (float,float) tuples which represent two points in a cartesian plane and returns true if the points describe a vertical line and false otherwise
```

##### primes

```python
def primes(n:int) -> [int]:
    #accepts an integer n and returns the first n primes
```

##### fibonacci

```python
def fibonacciSequence(n:int) -> [int]:
	#accepts an integer n and returns a list containing the first n elements of fibonacci sequence (starting with 0 and 1)
```

##### sorting

```python
def sortedIntegers(l:[int]) -> [int]:
	#accepts a list of integers and returns a list with the same integers sorted from smallest to highest
```

##### sublists

```python
def sublists(l:[int]) -> [[int]]:
	#accepts a list of integers and returns all the sublists of the list. Sublists are contiguous chunks of a list (including an empty list and the list itself). [1,2], [2], [], [2,3,4], and [1,2,3,4,5] are sublists of [1,2,3,4,5] but [3,5] and [1,2,3,4,6] are not.
```

##### fast modular exponentiation (optional)

this should work for large values of b and p. To do this implement fast modular exponentiation from CMSC 56

```python
def fme(b:int,p:int,m:int) -> int:
    #accepts 3 ints, b,p and m and computes b^p mod m
```

## Assessment Criteria

- Completeness of python functions - 35
- Correctness of function names and parameter names - 5

**Deadline November 30, 2020**