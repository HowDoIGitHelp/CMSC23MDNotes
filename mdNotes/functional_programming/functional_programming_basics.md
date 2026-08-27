# Functional Programming Basics

## Reimagined functions

Lambda calculus evolved from a system of logic foundation with deep roots to computation theory into something that became a basis for *programming language design*.
Language designers started to consider the unconventional representation of lambda calculus expression as a valid and pragmatic way of *representing data*.
Around 1950s programming languages patterned around the framework of lambda calculus started to emerge.
One of the earliest and most important of these languages was **Lisp** which evolved to become a large family of programming languages[@bergin_evolution_1996].
Soon, more programming languages started to implement the same formalism described by lambda calculus.
This opened a new paradigm of programming languages called **functional programming paradigm**.
To explore this paradigm this section will introduce the programming language **Haskell**.
This language has become one of the most important functional programming language, setting the standards for other languages' paradigm.

### How functions are treated Differently

One of the biggest difference between your classic imperative programming languages like C and Java and a functional programming language, is how it treats its *functions*.
You might have probably guessed that since the paradigm has "function" in its name.
To explore this contrast lets start by introducing a simple function, written in C.
This function basically accepts an integer and returns the same integer but squared:

```c
int square(int x){
	return x*x;
}
```

Functions like these are patterned from mathematical functions.
It has a **name** to invoke it later called `square`, it has **specifications** on which type of data it accepts (`int x`) and produces (`int`), and finally it has **instructions** on what must be done when it is invoked (`return x*x`). Functional programming functions behave in more or less the same way.

```haskell
square :: Int -> Int
square x = x * x
```

It looks different but all the parts you can find in a C function can also be found on this Haskell function.

In terms of invocation, they are also used similarly and of course they behave similarly:

```c
square(5);
```

```haskell
square 5
```

Although functions in non-functional programming behave and look similar to functions in functional programming language, they have a huge difference in the way the programming language *treats* it.

A function in C is treated differently from other types of data.
In fact C programmers will rarely call a function a *value*.
What this means is that canonical value types like integers, characters, and arrays (even compound value like `struct` instances and objects) can be *passed* on functions and can be *returned* as functions.

```c
int* add_to_array(int arr*,int x,int size){
    for(int i=0;i<size;i++){
        arr[i]+x;
    }
    return arr;
}
```

C discriminates function from these canonical value types.
Therefore, during runtime, non-functional programming languages interpret the expression `square(5)` as "the number $5$ squared" while the expression `square` is just some disembodied function name (*square of which number?*).
Imperative programming functions during runtime are meaningless unless they are directly invoked.

### Higher Order Functions

#### Passing functions

Functional programming languages treat functions the same way it treats values, you *can* pass them in other functions, and you *can* return them as well.

```haskell
s :: Int -> Int
s x = x + 1

p :: Int -> Int
p x = x - 1

applytwice :: (Int-> Int) -> Int -> Int
applytwice f x = f (f x)
```

The code above shows two function definitions with some *type signature annotations* for readability.
The first is the function `s :: Int -> Int` which is *applied to an integer* and *produces an integer*.
What it does is it simply adds one to `x`.
The second function is similar but what it does is subtracting one from `x`.

Type signature annotations are not required here, that's why they're called *annotations*.
Adding these annotations will *restrict* the type the functions can be applied to.
Type signature annotation syntax are understood like this

```haskell
f :: Paramtype -> AnotherParamtype -> ... -> OutputType
```

The last type in the `->` series is the type the function produces (similar to its return type) and everything else before it are parameter types.

The third function called `apply_twice` is what we call a higher order function.
A **higher order function** is a function that either accepts a function as a parameter or returns a function parameter or both.
The function `applytwice`, as described by its *type signature*, is applied to a function `f` and an integer `x`and produces an integer.
It applies the function `f` twice to `x`, something like $f(f(x))$.

By defining a function like this we can do something like this during runtime:

```haskell
ghci> applytwice s 3
5
ghci> applytwice p 3
1
```

To a programmer with no experience with functional programming, this feature can be surprising especially since mathematical functions in algebra or calculus don't even explore this capability.
But if you remember this is not just some arbitrary added feature added for novelty.
This feature is directly patterned from lambda calculus:

$$
\begin{aligned}
\text{let } S &= \lambda n .\lambda s. \lambda z. (s(nsz))\\
T &= \lambda f. \lambda x. (f(fx))\\
\overline 5&=TS\overline{3}
\end{aligned}
$$

In lambda calculus an *abstraction* and an *application* does not restrict anyone from the type of expressions bound to variables.
In the spirit of implementing lambda calculus, any functional programming language will allow you to do this as well.

#### Returning functions

On the other side of the coin, a function, in functional programming will also let you *return functions* the same way you *return* any other kind of data.

To explore this, suppose we have different functions that when applied to an integer, produces that integer plus a certain integer.

```haskell
addTwo :: Int -> Int
addTwo x = x + 2

addThree :: Int -> Int
addThree x = x + 3

addFour :: Int -> Int
addFour x = x + 4
```

We can generalize these functions into a *function-maker* function, that when applied to an arbitrary integer `x`, will produce a function similar to `addx` which is a function that adds `x` to your integer.

```haskell
addMaker :: Int -> (Int -> Int)
addMaker x = (\y -> x + y)
 ```

We are introducing new syntax here, but this new syntax is a representation of an expression we already know from lambda calculus.
The definition for your `addMaker` (`\y -> x + y`) is basically an implementation of the following *lambda expression*[^backslash_lambda].
`y` is the bound variable, and the operator `->` separates the inputs and the output, `x + y`[^plus].

$$
\lambda y. \text{add }x y
$$

[^backslash_lambda]: In fact, the reason why Haskell syntax uses the `\` character to represent lambda expressions is because this is your keyboard's best physical approximation of the Greek letter $\lambda$.

[^plus]: Extra note: "$+$" does not exist in the universe of lambda calculus so instead what's used here is a reference to a lambda calculus abstraction called "$\text{add}$". You can check its definition in the optional reading [Lambda Calculus Encodings](/functional-formalism)

What this expression means then is that `addMaker` *produces a lambda expression*, which essentially behaves exactly like a function.
This allows you to create functions during runtime.

```Haskell
ghci> addSix = addMaker 6
```

Simply writing the expression `addSix` on your terminal will yield you an *error*, because printing `addSix` doesn't really have a meaning outside the world of lambda calculus.
It is a lambda expression which is *basically* a function.
*How do you represent a function as a string?*

But since `addSix` is a lambda that behaves exactly like a function, you can apply `addSix` to an integer, and it will give you a meaningful answer.

```haskell
ghci> addSix 3
9
```

In fact, you can even omit the part where you bind the value returned by `addMaker` to a name, and instead use it directly.
Here, `(addMaker 7)` is a lambda expression, therefore it can be applied to an integer.

```haskell
ghci> (addMaker 7) 4
11
```

This nifty trick right here is the reason why lambda expressions are also called **anonymous functions** since these expressions on their own don't have a name.
Lambda expressions generally appear in functional programming languages and even non-strictly functional programming languages.
Lambdas can be useful if you want to create a function that will be used only once:

```haskell
ghci> applytwice (\x -> x + 2) 3
7
ghci> (\x -> x * x) 4
16
```

Lambdas, just like any other canonical value type can be bound to identifiers[^binding].
Doing this will **name** the lambda thus allowing it to behave just like any other named function.


[^binding]: Bindings are haskell's representation of a mathematical "let" statement. When you see a `=` operator like `x = 3`, this is not an assignment statement, but instead a let binding. In this case **3** is bound to the identifier `x`. Similar to what happens when you say $\text{let } x = 3$ in math.

##### Closure

A higher order function like `addMaker` above, is not *only* producing the lambda inside its definition.
What is actually being produced is a construct called a **closure** which is the function definition described by the *lambda* and the *environment* of the function call.
The extra data, called environment, is the reason why the lambda `(\y -> x + y)` makes sense outside the context of `addMaker`.
Without passing the *environment*, the variable `x` would be a free variable which will yield you a compilation error.


Inside your `addmaker` when you evaluate `addMaker 6`, the parameter `6` is *bound* to the variable $x$.
Therefore, the resulting lambda produced by `addMaker 6` will behave exactly like the lambda, `(\y -> 6 + y)`.

Closures are still a direct consequence of lambda calculus' variable binding rules.
Specifically how the variables $x$ and $y$ are bound in the innermost body of the lambda calculus abstraction $\lambda x.\lambda y.
addx$.

## Currying

If we look back to lambda calculus you'll notice how abstractions are defined to be:

$$
\lambda x. \mathcal{M}
$$

Here we can see that abstractions are defined to have exactly one parameter.
One can argue that this is different from how functional programming represents its own functions and lambdas since functions with *multiple parameters* are allowed in these languages.
As it turns out, these functions are just *disguised* to have multiple parameters.
These functions are just several single parameter functions combined to *simulate* multiple parameter functions.
As an example: a function `add` that adds two numbers may look like multiple parameter functions:

```Haskell
plus x y = x + y
```

Internally, this function is equivalent to two lambda calculus abstractions, nested together to simulate *multiparameterness*.

```haskell
plus = \x -> (\y -> x + y)
```

Here `plus` is a higher level function that accepts a single argument `x` and produces the closure `(\y -> x + y)`.
This expression is a direct implementation of the following lambda calculus abstraction[^plus].

$$
\text{plus}=\lambda x.\lambda y. \text{add }x y
$$

Just like lambda calculus, Haskell's `->` operator is right associative, so you can write the same *plus* function as:

```haskell
plus = \x -> \y -> x + y
```

You can even omit the first `->` and the `\` near `y`, and it will mean the same lambda expression:

```haskell
plus = \x y -> x + y
```

Which looks almost exactly similar to a lambda calculus expression with multiple parameters:

$$
\lambda xy.\text{add }xy
$$

Now when you want to apply this function we write:

```haskell
ghci> (plus 3) 4
7
```

Which means that: first, we are evaluating `(plus 3)` which will give us a *closure*.
The closure is then *applied* to `4` which completes the evaluation to `7`.
This is also a direct implementation of a lambda calculus application:

$$
(\text{plus }\overline{3}) \overline 4
$$

And just like lambda calculus, function applications in Haskell are also left associative, so you can omit the parentheses:

```haskell
ghci> plus 3 4
7
```

All multiple parameter functions and lambdas in Haskell are nested single parameter abstractions in disguise, so for all intents and purposes, these two definitions for `plus` behave in the exact same way:

```haskell
plus x y = x + y

plus = \x -> (\y -> x + y)
```

This means that the expression`(plus 3)` will have the same meaning regardless of the way you define `plus`.
The expression `(plus 3)` has a special name, it is called a **partial application**.
When you apply a function that is supposed to accept $n$ parameters to $m$ values (where $m<n$), i.e. you are supplying the function *less parameters* than it is expecting.
Instead of getting the value, you get a partial application of that function which will evaluate to a *closure*.

The process of converting a multiparameter function or lambda to a nested single parameter lambda is called **currying**.
This term is named after the mathematician Haskell Brooks Curry, which is the same Haskell, the programming language is named after.

Partial application can be very useful since you quickly create lambdas from the definition of existing functions.
For example, a function that adds 15 to an element as such:

```haskell
addFifteen = \x -> 15 + x
```

But with partial application, this can be defined with the following:

```haskell
addFifteen = plus 15
```

We can show that these are equivalent definitions by currying `plus`

```haskell
addFifteen = plus 15
addFifteen = (\x -> (\y -> x + y)) 15
addFifteen = \y -> 15 + y
addFifteen = \x -> 15 + x --alpha equivalent to the lambda above
```

Also, all operations in haskell are also functions.
This means you can also use partial applications with them.
An operator can be used like a function if it is written using preorder notation instead of inorder notation.

```haskell
ghci> (+) 3 2
5
```

And since `+` is a function, it can also be partially applied.

```haskell
ghci> applytwice (+ 3) 4
10
```
