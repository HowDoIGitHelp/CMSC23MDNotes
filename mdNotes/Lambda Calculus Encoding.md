
# Lambda Calculus Encoding (Optional Reading)

One of the most interesting things that the Lambda Calculus System demonstrates is it's use in computability theory. It's importance in computing has led to the formulation of the Church-Turing Thesis which conjectures that the Lambda Calculus System and the hypothetical Turing machine rules as complete representations of any algorithm. The lambda calculus system is said to be **Turing Complete**, which means that the system can simulate any concievable Turing machine. To demonstrate its turing completeness this topic will show some of the encodings in lambda calculus.

### Boolean Values

A good starting point for encoding is the smallest unit of data, a boolean value. Boolean values, such as true and false can be represented by the following lambda expressions:

- **True:** $T=\lambda x. \lambda y. x$
- **False:** $F=\lambda x. \lambda y. y$

The way boolean values are encoded in lambda calculus using an abstraction that when applied to two values, produces first value for true and produces the second value for false. To demostrate the consistency of this encoding we can use this encoding of an `if_then_else` function. An `if_then_else` function in the context of any familiar programming language looks like this:

```python
if(condition)
	this
else
	that 
```

The function consumes three expressions (`condition`,`this`, and `that`) If the `condition` is true, then the function produces `this`, otherwise the function produces `that`. This function can be represented in lambda calculus as the following function:
$$
\lambda c.\lambda x. \lambda y. cxy
$$

> $c$ : `condition`, $x$ : `this` ,$y$ : `that`

Applying this function shows how lambda calculus boolean encodings work. The example below shows what happens when the condition is applied to a true condition
$$
\begin{aligned}
(\lambda c.\lambda x. \lambda y. cxy)Tab &= (\lambda c.\lambda x. \lambda y. cxy)(\lambda u. \lambda v. u)ab\\
&=(\lambda x. \lambda y. ((\lambda u. \lambda v. u)xy))ab\\
&=(\lambda x. \lambda y. ((\lambda v. x)y))ab\\
&=(\lambda x. \lambda y. x)ab\\
&=(\lambda y. a)b\\
&=a\\
\end{aligned}
$$

> In the first line a sneaky $\alpha$ equivalency is done on the `True` encoding.

The example below in the other hand is the same `if_then_else` function applied to a false condition
$$
\begin{aligned}
(\lambda c.\lambda x. \lambda y. cxy)Fab &= (\lambda c.\lambda x. \lambda y. cxy)(\lambda u. \lambda v. v)ab\\
&=(\lambda u. \lambda v. v)ab\\
&=b
\end{aligned}
$$

> In the effort of saving lines $\beta$ reductions with currying are applied in one go.

By representing the `if_then_else` function in lambda calculus we are also able to encode all the logic gates by reusing our `if_then_else` function.

#### `not` function

The `not` function can be represented using the`if_then_else` function this way:

```python
if(condition)
	False
else
	True
```

In lambda calculus:
$$
\lambda x.xFT
$$

#### `or` Function

The `or` function can be represented using the`if_then_else` function this way:

```python
if(left)
	True
else
	right
```

In lambda calculus:
$$
\lambda x. \lambda y. xTy
$$

#### `and` Function

The `or` function can be represented using the`if_then_else` function this way:

```python
if(left)
	right
else
	false
```

In lambda calculus:
$$
\lambda x. \lambda y. xyF
$$

### Natural Numbers

Natural numbers are encoded in lambda calculus similar to how numbers are described using *Peano's Axioms*. By defining $0$ and defining a successor function. This encoding is called Church numerals. The first natural number $0$ is defined as the lambda expression:
$$
\overline{0} = \lambda s. \lambda z .z
$$
Which is $\alpha$ equivalent to the encoding for boolean false.

All other natural numbers are derived using a special function called the successor function, such that the succesor of any natural number $n$ is $n+1$. The successor function in lambda calculus is represented by:
$$
S=\lambda n. \lambda s. \lambda z. (s(nsz))
$$
Using this function we can derive the encoding for the nfireatural number $\overline{1}$:
$$
\begin{aligned}
\overline{1}=S(\overline{0})&=(\lambda n. \lambda s. \lambda z. (s(nsz)))(\lambda u. \lambda v .v)\\
&=\lambda s.\lambda z.(s((\lambda u. \lambda v .v)sz))\\
&=\lambda s.\lambda z.(s(z))
\end{aligned}
$$
To derive this encoding for two we simply find $S(\overline{1})$.
$$
\begin{aligned}
\overline{2}=S(\overline{1})&=(\lambda n. \lambda s. \lambda z. (s(nsz)))(\lambda u.\lambda v.(u(v)))\\
&=\lambda s.\lambda z.(s((\lambda u.\lambda v.(u(v)))sz))\\
&=\lambda s.\lambda z.(s(s(z)))
\end{aligned}
$$
Continuing this process and generalizing successions will lead us the following encoding of natural numbers:

| Church Numeral |    Lambda Calculus Encoding     |
| :------------: | :-----------------------------: |
| $\overline{0}$ |   $ \lambda s. \lambda z .z$    |
| $\overline{1}$ |  $\lambda s.\lambda z.(s(z))$   |
| $\overline{2}$ | $\lambda s.\lambda z.(s(s(z)))$ |
|    $\vdots$    |            $\vdots$             |
| $\overline{n}$ | $\lambda s. \lambda z. s^n(z)$  |

>$\mathscr{M}^n(\mathscr{N})$ is a shorthand notation for $n$ applications of $\mathscr{M}$ to $\mathscr{N}$ or $\mathscr{M}(\cdots(\mathscr{M}(\mathscr{M}(\mathscr{N})))\cdots)$.

Another useful definition of a successor function would be:
$$
S(\overline{n})=S(\lambda s. \lambda z. s^n(z))=\lambda s. \lambda z. s(s^n(z))=\lambda s. \lambda z. s^{n+1}(z)
$$

#### Addition and Multiplication

The successor function will help us derive the lambda calculus representation of an addition function. In the same sense that addition is just a repetition of increments, we can implement addition by making use of repetitive applications of the successor function.
$$
\text{add}=\lambda m.\lambda n. mSn
$$
To test the consistency of this function, we can try adding two arbitrary Church numerals $\overline{m}$, and $\overline{n}$. 
$$
\begin{aligned}
\text{add } \overline{m}\overline{n}&=(\lambda m. \lambda n. mSn)\overline{m}\overline{n}\\
&=\overline{m}S\overline{n}\\
&=(\lambda s. \lambda z. s^{m}(z))S(\lambda a. \lambda b. a^{n}(b))\\
&=(\lambda z.S^m(z))(\lambda a. \lambda b. a^{n}(b))\\
&=S^m(\lambda a.\lambda b.a^n(b))\\
&=\lambda a.\lambda b.a^{m+n}(b)\\
&=\lambda s. \lambda z. s^{m+n}(z)\\
&=\overline{m+n}
\end{aligned}
$$
Multiplication works in a similar manner. To solve for the product of two Church numerals $\overline{m}$ and $\overline{n}$, we simply add $n$ to $0$ repetitively, $m$ number of times. The function will look similar to a $\text{add}$, but with a partial application of $\text{add } n$ instead of the successor function $S$.
$$
\text{mult }=\lambda m.\lambda n. m (\text{add }n)\overline{0}
$$

