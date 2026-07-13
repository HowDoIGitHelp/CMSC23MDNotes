# Extra Stuff

Here are extra things I want to discuss that do not really fall into specific lectures.

## Naming Methods Elegantly

One of the important things to consider about coding in OOP (and in general) is properly naming identifiers, functions, and classes. I'm sure this was talked about in your early programming languages. Creating names is a programming skill in and of itself. Creating concise (unlike Java's verbose naming schemes), descriptive names help in the maintainability of the code you're writing. When the name itself tells you what an identifier is for and when the name of the modifier tells you what it does, then you don't need to explain it in documentation or comments.

Since you're probably experts in naming identifiers from you C coding experience, lets focus on naming methods.

> **Disclaimer**: These naming conventions are according to MY standards of code elegance. I'm not saying that this particular way of naming is the correct way, to be honest these standards are more  subjective than objective. While your in my course I hope you'll at least try incorporate these conventions in your code. I don't care about how you write names in other course, but in my course, these are the names that I consider to be *beautiful*.
>
> Sometimes, even I forget to adhere to my own conventions, especially when I'm writing code outside OOP. You'll probably notice some contradictions to these conventions somewhere in this course. Just consider these contradictions as proof that the instructor that made these resources is indeed a human being. Let me offer you a preemptive *whoops* for all of those contradictions.

Methods (and functions in general) can be divided into two types, methods that return something, known as **builders**, and methods that do not return something, known as **manipulators**.

### Builders

An inelegant method name:

```python
def add(addend1:int, addend2:int) -> int:
    return addend1 + addend2
```

Now this seems like a descriptive enough name that describes what the method does. What is wrong with it? The issues start to surface once you invoke the function:

```python
solution = 5 - add(3,2)
```

When you see this line of code, your brain reads this as:

"solution becomes five minus add three and two"

Which can still be understood since we're used to what the method `add()` usually means. But consider this alternative way of naming this function:

```python
def sum(addend1:int, addend2:int) -> int:
    return addend1 + addend2
```

When called:

```python
solution = 5 - sum(3,2)
```

Your brain reads this as:

"solution becomes 5 minus sum of 3 and 2"

Which makes way more sense.

One of the most egregious violations to this convention is the naming of **getter**. Like the following

```python
#inside some class
def getName(self):
	return self.__name
```

```python
print("Hello " + p.getName())
```

Getter functions are usually created to reveal the values of private attributes. First of all getters, are a bit evil and must be avoided as much as possible since they violate the privacy of private attributes. It's supposed to be a private attribute, other classes are not supposed to access. 

But sometimes exposing these attributes are needed. Sometimes, you don't need to be able to change the value of the private attribute, you just need to know its value. The best way to name a getter is  the following.

```python
#inside some class
def name(self):
	return self.__name
```

```python
print("Hello " + p.name())
```

People might complain, that `name()` and `__name` are ambiguous. But for me this ambiguity is fine since they end up meaning the same thing. Plus, they will never be used interchangeably (maybe in functional paradigm they may) because one is a method and the other is an attribute.

So the rule of the thumb that you need to follow when you are naming builders is the following, **A method that returns something (a builder), should be named after a noun that describe what it is returning.**

#### Some more builders named elegantly

This is a factory method, which is responsible for building new instances of a class called person. Therefore this method is called `newPerson()`

```python
#inside some class
def newPerson(self) -> Person:
	return Person()
```

This method returns the element of a given list at position $k$

```python
def kthElement(list:[float],k:int) -> float:
	return list[k]
```

This is a method that returns the concatenation of two strings

```python
def concatenation(u:str, v: str) -> str:
	return u + v
```

### Manipulators

This is an example of an inelegantly named manipulator:

```python
#inside some class
def nameString(self):
	print(self.__name)
```

When your brain reads an invocation of this method it appears out of place:

```python
if (value > threshold):
	nameString()
else:
	print("value is invalid")
```

A better name for this function is the following:

```python
#inside some class
def printName(self):
	print(self.__name)
```

```python
if (value > threshold):
	p.printName()
else:
	print("value is invalid")
```

The original name `nameString()` is inelegant because it is named like a builder, when in fact it doesn't build anything. It returns nothing. This method an example of a **manipulator**. It should be named like a manipulator. This particular method manipulates the output stream of wherever you are printing.

**Setter** methods are actually named correctly, setter methods are methods that set the value of a specific private attribute:

```python
#inside some class
def setName(self, newValue:str):
	self.__name = newValue
```

Setters are also quite evil in the same way getters are evil. These methods change the values of private methods, violating their privacy. Although the usage of setters is unavoidable in some design patterns, avoid them as much as you can.  But at least its name follows this convention for naming manipulators. 

The rule of thumb for naming manipulators is the following. **A method that doesn't return anything should be a manipulator and must be named from the verb that describes the its manipulation**.

#### Some more manipulators named elegantly

A method that advances time by 5 seconds:

```python
#inside some class
def skipForward(self):
    self.__time = self.__time + 5
```

A method that removes the first element in the list

```python
#inside some class
def decapitate(self):
	self.__list = self.__list[1::]
```

> It's called decapitate because it removes the head of he list. Sometimes, creative and descriptive method names like this help you remember what they do, just because they are accurately named but still memorable

### Predicates

```python
def isPositive(n:int) -> bool:
	return n > 0
```

Predicates are methods that return either true or false. Although these methods are technically builders, they follow a different naming convention. These methods are named like predicates in a sentence. Example:

Checks if a word is capitalized

```python
def isCapitalized(word:str) -> bool:
	return word[0].isUpper()
```

Checks is a list is empty

```python
def isEmpty(list:[int]) -> bool:
	return len(list) == 0
```

The reason why predicates are named like this is because of how they are used inside if statements. The following is way more readable:

```python
if (isPositive(n)):
	return n
else:
	return -n
```

"If n is positive then ..."

Compared to this

```python
if(positivity(n)):
	return n
else:
	return -n
```

"If positivity of n then ..."

### Single Responsibility Principle and correct naming

The correct naming of methods helps with one of OOP's design principle, SRP. The name of the function describes the one thing it does. For example the method `sum()` does exactly one thing, it builds the sum of two numbers. The method `decapitate()` does exactly one thing, it removes the head of the list. Whenever you encounter a function that gives you trouble in deciding a name because it is both a manipulator and a builder then it means that the method has more than one responsibility. This means that you should break down this method into smaller methods that does exactly one thing.

The following method should be broken down

```python
def decapitateAndHead(self):
    head = self.__list[0]
	self.__list = self.__list[1::]
    return head
```

Into these two methods:

```python
def decapitate(self):
	self.__list = self.__list[1::]
```

```python
def head(self) -> int:
	return self.__list[0]
```

## The special function `__str__()`

You will sometimes encounter the following function in the lab exercises. When you add this function inside your class, it makes the instances of that class *stringable* and *printable*. This means that the class now has a string representation, which means that instances of the class can be easily converted to string and can be printed directly using `print()`.

For example, given a person class:

```python
class Person:
    def __init__(self,name:str,age:int):
        self.name = name
        self.age = age

    def __str__(self) -> str:
        return self.name + " " + str(self.age)
```

By implementing the `__str__()` method your class instances can now be treated like a stringable or printable instances:

>`__str__()` should always accept nothing (but the reference to self) as a parameter and return a string.

**Printing the instance itself**

When a printable type is placed inside the print function and nothing else, it automatically converts it as a string and prints it

```python
print(Person("Cheems",29))
```

```
Cheems 29
```

The string above is printed since this is the value that `Persons`'s' `__str__()` method returns.

**Conversion to string**

A stringable instance can be converted to a string using the builtin `str()` function:

```python
print("Hi I'm " + str(Person("Cheems",29)) + " years old")
```

```
Hi I'm Cheems 29 years old
```

This will also work for formatted strings if you use the sigil `%s`:

```
print("Hi I'm %s years old" % Person("Cheems",29))
```

```
Hi I'm Cheems 29 years old
```



