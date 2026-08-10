# Advanced Constructs in Logic Programming

## Recursive Definitions

Similar to functional programming, logic programming represents repetition using recursion.
While functional programming makes heavy use of recursive functions to implement complex behavior, logic programming languages like Prolog uses recursive rules to model complex structures.
For example: consider the following knowledge base:

```prolog
is_ancestor(Parent, Child) :- is_parent(Parent, Child).
is_ancestor(Ancestor, Descendant) :-
    is_parent(Parent, Descendant), 
    is_ancestor(Ancestor, Parent).

is_parent(juan, francisco).
is_parent(cirila, francisco).
is_parent(teodora, jose).
is_parent(francisco, jose).
is_parent(brigida, teodora).
is_parent(lorenzo, teodora).
```

You'll notice that the rule `is_ancestor` is special since one of its goals is itself.
The query below will yield a `true` response due to the recursive nature of the `is_ancestor` rule.

```prolog
?- is_ancestor(juan, jose).
```

The query will match both rule heads, but the first instance (the base case) will lead to an unresolved goal since `is_parent(juan, jose)` cannot be resolved.
On the other hand the second instance of the rule (the recursive case), will lead to a propagation of goals that will resolve.

## Numbers in Logic Programming

Since logic calculus is a formalism for the foundation of mathematics, how do numbers emerge from predicates and propositions?

This is also another concept shared between, logic calculus and lambda calculus.
You can represent numerals (specifically integers) using Peano's axioms:

> 0 is an integer.

> The successor of an integer, denoted by s(n) is also an integer.

You can represent these axioms as a knowledge base:

```prolog
int(0).
int(s(X)) :- int(X).
```

This knowledge base will then define all the possible natural numbers out there, demonstrated by the query:

```prolog
int(X)
```

```prolog
X = 0
X = s(0)
X = s(s(0))
X = s(s(s(0)))
...
```

Using this representation, you can then define arithmetic operations such as addition and multiplication (also based on Peano's axioms) [@hosch_peano_2010].

$$
\begin{aligned}
&a + 0 = a\\
&a + b = c \to a + (b + 1) = (c + 1)
\end{aligned}
$$

$$
\begin{aligned}
&a * 0 = 0\\
&a * b = d \land a + d = c \to a * (b + 1) = c
\end{aligned}
$$

```prolog
int(0).
int(s(X)) :- int(X).

add(A,0,A).
add(A,s(B),s(C)) :- add(A,B,C).

mult(_,0,0).
mult(A,s(B),C) :- mult(A,B,D), add(A,D,C).
```

While this representation of numbers is a good way of demonstrating how numbers can be defined using logic, it's not really that usable.
We want to numbers to be easily readable, that's why we use base-10 Arabic numerals to represent numbers.
Prolog does have a builtin representation for numbers.
Numbers are accepted terms in the form of constants.
You can also apply some predicates to numbers like `=`, `<`, `>`[^infix].

```prolog
positive(X) :- X > 0.
```

```prolog
?- positive(2).
true.
```

[^infix]: Note that equality and inequality operators are also predicates. In the examples here they are written as infix operators, but you can still use them using the prefix functor syntax (i.e. `=(2,3)`)

Unfortunately, these representations are also very limited.
Predicates like `=`, `<`, and `>` break the logical nature of Prolog.
If you try to use these predicates with uninstantiated variables, you will end up with an error.

```prolog
?- X = 2.
Arguments are not sufficiently instantiated
In:
   [1] 2>_2258
```

To solve this limitation, the library `clp(fd)` and `clp(z)` was created.
The library `clp(fd)` stands for **Constraint Logic Programming Over Finite Domains**.
This library offers predicates that can be used to apply logical reasoning on integers.
This library was further refined to a more complete and more advanced library called `clp(z)` or **Constrained Programming Over Integers**.

These libraries include special predicates known as constraints.
**Constraints** are predicates that restrict a variable to a specific set of values.
Constraints are usually applied to *numerical variables* to define the domain of said variable.
The libraries, `clp(fd)` and `clp(z)` include the equality and inequality constraints [@triska_power_2026].

- `X #= Y`: $x = y$
- `X #< Y`: $x < y$
- `X #> Y`: $x > y$
- `X #\= Y`: $x \neq y$
- `X #>= Y`: $x \geq y$
- `X #=< Y`: $x \leq y$


To import libraries in your knowledge base, use the headless rule syntax[^clpfd_import].

```prolog
:- use_module(library(clpfd)).
```

[^clpfd_import]: Older implementations, like `swi-prolog` comes with `clpfd` but not `clpz`. Newer implementations like `scryer-prolog` come with both.

You can import it on the REPL by directly writing the `use_module` predicate as a query.
With the library loaded in the repl, you can use the constraints on your queries.

```prolog
?- use_module(library(clpfd)).
true.
```

When you use the `#<` constraint with a variable, it restricts said variable's domain to satisfy the constraint.

```prolog
?- X #< 3.
X in inf..2.
```

In the example above, with the constraint `X #< 3`, the integer variable `X` can only have values in the range $[-\infty, 2]$.

When used with numerical expressions, you can use Prolog as solver.

```prolog
?- 4 #= 2*X + 2.
X = 1.
```

Using constraint programming, will be limited when used as a solver since the domain is finite and you the answers are based on relations.
If the solution to your equation is not an integer, then the variable `X` doesn't have a valid integer that satisfies the constraint[^clpr].
In such case, Prolog will respond with `false`

```prolog
?- 0 #= 2*X + 3.
false.
```

[^clpr]: There are libraries that support constraint logical programming over real numbers like `clpr` which also comes bundled with `swi-prolog`.

You can combine multiple constraints into a conjunction to further control the domain of an integer.
In the example below, the values of `X` can only be in the range $[3,11] \cup [13,\infty]$[^inf_sup].

```prolog
?- X #>= 3, X #\= 12.
X in 3..11\/13..sup.
```

[^inf_sup]: `inf` stands for infimum, the smallest value in the domain and `sup` stands for supremum, the largest value in the domain.

You can also directly use the `in` predicate to create constraints over a domain expression.
In the example below, the domain ranges `0..10` and `9..12` are combined using the union operator, `\/`.

```prolog
?- X in 0..10 \/ 9..12.
X in 0..12.
```

To enumerate values in a domain, you can add the predicate `indomain/1` with a conjunction.

```prolog
?- X in 0..10, 1 #\= X mod 2, indomain(X).
X = 0 ;
X = 2 ;
X = 4 ;
X = 6 ;
X = 8 ;
X = 10.
```

## Data Structures

As discussed before, Prolog support compound terms in its syntax.
Depending on where a term appears in Prolog code, it can be treated as a predicate with arguments, or a term representing data.

```prolog
a(X).
b(c(d(Y)), e).
f(X) :- g(h), i(j).
k.
```

If a Prolog term appears as a fact, the head of a rule, or in the body of the rule, it is treated as a predicate with arguments.
In the example above, `a/1`, `b/2`, `f/1`, `g/1`, `i/1`, `k/0` are considered as predicates.
On the other hand, if a term appears as a predicate argument or a term argument, then it is treated as a term representing data.
In the example above, `X`, `c(d(Y))`, `d(Y)`, `Y`, `e`, `h`,`j`, are considered terms representing data.

### Compound data

Compound terms can be used to represent compound data, for example, you can represent a compound data structure that represents identity, using the generic term `id(Name, Age)`.

```prolog
:- use_module(library(clpfd)).

person(id(artman, 16)).
person(id(bartman, 19)).
person(id(cartman, 21)).
adult(Name) :-
    person(id(Name, Age)),
    Age #>= 18.
```

### Lists

Prolog uses a special representation for lists.
An **empty list** is represented by the special atom `[]`.
A special functor can be used to denote recursively from here.
For example, we can define use `list/2` to denote any list.
Any term defined as `list(Head, Tail)` is a *list*, if `Tail` is a *list*.

With this you can represent a list with *one element* as `list(elem1, [])`.
You can define a list with *two elements* as `list(elem1, list(elem2, []))`.

As the number of elements grow, this representation can be cumbersome, so Prolog uses a special syntax to lists with any number of elements using comma separated terms.
For example, a *list with four elements* can be written as `[elem1, elem2, elem3, elem4]`.
Prolog still allows us to use the *head-tail* pattern for defining lists as `[Head|Tail]`.
Such a list is valid if `Tail` is also a *valid list*.
For example, the four element list can be represented as `[elem1 | [elem2, elem3, elem4]]`.

```prolog
?- =([elem1, elem2, elem3, elem4], [elem1 | [elem2, elem3, elem4]]).
true.
```

You can also combine, comma separated lists with the head-tail pattern as such:

```prolog
?- =([elem1, elem2, elem3, elem4], [elem1, elem2 | [elem3, elem4]]).
true.
```

You can use the `[Head|Tail]` pattern to create interesting predicates involving lists.
For example, you can find the length of a list by creating the following rule:

```prolog
:- use_module(library(clpfd)).

list_length([], 0).
list_length([Head|Tail], Length) :-
    Length #= Tail_length + 1,
    list_length(Tail, Tail_length).
```

```prolog
?- list_length([a,b,c,d],X).
X = 4.
```

This predicate is very similar to the way a list's length is calculated in functional programming.
But it can be used in more interesting ways.
For example, we can create the predicate `list_nth_element/3`.
Given, `List`, `N`, and `Elem`, `list_nth_element(List, N, Elem)` is true if `Elem` is found on index `N` in the list.
To create this predicate, we first define a helper predicate called, `list_take_n/3`.
Given `List`, `N`, `Leftover`, `list_take_n(List, N, Leftover)` is true if, `Leftover` is the list you end up with after taking `N` elements from `List`. 

```prolog
:- use_module(library(clpfd)).

list_take_n(List,0,List).
list_take_n([Head|Tail], N, Leftover) :-
    N #> 0,
    M #= N - 1,
    list_take_n(Tail, M, Leftover).

list_nth_element(List, N, Elem) :-
    list_take_n(List, N, [Elem|Tail]).
```

With this `list_nth_element/3` created, we can find the nth element of a list.

```prolog
?- list_nth_element([a,b,c,d,e],2,X).
X = c.
```

But at the same time, we can also use `list_nth_element/3` to check which indices an element is at.

```prolog
?- list_nth_element([a,q,c,q,e],Index,q).
Index = 1 ;
Index = 3 ;
false.
```
[^false_branch]

[^false_branch]: The extra `false` branch is a result of SLD resolution unifying the query to both the fact and the rule head in the knowledge base.

We can write such queries because we are working with predicates that act as *pure relations*.
Pure relations, unlike functions *do not have a set input and output*.
A correctly written predicate should work no matter which argument is replaced by a variable.

