# Recursion from Fixed Point Combinators

Haskell and other functional programming languages are based on lambda calculus formalism.
But how is recursion achieved from lambda calculus?

In lambda calculus you've seen how we sometimes give lambda calculus expressions identifiers.
We do this to for convenience.
This allows us to define lambda calculus expressions based on other lambda calculus expressions.

For example, we define the successor function[^successor] as the following.

$$
S(\overline{n})=S(\lambda s. \lambda z. s^n(z))=\lambda s. \lambda z. s(s^n(z))=\lambda s. \lambda z. s^{n+1}(z)
$$

[^successor]: Refer to [Addition and Multiplication](/functional-formalism#addition-and-multiplication).

We can reuse the successor functions definition in the definition for the addition functions.

$$
\text{add}=\lambda m.\lambda n. mSn
$$

The identifiers $S$ and $add$ are just a means for us to avoid rewriting definitions.
If we want to, functions can be truly anonymous.
This becomes an issue when it comes to recursive functions.
In such cases the function being defined contains a reference to itself.

```haskell
summation :: Int -> Int
summation 0 = 0
summation n = n + summation (n - 1)
```

To simulate recursion in the formalism of lambda calculus, we use special functions, called **fixed point combinators**.
A fixed point of a given function, is a value, which has the property of being its own image.
Generally, if you have a function $f$, $u$ is a fixed point of $f$ if $f(u) = u$.
In the example below, we have a quadratic function with two fixed points.

$$
\begin{aligned}
f(x) = x^2 + 3x -3\\
f(-3) = 9 - 9 - 3\\
f(-3) = -3\\
\\
f(1) = 1 + 3 - 3\\
f(1) = 1
\end{aligned}
$$

In the example below, the horizontal shear transformation has infinitely many fixed points.

$$
\begin{aligned}
\begin{bmatrix}
1 & 1\\
0 & 1
\end{bmatrix}
\begin{bmatrix}
u\\
0
\end{bmatrix} = 
\begin{bmatrix}
u\\
0
\end{bmatrix} 
\end{aligned}
$$

All functions in lambda calculus have `fixed points`.
This can be proven using a *fixed point combinator*.
A fixed point combinator is a special function that can produce the fixed point of any lambda calculus function.
There are many fixed point combinators, the example below, known as the Y-combinator is discovered by Haskell Curry.

$$
Y = \lambda f.(\lambda x. f(x x))(\lambda x. f(x x))
$$

We can show that this is indeed a fixed point combinator by applying it to a general function $F$.

$$
\begin{aligned}
Y F &= (\lambda f.(\lambda x. f(x x))(\lambda x. f(x x))) F\\
&= (\lambda x. F(x x))(\lambda x. F(x x))\\
&= F((\lambda x. F(x x)) (\lambda x. F(x x)))\\
\\
Y F &= F(Y F)
\end{aligned}
$$

To end up with the fixed point property $Y F = F (Y F)$, we substitute $Y F$, with the second reduction.
This shows any function $F$ has a fixed point $Y F$.

If we apply the fixed point combinator to the successor function $S$, we end up with an infinitely expanding repeated applications of $S$.

$$
\begin{aligned}
Y S &= S(Y S)\\
Y S &= S( S (Y S)))\\
Y S &= S( S (S(Y S)))\\
&\vdots\\
Y S &= S( S (S( \cdots S (Y S) \cdots )))\\
\end{aligned}
$$

You can write a fix point combinator in Haskell by using the fix point property $Y F = F (Y F)$.

```haskell
y :: (a -> a) -> a
y f = f (y f)
```

As you can see this definition contains a recursive call.
We can avoid a recursive call by using the property of all fix points: $u$ is a fixed point if $f(u) = u$[^circular_definition].

```haskell
y :: (a -> a) -> a
y f = u
    where u = f u
```

In fact this is very similar to how the `fix` function is defined, Haskell's built-in fixed point combinator[^circular_definition].

```haskell
fix :: (a -> a) -> a
fix f = let x = f x in x
```

[^circular_definition]: The where clause `u = f u` from `y` and let clause `x = f x` are circularly dependent definitions that might be a problem for other languages. But since Haskell is lazily evaluated, it will not end up being a syntax error.

When you use the fixed point combinator on any `(a -> a)` function, it also ends up as repetitive application of a function, repeated an infinite amount of times.
For example, given the successor function `s`.

```haskell
s :: Int -> Int
s n = n + 1
```

When applying the `fix` to `s`, the evaluation looks like this:

```haskell
fix s
let x = f x in x
let x = f x in (f x) --substitute value of x with (f x)
let x = f x in (f (f x)) --substitute value of x with (f x) again
let x = f x in (f (f (f x)))
...
let x = f x in (f (f (f ... (f x) ...)))
```

Note that what actually happens under the hood in Haskell is more complicated than this, but in for the purposes of visualization, let's assume that this is correct.

We can use this repetitive nature of `fix` to show how recursion can be achieved without recursive calls.

Using the summation function as an example:

```haskell
sum :: Int -> Int
sum 0 = 0
sum n = n + sum (n + 1)
```

First, we convert summation into a higher order function that accepts some `r :: (Int -> Int)` as a first argument and some `n :: Int` as a second argument.

```haskell
sum :: (Int -> Int) -> Int -> Int
sum r 0 = 0
sum r n = n + sum (n - 1)
```

We then replace the recursive call to `sum` with `r`

```haskell
sum :: (Int -> Int) -> Int -> Int
sum r 0 = 0
sum r n = n + r (n - 1)
```

To complete the repetition, we apply `fix` to `sum`.
The resulting function will be our completed `summation` function.

```haskell
summation = fix sum
```

To see how this is equivalent to the recursive version, we can simulate its evaluation when `summation` is applied to `3`.

```haskell
summation 3
(fix sum) 3
(let x = sum x in x) 3
(let x = sum x in (sum x)) 3 --evaluate the partial application, (sum x)
(let x = sum x in (\n -> sum x n)) 3
(let x = sum x in (sum x 3)) --apply lambda to 3
(let x = sum x in (3 + x 2)) --evaluate (sum x 3)
(let x = sum x in (3 + (sum x) 2)) --evaluate x based on x = sum x
(let x = sum x in (3 + (\n -> sum x n) 2))
(let x = sum x in (3 + (sum x 2)))
(let x = sum x in (3 + (2 + x 1)))
(let x = sum x in (3 + (2 + (sum x) 1)))
(let x = sum x in (3 + (2 + (sum x 1)))) --partial application and apply to 1
(let x = sum x in (3 + (2 + (1 + x 0))))
(let x = sum x in (3 + (2 + (1 + (sum x) 0))))
(let x = sum x in (3 + (2 + (1 + (sum x 0)))))
(let x = sum x in (3 + (2 + (1 + 0)))) --evaluate sum x 0 = 0 (base case)
(3 + (2 + (1 + 0))) --evaluate the let binding
6
```

You can achieve the same result in fewer steps using the fix point `y f = f (y f)`.

Actual recursion in haskell is achieved using a cyclical graph, which is not too dissimilar to the circularly dependent fix point `let x = f x in x`.
