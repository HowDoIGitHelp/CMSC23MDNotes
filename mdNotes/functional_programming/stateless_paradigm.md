# A Stateless Paradigm

## Functional purity and the absence of states

One of the most distinguishing aspects of functional programming and the declarative family of languages is its philosophy of statelessness.
A programmer primarily exposed to mutable imperative programming languages will find that the concept of state is natural and maybe even inevitable.
Functional paradigm challenges this concept and offers a much safer and mathematically intuitive philosophy.
In the perspective of functional programming, there is no state, and everything is immutable.

### Pure functions

To understand the functional programming perspective, we have to take a step away from the imperative programming definition of a function.
Let's go back to the definition of a function in mathematics.

Across several, mathematical disciplines a function means the same thing.
Consider two sets $A$ and $B$.
We can define a function as the *mapping* between the elements of $A$ and $B$.
The elements of $A$ and $B$ can be anything, they can be numbers, which shows us how a function can be represented by a formula or a graph.
The elements of $A$ and $B$ can be matrices and vectors, which defines a function as a transformation between two vector spaces.
On the higher level perspective of category theory, functions are *morphisms* between objects of a given category.

$$
f:(A\to B)
$$

If you remember functions from discrete math, functions at its most basic form looks like the one above.

Functions in functional programming languages like Haskell are (arguably) the closest computer representation of a mathematical function.
We call these functions, **pure functions**.

They differ from your standard C function because the definition inside pure functions are only instructions on how to produce a result based on the parameters.
To fully understand this concept here are some examples of impure functions

```c
int square(int x){
	addToExternalLogger("calculating square");
    return x*x;
}
```

The impurity in this function is the line where the function writes to some external logger, `addToExternalLogger("calculating square");`.
A function can only be pure if the result of the function can be fully determined by its parameter.
The only parameter here is `x`.
Invoking this square function with the same parameter value does not do the same thing.
The effect of changing the logger is dependent on the previous state of the external logger.
The effect on the logger is what we call a **side effect** of the `square` function.
It is a side effect since this line of code modifies values outside boundaries of the function.

```c
int* increaseArray(int *a, int size){
    for(int i = 0; i < size; i++)
    a[i]+1;
}
```

A pass by address/reference function which changes the value of a parameter will automatically be an impure function since changing the value of `a` is a **mutation**, which is a side effect.


```java
String headsortails(){
	if(randInt()%2==0)
		return "heads";
	else
		return "tails";
}
```

This function is also impure because the return value is not dependent on the parameters alone.
The return value will be dependent on the randomization seed which is something outside the parameters of the function.

A pure function must satisfy these two:

1. A pure function has no side effects
2. A pure functions output must be dependent on the inputs alone[^function]

[^function]: In fact if $f(a)=b$ and $f(a)=c$ where $b\neq c$, then $f$ is not a function at all

A good way to test if a function is pure is if you can (theoretically) create an infinitely long *lookup table* such that, looking up the value for a specific input is perfectly identical to calling the function with the same input.
And if you think about it this is the essence of a function.
Functions are just of mappings between the domain and the range,

### The Absence of mutation

One of the hallmarks that make imperative programming imperative is the assignment statement.
It enables the program to advance to a new state.
Purely functional programming languages like Haskell, lacks the mechanism to mutate anything.
Using the "`=`" operator (which signals an assignment statement in imperative languages) in functional languages *binds* the value on the right-hand side to the left-hand side.
This mechanism is conceptually different from an assignment operation in C.
It is perfectly fine to do the following in C:

```C
int x = 0;
x = 1;
```

This code in C starts with a combined declaration and assignment: `int x = 0`.
The second line, **reassigns** the same variable `x` to the new value `1`.
These lines of code correspond to a *mutation* on the variable `x`, (from `0` to `1`).
Because mutation can happen anytime during runtime, the value of the variable `x` is not definite.
This is why values of variables in imperative programming **depend on the current state** of the program.

On the other hand, replicating this C code in Haskell is in fact not allowed:

```haskell
x = 0
x = 1
```

It will give an error message upon compilation:

```haskell
main.hs:2:1: error:
  Multiple declarations of ‘x’
  Declared at: main.hs:1:1
         main.hs:2:1
```

In Haskell, any "`=`" statement is a *declaration of a binding*.
These bindings are *final* (in the scope of the identifier).
It is even wrong to call `x` here a variable since its value does not vary.
The correct way to call `x` is *identifier*, since it merely identifies the value bound to it.

### Consequences of Statelessness and Immutability

Haskell's functions are better representations of mathematical functions. 
But what is the point of faithfully representing mathematical functions?

There are several reasons why programming without state can lead to better code.
Eliminating all side effects is demonstrably *safer* against accidental errors.
Building a library of functions perfectly working without worrying about side effects makes the system easier to understand and more resilient to changes.

```c
void f(int *x, int y){
    *x = *x + y;
    printf("%d\n",*x);
    return;
}
int main(void) {
    int x = 0;
    f(&x, 3);
    x = x - 2;
    f(&x, 3);
}
```

The exact behavior of the function `f()` **depends on where you use it**.
Even if you use the same parameters, you're not guaranteed to get the same results.

On small chunks of code, managing the consequences of having states such as global variables, will be trivial since you can reasonably track which variables are global (*or external in general*) and which functions interact with the global variables.
Even with a few lines of code like the example, the function's effects and *side effects* are not very obvious.

As the system grows, using functions and variables without double-checking for side effects becomes much harder.
As a consequence the whole system becomes a nightmare of impure functions on top of impure functions which may unexpectedly affect other parts of the system.
On a corporate setting where multiple people are working on the same system, refactoring becomes *unsafe* without knowledge of all the side effects of the functions in use.
On systems with *shared resources* and multi-threading it becomes extra-extra difficult to keep track of things without proper documentation.

Even with all these disadvantages in the state-full mutable paradigm of imperative programming, *one can still write robust and harmonious code*.
You just have to be extra careful writing your code with discipline, *only using global variables and side effects when it is safe and necessary*.
This is in fact the reason why *writing smaller pure functions* is considered good practice in any paradigm.
After all, being able to code with states can be thought of as an extra feature.
You can be a C programmer and just treat all your variables as immutable and all of your functions as pure.

