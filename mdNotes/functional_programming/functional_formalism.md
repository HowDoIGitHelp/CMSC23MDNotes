# Functional Programming Formalism

During the 1930s a mathematician investigating the foundation of mathematics, named Alonzo Church, introduced a formal system of expressing computational logic.
The system he created was called **Lambda Calculus**.
It was until the 1960s when the system found its way through different disciplines.
It became something more than a mathematical formalism and became an important concept in linguistics and **computer science** [@church_set_1932].

Before we dive into functional programming let's introduce ourselves to the formalism that inspired it, Lambda Calculus.
These concepts may seem strange at first since it imagines a mathematical foundation beyond numbers, sets, and logic.

## Expressions in the Lambda Calculus Formalism

Let $\Lambda$ be the set of expressions under the Lambda calculus formalism

1. **Variables**. If x is a variable, then $x \in \Lambda$ 
2. **Abstractions**. If $x$ is a variable and $\mathcal{M} \in \Lambda$, then $(\lambda x. \mathcal{M}) \in \Lambda$.
3. **Applications**. If $\mathcal{M} \in \Lambda \land \mathcal{N} \in \Lambda$, then $(\mathcal{M} \mathcal{N}) \in \Lambda$.

Take a look at these important precedence conventions.
You might get confused if you read some lambda calculus expressions.
Some people often omit parentheses or single-parametrizations to write shorter expressions:

1. Application is left associative

   $$
   \mathcal{M_1}\mathcal{M_2}\mathcal{M_3} = ((\mathcal{M_1}\mathcal{M_2})\mathcal{M_3})
   $$

2. Consecutive abstractions can be uncurried

   $$
   \lambda xyz.\mathcal{M}=\lambda x.\lambda y.\lambda z.\mathcal{M}
   $$

3. The body of an abstraction extends to the right

   $$
   \lambda x.\mathcal{M}\mathcal{N}=\lambda x.(\mathcal{M}\mathcal{N})
   $$

## Reductions

Reductions are a ways to simplify and evaluate lambda expressions.
You'll learn later that these reductions are basically concepts that are eventually adapted to functional programming concepts.

### $\alpha$ equivalence:

$\alpha$ equivalence states that any bound variable, has no inherent meaning and can be replaced by another variable:

$$
\lambda x.x =_\alpha \lambda y.y
$$

Given a lambda calculus abstraction $\lambda x.
\mathcal{M}$, this abstraction's bound variable is $x$.
The bound variable $x$ may appear somewhere in $\mathcal{M}$, the body of the abstraction.
An alpha equivalence basically shows that the name of the variable has no inherent meaning.
Therefore, you can replace it with any other variable name.

### $\beta$ Reductions

$\beta$ reductions state how to simplify abstractions.
This process is similar to applying a function in the context of programming.
For example, we use the identity function ($\lambda x.x$) and apply it to some free variable $y$.

$$
(\lambda x.x)y=_\beta y
$$

When you beta-reduce some application $\mathcal{M}\mathcal{N}$, what you're doing is replacing all instances of the bound variable in $\mathcal{M}$ with $\mathcal{N}$.
Here's another example, 

$$
(\lambda u. \lambda v.uvu)\lambda x.x =_{\beta} \lambda v.(\lambda x.x)v(\lambda x.x)
$$


### $\eta$ reductions

$\eta$ reductions describe equivalencies that arise because of free variables.
If $x$ is a variable and does not appear free in $\mathcal{M}$ then:

$$
\lambda x.(\mathcal{M}x) =_\eta \mathcal{M}
$$

The lambda expression here is just some redundant abstraction.
These $\eta$ reductions characterize higher level simplifications that are not always as obvious as the other reductions.

### Reduction example

For example, to reduce the following lambda expression, we must first understand what it means.

$$
(\lambda x.\lambda y.(xy))(\lambda x.\lambda y.(xy))
$$

In the outermost level, the expression is the application of $\lambda x.\lambda y.(xy)$ to itself.
It follows the second type of lambda calculus expression discussed earlier, $\mathcal{M}\mathcal{N}$ where $\mathcal{M}\in \Lambda$ and $\mathcal{N}\in \Lambda$.
 In this context $\mathcal{M} = (\lambda x.\lambda y.(xy))$ and also $\mathcal{N} = (\lambda x.\lambda y.(xy))$.


When you start evaluating this expression, you might be tempted to automatically apply a $\beta$ reduction by itself:

$$
\begin{aligned}
(\lambda x.\lambda y.(xy))(\lambda x.\lambda y.(xy))&=_{\beta}\lambda y.((\lambda x.\lambda y.(xy))y)\\
&=_{\beta}\lambda y.\lambda y.(yy)
\end{aligned}
$$

But this reduction is actually incorrect because the although $x$ and $y$ appear on both lambda expressions, these variables don't have the same meaning.
The $x$ and $y$ variables inside the left lambda expression are **bound** inside this lambda expression.
The $x$ and $y$ variables outside the left lambda expression (inside the right lambda expression) are **free** in its context, therefore, even though they look the same, it is incorrect to interchange the two variables.

Two avoid confusion with similarly named variables, it is advisable to apply $\alpha$ equivalencies, to give variables different names.
This can be done by replacing the right abstractions' bound variables with $u$ and $v$.
Again, this alpha reduction doesn't change the meaning of the abstraction, it merely renames the bound variables.

$$
(\lambda x.\lambda y.(xy))(\lambda x.\lambda y.(xy))=_\alpha(\lambda x.\lambda y.(xy))(\lambda u.\lambda v.(uv))
$$

The correct reduction now is as follows.
Still a $\beta$ reduction but without the ambiguity of similar variable names.

$$
\begin{aligned}
(\lambda x.\lambda y.(xy))(\lambda u.\lambda v.(uv))&=_\beta \lambda y.((\lambda u.\lambda v.(uv))y)\\
&=_\beta \lambda y.\lambda v.(yv)
\end{aligned}
$$

## Lambda Calculus Encoding (Optional Reading)

One of the most interesting things that the Lambda Calculus System demonstrates is its use in computability theory.
Its importance in computing has led to the formulation of the Church-Turing Thesis which conjectures that the Lambda Calculus System and the hypothetical Turing machine rules as complete representations of any algorithm.
The lambda calculus system is said to be **Turing Complete**, which means that the system can simulate any conceivable Turing machine.
To demonstrate its Turing completeness this topic will show some of the encodings in lambda calculus.

### Boolean Values

A good starting point for encoding is the smallest unit of data, a boolean value.
Boolean values, such as true and false can be represented by the following lambda expressions:

- **True:** $T=\lambda x. \lambda y. x$
- **False:** $F=\lambda x. \lambda y. y$

The way boolean values are encoded in lambda calculus using an abstraction that when applied to two values, produces first value for true and produces the second value for false.
To demostrate the consistency of this encoding we can use this encoding of some `if_then_else` function.
A general `if_then_else` function in the context of any familiar programming language looks like this:

```python
if(condition)
	this
else
	that 
```

The function consumes three expressions (`condition`,`this`, and `that`) If the `condition` is true, then the function produces `this`, otherwise the function produces `that`.
This function can be represented in lambda calculus as the following function:

$$
\lambda c.\lambda x. \lambda y. cxy
$$

$c$ : `condition`, $x$ : `this`, $y$ : `that`

Applying this function shows how lambda calculus boolean encodings work.
The example below shows what happens when the condition is applied to a true condition

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

[^alpha_eq]

[^alpha_eq]: In the first line a quick $\alpha$ equivalency is done on the `True` encoding.

The example below in the other hand is the same `if_then_else` function applied to a false condition

$$
\begin{aligned}
(\lambda c.\lambda x. \lambda y. cxy)Fab &= (\lambda c.\lambda x. \lambda y. cxy)(\lambda u. \lambda v. v)ab\\
&=(\lambda u. \lambda v. v)ab\\
&=b
\end{aligned}
$$

[^beta_eq]

[^beta_eq]: In the effort of saving lines $\beta$ reductions with currying are applied in one go.

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

Natural numbers are encoded in lambda calculus similar to how numbers are described using *Peano's Axioms*.
By defining $0$ and defining a successor function.
This encoding is called Church numerals.
The first natural number $0$ is defined as the lambda expression:

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
| $\overline{0}$ |   $\lambda s. \lambda z .z$    |
| $\overline{1}$ |  $\lambda s.\lambda z.(s(z))$   |
| $\overline{2}$ | $\lambda s.\lambda z.(s(s(z)))$ |
|    $\vdots$    |            $\vdots$             |
| $\overline{n}$ | $\lambda s. \lambda z. s^n(z)$[^superscript]  |

[^superscript]: $\mathcal{M}^n(\mathcal{N})$ is a shorthand notation for $n$ applications of $\mathcal{M}$ to $\mathcal{N}$ or $\mathcal{M}(\cdots(\mathcal{M}(\mathcal{M}(\mathcal{N})))\cdots)$.

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

Multiplication works similarly.
To solve for the product of two Church numerals $\overline{m}$ and $\overline{n}$, we simply add $n$ to $0$ repetitively, $m$ number of times.
The function will look similar to a $\text{add}$, but with a partial application of $\text{add } n$ instead of the successor function $S$.

$$
\text{mult }=\lambda m.\lambda n. m (\text{add }n)\overline{0}
$$
