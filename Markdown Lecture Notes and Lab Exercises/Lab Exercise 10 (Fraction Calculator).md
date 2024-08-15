# Lab Exercise 10 (Fraction Calculator)

## Task

You're creating a less sophisticated version of a fraction calculator. This calculator only has arithmetic operations inside it, addition, subtraction, division, and multiplication. Inside this calculator, a calculation is represented in a `Calculation` instance. Every calculation has four parts:

- `__left` - represents the left operand fraction
- `__right` - represents the right operand fraction
- `__operation` - represents the operation ($+$,$-$,$\times$,$\div$)
- `__answer` - represents the solution of the operation

```python
class Fraction:
    def __init__(self,num:int,denom:int):
        self.__num = num
        self.__denom = denom
    def num(self):
        return self.__num
    def denom(self):
        return self.__denom
    def __str__(self) -> str:
        return str(self.__num) + "/" + str(self.__denom)

class Calculation:
    def __init__(self,left:Fraction,right:Fraction,operation:Operation): #will cause an error when ran since Operation does not exist yet
        self.__left = left
        self.__right = right
        self.__operation = operation #the parameter that represents the operation
        self.__answer = None #the answer should be calculated here

    def __str__(self):
        return str(self.__left) + " " + str(self.__operation) + " " + str(self.__right) + " = " + str(self.__answer)


f:Fraction = Fraction(1,4)
print(f)
```

Python does indeed support higher order functions but your boss is anti-functional programming so he forbids the use these features. Because of this you decide to implement the strategy pattern.

To do this, you need to create an abstraction called `Operation` to represent the different operations. For each operation, you create a class that realizes `Operation`. 

![strategy pattern example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/bab2c4e390f529f00af5cb16d9597609863b3cd7/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/strategyexample.svg)

> `execute()` should have been named like a builder method (something like `solution()`), I'm keeping the name `execute()` since this is how Strategy patterns usually names this particular method.

**Complete the system using the strategy pattern.**

## Assessment Criteria

- Completeness of the pattern - 40
- Elegance of method and attribute naming - 10