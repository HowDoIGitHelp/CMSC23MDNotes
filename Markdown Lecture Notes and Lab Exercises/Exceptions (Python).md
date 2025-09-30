# Exceptions (Python)

## Introduction

One of the features added to imperative programming was the elegant handling of errors. Exception handling provided OOP a mechanism to control what exactly the system does when parts of the system fail. 

## Learning Outcomes

1. Create methods that raise errors in python
2. Create a try-except clause to properly react to errors in python
3. Design systems that correctly assign error handling responsibilties

---

Let's explore this with an example. In the snippet below, the last line is not reached because the system fails during `print(quotientUnsafe(3,0))`.

```python
def quotientUnsafe(a:float, b:float) -> float:
    return a/b

print(quotientUnsafe(3,2))
print(quotientUnsafe(3,0))
print("some more behavior")
```

```python
1.5
---------------------------------------------------------------------------
ZeroDivisionError                         Traceback (most recent call last)
 in 
      3 
      4 print(quotientUnsafe(3,2))
----> 5 print(quotientUnsafe(3,0))
      6 print("some more behavior")

 in quotientUnsafe(a, b)
      1 def quotientUnsafe(a:float, b:float) -> float:
----> 2     return a/b
      3 
      4 print(quotientUnsafe(3,2))
      5 print(quotientUnsafe(3,0))

ZeroDivisionError: division by zero
```

> Python has its own error raised when it encounter division by zero (`ZeroDivisionError`) but well create our own for the sake of learning

Problematic functions and methods like `quotientUnsafe` don't always return a float. The problem for this `quotientUnsafe` function is that there is a possibility you'll end up dividing with zero. This introduces the concept of exception. Where the quotient function works **except** when the denominator is zero.

To implement this kind of behavior. You create an if-else check (or any control statement) to make sure the denominator is not zero. If you do encounter a zero denominator you **`raise`** an error. Here you are raising a user defined error instance called `DivisionByZeroError`. This user defined error must be a specialization of the built in class `Exception`

```python
class DivisionByZeroError(Exception):
    def __init__(self,numerator:float, denominator:float):
        self.numerator = numerator
        self.denominator = denominator

def quotient(a:float, b:float) -> float: #maybe a float
    if b == 0:
        raise DivisionByZeroError(a,b)
    else:
        return a/b
```

When quotient is called where the denominator passed is zero, python will inform you that the function call has resulted in a `DivisionByZeroError`

```python
try:
    quotient(3,2)
    quotient(3,0)
except DivisionByZeroError:
    print("there was dividing by zero somewhere but it's okay I'm still fine")
    
print("some more behavior")
```

```
there was dividing by zero somewhere but it's okay I'm still fine
some more behavior
```

By enclosing the lines of code that could potentially raise errors, you create a safety net for the system. The system **tries** to execute these lines, and if there are no errors raised inside the `try` block then the system runs normally. Nothing abnormal would occur **except** when the problematic lines of code raises an error. In this specific case, the system is catching, a specific type of error called `DivisionByZeroError`. If a specific type of error is not specified in the exception block, the system will catch any general error.

If a method with potential to raise errors like `quotient()`, is invoked inside another method without using the try except block, the caller method becomes a method with potential to raise errors as well. 

```python
def mixedFraction(a:float, b: float, c:float) -> float: #maybe a float
    return a + quotient(b,c)

mixedFraction(1,1,0)
print("some more behavior")
```

```python
---------------------------------------------------------------------------
DivisionByZeroError                       Traceback (most recent call last)
 in ()
      2     return a + quotient(b,c)
      3 
----> 4 mixedFraction(1,1,0)

 in mixedFraction(a, b, c)
      1 def mixedFraction(a:float, b: float, c:float) -> float: #maybe a float
----> 2     return a + quotient(b,c)
      3 
      4 mixedFraction(1,1,0)

 in quotient(a, b)
      7 def quotient(a:float, b:float) -> float: #maybe a float
      8     if b == 0:
----> 9         raise DivisionByZeroError(a,b)
     10     else:
     11         return a/b

DivisionByZeroError: (1, 0)
```

Here, the `quotient()`'s caller,`mixedFraction()`, does not enclose the problematic line with a try-except block. This means that `mixedFraction` is basically the ignoring any potential error, shifting the responsibility of dealing with the error to wherever `mixedFraction()` is called.

On the example below, `quotient()` is invoked inside `quotientString()`, but instead of ignoring the error, `quotientString()` deals with it using a try-except block. When quotient fails, the function returns "undefined number" instead of a fraction string.

```python
def quotientString(a:float,b:float) -> str: #always a string
    try:
        wholePart = math.floor(quotient(a,b))
        decimalPart = quotient(a,b) - wholePart
        return str(wholePart) + " and " + str(decimalPart)
    except Exception:
        return "undefined number"
print(quotientString(17,7))
print(quotientString(1,0))
print(quotientString(1,3))
```

```python
2 and 0.4285714285714284
undefined number
0 and 0.3333333333333333
```

By dealing with potential errors, `quotientString()` becomes a safe function that has no potential of breaking the system.

You can catch multiple kinds of exceptions if you want to handle different exceptions differently. Here, you see a different kind of `Exception` called `IndexError`. This exception is raised when an iterable type like list accesses a non-existent member. Here the function `quotientList` wants to append `math.inf` if you're dividing by zero and not append anything if you're dividing with non-existent list members.

```python
def quotientList(l:list[float],m:list[float]) -> [float]: #always a list of float
    r = []
    for i in range(0,len(l)):
        try:
            r.append(quotient(l[i],m[i]))
        except DivisionByZeroError:
            r.append(math.inf)
        except IndexError:
            pass
    return r
quotientList([1,2,3,4,5,6],[3,0,2,2,1])
```

```python
quotientList([1,2,3,4,5,6],[3,0,2,2,1])
```

### To ignore errors or to deal with errors?

So you've seen two ways to react with potential errors, to ignore them like `mixedFraction()` or to deal with them like `quotientString()`. Which is the proper way? You might think that dealing with errors is better since it's this way doesn't break the system. But actually the choice to ignore or to deal with errors depends on who has the correct responsibility in fixing the error. 

If you immediately deal with the error as quickly as possible, you'll end up missing the importance of raising errors. The method `quotient()` for example, is responsible for providing the caller with a quotient, that must be this method's only responsibility. You should not give `quotient` the responsibility of fixing division by zeroes. That responsibility lies on its caller, because different caller's may have different ways to deal with the error. The method `quotientString()` deals with division by zero with "undefined number" while the method `quotientList()` deals with division by zero with "inf". These two callers have different ways of interpreting division by zero so they should be the one's responsible for dealing with the error.



