# Haskell Cheat Sheet

## Setting up Haskell

To start writing Haskell code, install Haskell through stack. Stack is found in the folder called "Haskell/Stack" inside the provided course pack. Copy the Stack folder and place it in your computer. To be able to use stack anywhere, add your copy of the stack folder in the PATH variable of your computer.

Once stack has been set-up using the steps above, you can run the GHC repl using the command 

```
> stack ghci
```

The first time you run this code, stack will automatically install the GHC compiler. 

After downloading GHC, you will be taken to the Prelude part of the your GHC repl. To test if everything is working properly, try the following Haskell expression:

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

> If the path to the haskell file contains spaces, you need to enclose the path in braces

## Type annotations

**Pattern**

The last type in the arrow series is the range type or return type. Every type before it are the domain types or the types of the parameters in order

```haskell
function_name :: Type_of_Param1 -> Type_of_Param1 -> ... -> Type_of_Paramn -> ReturnType
```

**Examples**

Double of an integer, $\text{double} : \mathbb{Z} \to \mathbb{Z}$

```haskell
double :: Int -> Int
```

Sum of the length of two strings (strings in Haskell are arrays of `Char`, an array of specific types are written enclosed in square brackets, `[Type]` represents an array of `Type`s)

```haskell
len :: [Char] -> [Char] -> Int
```

Check if some integer is even (boolean values are written capitalized, `True` and `False`, )

```haskell
isEven :: Int -> Bool
```

Accept two `(Int -> Int)` functions and produce the composition of those functions (given functions $f$ and $g$, $f \circ g$)

```haskell
compose :: (Int -> Int) -> (Int -> Int) -> (Int -> Int)
```

## Function definitions

**Pattern**

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

## If-then-else expression

**Pattern**

One of haskell's condition expressions are if-then-else expression. Because a haskell expression is required to evaluate to something, unlike C, all `if` parts must be followed by a `then` part and `else` part. 

```haskell
if <bool-exp> then <exp1> else <exp2>
```

The expression inside the if clause must be an expression that evaluates into a boolean value. If the expression `<bool-exp>` evaluates to `True`, then the whole if-then-else expression evaluates to whatever `<exp1>` evaluates to. If `<bool-exp>` evaluates to `False`, then the whole if-then-else expression evaluates to whatever `<exp2>` evaluates to. The then clause and else clause cannot be empty and they must evaluate to the same type

> Haskell boolean literals start with uppercase letters, `True` and `False`.

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

evaluates to 3.

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

## Lambdas

**Pattern**

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

## Let Binding

To bind a value to some identifier use the `=` operator

**Pattern**

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

## Function/Lambda Application

**Pattern**

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