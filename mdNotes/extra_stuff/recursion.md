# Recursion

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

In lambda calculus, *all functions have fixed points*.
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


