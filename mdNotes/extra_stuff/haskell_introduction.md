# Haskell Introduction

## Setting up Haskell

To start writing Haskell code, install Haskell through stack. Stack is found in the folder called "Haskell/Stack" inside the provided course pack. Copy the Stack folder and place it in your computer. To be able to use stack anywhere, add your copy of the stack folder in the PATH variable of your computer.

Once stack has been set up using the steps above, you can run the GHC repl using the command 

```
> stack ghci
```

The first time you run this code, stack will automatically install the GHC compiler. 

After downloading GHC, you will be taken to the Prelude part of your GHC repl. To test if everything is working properly, try the following Haskell expression:

```haskell
Prelude> show (1 + 3)
```

If everything is good to go, the GHC expression will evaluate to:

```haskell
"4"
```

To exit GHC run the following GHC command

```
:quit
```

To load a Haskell program, enter the GHC repl first

```
> stack ghci
```

While inside `Prelude`, use the command `:load <Path to haskell file>`. For example

```haskell
Prelude> :load "Trying Things.hs"
```

If the path to the haskell file contains spaces, you need to enclose the path in quotes.

## Binding

To bind a value to some identifier use the `=` operator

```haskell
identifer = <some expression>
```

The expression to the left of the `=` operator is evaluated and then bound to the identifier to the right of the `=` identifier.

**Example**

the integer 3 bound to `x`

```haskell
x = 3
```

a lambda bound to `f`

```haskell
f = \x -> x + x
```

## Type annotations

The last type in the arrow series is the range type or return type. Every type before it are the domain types or the types of the parameters in order

```haskell
function_name :: Type_of_Param1 -> Type_of_Param1 -> ... -> Type_of_Paramn -> ReturnType
```

**Examples**

Double of an integer, $\text{double} : \mathbb{Z} \to \mathbb{Z}$

```haskell
double :: Int -> Int
```

Sum of the length of two strings (strings in Haskell are arrays of `Char`, an array of specific types are written enclosed in square brackets, `[Type]` represents an array of `Type`)

```haskell
len_sum :: [Char] -> [Char] -> Int
```

Check if some integer is even (boolean values are written capitalized, `True` and `False`)

```haskell
isEven :: Int -> Bool
```

Accept two `(Int -> Int)` functions and produce the composition of those functions (given functions $f$ and $g$, $f \circ g$)

```haskell
compose :: (Int -> Int) -> (Int -> Int) -> (Int -> Int)
```

### Type Variables

Haskell also supports type variables.
In order for functions to support multiple types, you can use type variables instead of concrete types like `Int`, `Char`, `[Int]`.

Types written in lower case are considered as type variables.
For example, you can define a more general `len_sum` function with the following type annotation:

```haskell
len_sum :: [a] -> [b] -> Int
```

By defining the function this way, it doesn't only accept two Strings (`[Char]`), it now accepts any two lists.

You can also define a more general `compose` function in the following way:

```haskell
compose :: (a -> b) -> (b -> c) -> (a -> c)
```

Since you have defined that the first parameter is of type `(a -> b)` and the second parameter is of type `(b -> a)`, the types labelled `b` should match.
In this case, the return type of the first function must match the accepted type of the second function.
The returned function is of type `(a -> c)`, this means that this returned function must accept a type that matches the first parameter's accepted type (`a`), and must return a type that matches the second parameters return type, (`c`).

### Typeclasses

Sometimes you might want to add some constraints to the allowed type in the type variable.
This is where you would use typeclasses.

By declaring the typeclass of a type variable, you will only be able to use types that match the typeclass specified.

For example, when you define a more generalized version of the `double` function as such:

```haskell
double :: a -> a
```

You might want to add an extra restriction to only allow, numerical types.
By adding that restriction, you will disallow the usage of double on strings, lists, chars, etc.
But you will allow the usage of integers, floats, doubles, etc.
To restrict `a` to only numerical types, you can use the `Num` typeclass.

```haskell
double :: Num a => a -> a
```

The typeclass prefix `Num a =>` means that type variables `a` can only be types under the `Num` subclass (i.e. `Int`, `Integer`, `Double`, `Float`).

## Function definitions

Every identifier placed in between the function name and `=` are the parameter names. The expression to the right of `=` is the expression evaluated when the function is called.

```haskell
function_name param1 param2 ... paramn = <some expression>
```

**Example**

Double function

```haskell
double x = x + x
```

isEven function

```haskell
isEven n = n % 1
```

## Function/Lambda Application

Two expression separated by a space is a function application. The expression on the left side must evaluate to a function or a lambda. This function/lambda is applied to the right expression as its parameter

```haskell
<expression1> <expression2>
```

A series of expressions are curried multiparameter applications. The leftmost expression must evaluate to a function or a lambda. This function/lambda is applied to the right expressions as its parameters.

```haskell
<expression1> <expression2> <expression3> ... <expressionn>
```

**Example**

```haskell
double 3
```

evaluates to 6

```
compose addThree double 2
```

evaluates to 7

## Lists

Lists in haskell can be defined as comma-separated values in between brackets.

```haskell
[element1, element2, element3]
```

Lists have to be homogeneous[^tuples], i.e. all of its elements must match the same type.

[^tuples]: Look into tuples for heterogeneous collections.

**Examples**

```haskell
[1,2,3,4]
```

```haskell
['a','b','c','d']
```

You can also create lists using the following pattern.
Such as list is valid if `tail` is a list and `head` matches the type of the elements in `tail`

```haskell
head : tail
```

**Examples**

```haskell
1:[2,3,4]
```

evaluates to `[1,2,3,4]`

```haskell
'a':('b': ['c', 'd'])
```

evaluates to `"abcd"`[^char_list]

[^char_list]: A list of `Char`s will evaluate to a string.

```haskell
1:2:3:4:[]
```

The `[]` expression evaluates to an empty list.

evaluates to `[1,2,3,4]`

You can also create regular series as lists by specifying the start and end of the list:

```haskell
[start..end]
```

**Examples**

```haskell
[1..6]
```

evaluates to `[1,2,3,4,5,6]`

```haskell
[-3..4]
```

evaluates to `[-3,-2,-1,0,1,2,3,4]`

## If-then-else expression

One of haskell's condition expressions are if-then-else expression. Because a haskell expression is required to evaluate to something, unlike C, all `if` parts must be followed by a `then` part and `else` part. 

```haskell
if <bool-exp> then <exp1> else <exp2>
```

The expression inside the if-clause must be an expression that evaluates into a boolean value. If the expression `<bool-exp>` evaluates to `True`, then the whole if-then-else expression evaluates to whatever `<exp1>` evaluates to. If `<bool-exp>` evaluates to `False`, then the whole if-then-else expression evaluates to whatever `<exp2>` evaluates to. The then clause and else clause cannot be empty, and both clauses must evaluate to the same type

Haskell boolean literals start with uppercase letters, `True` and `False`.

**Examples**

The expression:

```haskell
if (2 > 1) then 5 else 4
```

evaluates to `5`

The expression:

```haskell
8 * (if (3 <= 2) then 2 else 3)
```

evaluates to `24`

If-then-else statements can be nested by writing if statements inside the `then` part and `else` part

```haskell
if (2 > 1) then (if (0 == 1) then 2 else (1 + 2)) else (if (2 == 2) then 5 else (if (3 == 2) then 6 else 7))
```

evaluates to `3`

If else statements can be written neatly with tabs and newlines like this:

```haskell
f :: Int -> Int
f x = if (x > 1) 
  then (if (x == 1) 
    then 2 
    else (1 + 2)) 
  else (if (x == 2) 
    then 5 
    else (if (3 == x) 
      then 6 
      else 7))
```

## Guards

To avoid long nested `if-then-else` expressions, you can use guards.

```haskell
function param
    | boolean_expression1 = expression1
    | boolean_expression2 = expression2
    | boolean_expression3 = expression3
    ...
    | otherwise = expression4
```

Guard expression are read top to bottom, if it encounters a boolean expression that evaluates to `True`, the function will evaluate to the corresponding expression.

**Examples**

```haskell
f :: Int -> [Char]
f x
    | x == 1 = "option1"
    | x == 2 = "option2"
    | x == 3 = "option3"
    | otherwise = "invalid option"
```

```haskell
capped :: Num a => a -> a
capped x
    | x > 100 = 100
    | x < 0 = 0
    | otherwise = x
```

```haskell
f :: Int -> [Char]
f x
    | x == 1 = "option1"
    | x == 2 = "option2"
    | x == 3 = "option3"
```

A guard block with no `otherwise` clause is still valid, but it can cause run time errors if none of the boolean expression evaluate to `True`.

## Pattern matching

Pattern matching is a powerful tool that can also replace `if-then-else` expressions and guard blocks.
For example, we can define the factorial function as a function that evaluates into some `if-then-else` expression, or a guard block.
This is because we need to separate the evaluation for the base case and the recursive case.

```haskell
factorial :: Int -> Int
factorial n =
    if (n == 1) then 1
    else n * factorial (n - 1)
```

But with pattern matching, you can declare multiple definitions under the same function name where the arguments match to different patterns of values.

```haskell
factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n - 1)
```

Since the `factorial` name is defined with distinct patterns of values, these definitions can coexist with one another.
Whenever you call the `factorial` function with a given argument, Haskell will try to match it with all the possible patterns starting from top to bottom.
For example, if you evaluate `factorial 0`, it will match to the first pattern (`factorial 0`) and evaluate to 1.
If you evaluate `factorial 1`, it will match to the second pattern (`factorial n`) and evaluate to the recursive case `n * factorial 0`.

Pattern matching also allows you to break down data structures into different binding.
For example, you can define a function called `secondElement` which returns the second element of a list.
To achieve this we use the `:` list constructor.
In this example we use the specific pattern, `(e1:e2:rest)`
Any list that can be deconstructed into such pattern will match.
For example, the list `[1,2,3,4]`, can be deconstructed into `1:2:[3,4]`, (`e1 = 1`, `e2 = 2`, `rest = [3,4]`).
The list `['a','b']` can also be deconstructed into `'a':'b':[]`, (`e1 = 'a'`, `e2 = 'b'`, `rest = []`).
This means that the pattern can match any list with length two or greater.

```haskell
secondElement :: [a] -> a
secondElement (e1:e2:rest) = e2
```

From the deconstruction, haskell also creates a local binding that binds the first element into `e1`, the second element into `e2`, and the rest of the list into `rest`.
We can use this binding in the functions definition.

Since the `(e1:e2:rest)` pattern only matches lists with 2 elements or more, the pattern is non exhaustive.
We can add a definition that matches the wildcard pattern (`_`).
The wildcard pattern matches any expression, making our function exhaustive.

```haskell
secondElement :: [a] -> a
secondElement (e1:e2:rest) = e2
secondElement _ = error "list not long enough"
```

Make sure that the wildcard pattern appears last so that it doesn't subsume the expressions matched by `(e1:e2:rest)`.

You can also use the wildcard pattern to ignore binding unused parts of the data structure.
For example, for the first pattern, we only need the binding `e2` so we can replace the others with wildcards.

```haskell
secondElement :: [a] -> a
secondElement (_:e2:_) = e2
secondElement _ = error "list not long enough"
```

Here's another example, we can create a function that returns `True` if the list has exactly one element, and false if otherwise.

```haskell
isSolo :: [a] -> Bool
isSolo [_] = True
isSolo _ = False
```

Alternatively:

```haskell
isSolo :: [a] -> Bool
isSolo (_:[]) = True
isSolo _ = False
```

## Let Expressions

To create a local binding within an expression, you can use a let binding.
With a `let` expression like below, you can use `identifier` in the context of `expression`, its value will be `bound_expression`.

```haskell
let identifier = bound_expression in expression
```

**Examples**

```haskell
let x = 12 in 4 + x * x
```

evalauates to 148

```haskell
(let u = 13 in 2 + u) - (let u = 11 in 10 + u)
```

evaluates to -6

## Where blocks

You can create multiple local bindings inside the `where` block of functions.
All of the bindings inside the `where` block can be used in the scope of the function definition.

```haskell
function arg arg = definition
    where
        identifier1 = bound_expression1
        identifier2 = bound_expression2
        ...
```

**Examples**

```haskell
fullname :: [Char] -> [Char] -> [Char] -> [Char]
fullname fname mname lname = fname ++ " " ++ [minitial] ++ ". " ++ lname
    where minitial = head mname
```

You can even use pattern matching in your `where` blocks

```haskell
fullname :: [Char] -> [Char] -> [Char] -> [Char]
fullname fname mname lname = fname ++ " " ++ [minitial] ++ ". " ++ lname
    where (minitial:_) = mname
```

## Lambdas

```haskell
\param1 param2 ... paramn -> <some expression>
```

All identifiers between `\` and `->` are the parameters of the lambda. The expression to the right of `->` evaluates when the lambda is applied.

**Example**

A lambda that doubles a number

```haskell
\x -> x + x
```

A lambda that adds two numbers

```haskell
\a b -> a + b
```

The same function but written in its verbose uncurried form

```haskell
\a -> (\b -> a + b)
```
