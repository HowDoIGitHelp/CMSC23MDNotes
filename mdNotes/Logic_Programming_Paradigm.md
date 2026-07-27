# Logic Programming Paradigm

## Introduction

Just as functional programming paradigm is patterned from the formalisms of lambda calculus, logic programming is patterned from predicate calculus. Computer Scientists usually describe families of programming languages under the logic paradigm as a sub-paradigm of declarative programming (*declarative programming being any paradigm that is not imperative*).  In terms of application this paradigm is more closely related to  knowledge base programming languages like SQL. While SQL uses relations (not tables) to represent knowledge, logic programming uses rules of logic and predicate calculus to represent knowledge. 

## Learning Outcomes

1. Create prolog facts, rules, and queries
2. Explain the process of unification
3. Explain how proof search is used to respond to queries
4. Create recursive prolog rules

---

For this secton we will use the programming language Prolog as the representative of logic paradigm. Other logic programming families are answer set programming, ABYSS and Datalog.

### Facts Rules and Queries

#### Facts

There are three basic constructs in Prolog, facts, rules and queries. A knowledge base is a collection of facts and rules in the same way a c library or a python package is a collection of function definitions. Prolog programs are basically knowledge bases. Here's an example of a knowledge base:

```prolog
firetype(charmander).
firetype(charizard).
watertype(squirtle).
flyingtype(charizard).
```

Each line of code you can in this particular knowledge base is a fact. Just like facts, in logic, facts in Prolog are propositions that are known to be true. This means that your program knows four things. One of those are:

```prolog
firetype(charmander).
```

In Prolog `firetype(X)` represents the mathematical predicate you've learned in discrete math, $\text{firetype}(x)$. And just like predicates, this is attached to the meaning `X` is firetype. 

Therefore the fact `firetype(charmander)` represents the proposition, "charmander is firetype"

So, to summarize, the fact `firetype(charmander)` is basically a representation of the proposition, $firetype("charmander")$ where $firetype$ is a predicate and $charmander $ is a value assigned to the predicate. Any fact that can be found on the knowledge base are basically true propositions and any proposition that is not in the knowledge base (and cannot be inferred from the knowledge base) are false.

#### Queries

A knowledge base is used by writing queries to prolog. You can probably guess what this prolog construct means just by looking at its name. Queries represent questions you ask prolog. The answer to a question depends on what prolog knows. And what prolog knows is represented by the knowledge base. For example if you load the knowledge base we created earlier. We can ask prolog the following question below. Writing the following will basically ask prolog, "hey, is it true that charmander is firetype?"

```prolog
?- firetype(charmander).
```

Based on the knowledge base loaded earlier prolog knows that this proposition is indeed true. Therefore it responds with:

```prolog
yes
```

If prolog is asked with the query

```prolog
?- firetype(squirtle).
```

Prolog checks its knowledge base again. Realizing that none of the facts match this proposition, prolog responds with:

```prolog
no
```

Therefore, if you provide prolog with statements it has never seen before like the following:

```prolog
?- grasstype(pigeot)
```

Prolog will interpret this as false statement, responding appropriately with:

```prolog
no
```

#### Rules

Aside from facts, you can also define rules in your knowledge base. To illustrate this, lets add rules to our knowledge base.

```prolog
firetype(charmander).
firetype(charizard).
watertype(squirtle).
flyingtype(charizard).

resistanttofire(squirtle) :- watertype(squirtle).
```

A rule is basically an implication statement. A rule written as `c :- h` is equivalent to the implication statement $h \to c$. Prolog rules are written using the "c if h", the reverse of a conventional "if-then" implication "statement. A lot of people get confused here so just remember, `:-` is read as if. Therefore, the left half of a rule is the conclusion and the right half is the hypothesis.

Since we've written that rule we can ask prolog the following:

```prolog
?- resistanttofire(squirtle)
```

Prolog responds with

```prolog
yes
```

Although `resistanttofire(squirtle)` is not written as a fact it can be inferred from the rule `resistanttofire(squirtle) :- watertype(squirtle)` and the fact `watertype(squirtle)`. Therefore it is true via modus ponens:
$$
\begin{aligned}
&watertype(squirtle) \to resistanttofire(squirtle)\\
&watertype(squirtle)\\
\hline 
\therefore ~& resistanttofire(squirtle)
\end{aligned}
$$

#### Variables

Another important thing about Prolog constructs is that you can write them with variables. For example, writing the query:

```prolog
?- firetype(X)
```

Basically asks the question, which values when substituted to `X` in the predicate `firetype(X)`  will yield true statements? This can be interpreted in natural language as "which Pokémon are fire type?" Therefore, this query will yield the response:

```prolog
X = charmander
X = charizard
```

Variables inside facts and rules allows the creation of richer knowledge bases. Instead of the rule `resistanttofire(squirtle) :- watertype(squirtle).` we can write a more general rule using variables:

```prolog
firetype(charmander).
firetype(charizard).
watertype(squirtle).
flyingtype(charizard).

isresistantto(X,Y) :- watertype(X),firetype(Y).
isresistantto(X,Y) :- watertype(X),watertype(Y).
```

This introduces a more complicated rule `isresistanto(X,Y) :- watertype(X),firetype(Y)`. This rule's premise  is a conjunction of predicates `watertype(X)` and `firetype(Y)`.  

If we imagine that the predicate, $isresistantto(x,y)$ means "x is resistant to y", the whole rule can be interpreted as 

>  for all pairs of X and Y, X is resistant to Y, if  X is water type and Y is fire type,

This statement, can be written as the following quantification statement:
$$
\forall x \forall y ((watertype(x) \land firetype(y)) \to isresistantto(x,y))
$$
By writing this rule, prolog can infer the following facts:

```prolog
?- isresistantto(squirtle,charmander)
```

```prolog
yes
```

```prolog
?- isresistantto(squirtle,charizard)
```

```prolog
yes
```

```prolog
?- isresistantto(squirtle,squirtle)
```

```prolog
yes
```

If you ask prolog a harder question like the following:

```prolog
?- isresistantto(squirtle,X)
```

Prolog interprets this as "which values of `X` make the proposition: squirtle is resistant to X, true? Therefore, Prolog will look for the pokemon, squirtle is resistant to, therefore you with the output:

```prolog
X = charmander
X = charizard
X = squirtle
```

#### Prolog Syntax

There are three types of prolog terms [^1]. By composing these terms you can express rich knowledge bases.

1. Constants. These can either be atoms (known to us as strings such as `squirtle` ) or numbers (such as `24`).  

2. Variables. (Those that start with an underscore or any uppercase letter such as as `X`, `Z3`,`_4310`,  and `List`.)  

3. Complex terms. These have the form: `functor(term_1,...,term_n)`. We've seen examples of these in predicates and queries such as `firetype(charmander)` and `isresistantto(X,Y)`

### Unification

The way prolog is able to respond to complex queries such as:

```prolog
?- firetype(X)
X = charmander
X = charizard
```

is through the use of the logical concept known as **unification**. Unification algorithmically identify logical substitutions in symbolic expressions such as prolog facts, queries and rules. Unification is defined in prolog as the following:

>  Two terms unify if they are the same term or if they contain variables that can be uniformly instantiated with terms in such a way that the resulting terms are equal. 
>

This definition gives us the unification of trivial cases such as the unification of constants `squirtle` and `squirtle`. Prolog also unifies the complex terms `watertype(squirtle)` and `watertype(squirtle)` and the variables `X` and `X`. The complex terms `watertype(squirtle)` and `watertype(blastoise)` will not unite.

Prolog also unifies the variable `X` with the constant `squirtle`. Although they are not the same, the variable `X` can be uniformly instantiated to `squirtle` (i.e. `X = squirtle`). What this specific unification case means is that, you can find some binding of the constant `squirtle` to the variable `X` without breaking other unification rules. This successful binding means that these values indeed unify. By the same intuition, `watertype(X)` and `watertype(squirtle)` will also unify buy instantiating (`X=squirtle`). 

On the other hand the complex terms `isresistantto(X,charmander)` and `isresistantto(squirtle,X)` does not unify since you cannot find an instantiation of `X` that makes them equal. The instantiation `X=squirtle` evaluates to the terms `isresistantto(squirtle,charmander)` and `isresistantto(squirtle,squirtle)`. On the other hand, the instantiation `X=charmander` makes the terms `isresistantto(charmander,charmander)` and `isresistantto(squirtle,charmander)`. Both of these scenarios break because it forces the incorrect unification of the constant `squirtle` and `charmander`.

The process of unification can be summarized by the following[^1]: 

> Two terms $a$ and $b$ unify if and only if
>
> 1. $a$ and $b$ are constants and they are the same number or atom
> 2. $a$ is a variable and $b$ is any type of term (in this case $a$ is instantiated to $b$) or $b$ is a variable and $a$ is any type of term (in this case $b$ is instantiated to $a$). This rule automatically unify any pair of variables
> 3. $a$ and $b$ are complex terms and:
>    1. They have the same functors and the same number of arguments
>    2. all their corresponding arguments unify
>    3. the variable instatiations are uniform or compatible (you cannot instantiate $x$ to some constant $a$ when unifying a pair and instantiate $x$ to another constant $b$ when unifying another pair of arguments)

You can demonstrate unification in the prolog terminal using the predicate `=/2` (this means the `=` functor with two arguments).

```prolog
?- =(squirtle,squirtle)
yes
```

```prolog
?- =(squirtle,charmander)
no
```

```prolog
?- =(squirtle,X)
X=squirtle
yes
```

```prolog
?- =(X,Y)
X=_5071
Y=_5071
yes
```

> The instantiations `X=_5071` and `Y=_5071` asserts that both `X` and `Y` share the same variables in this case. This is also known as `X` and `Y` being aliased, meaning that they share each others instantiations

```prolog
?- =(watertype(X),watertype(squirtle))
X=squirtle
yes
```

```prolog
?- =(f(g(X),X),f(Y,a))
X=a
Y=g(X)
yes
```

#### Programming with unification

Unification is crucial with how one can write interesting logic programs. By creating knowledge bases that take advantage of unification, you can generalize structures based on the facts and rules of its characteristics. For example, the following is a knowledge base describing the characteristics of vertical and horizontal lines:

```prolog
vertical(line(point(X,Y),point(X,Z))).
horizontal(line(point(X,Y),point(Z,Y))). 
```

Instances of  line that unify with these predicates, are also instances of vertical and horizontal line. Therefore asking the query:

```prolog
?- vertical(line(point(1,2),point(1,3)))
```

Will yield the response:

```prolog
yes
```

It is indeed a vertical line. And the knowledge base makes sense, since any line that has the same x coordinate is vertical and any line that has the same y coordinate is horizontal. 

We can even ask more general queries to haskell such as:

```prolog
?- horizontal(line(point(2,3),point(Y,4)))
```

Which basically asks prolog for horizontal lines starting at $(2,3)$ and ends at a point with $4$ as the $y$-coordinate. Since prolog can't unify this query with any value for $Y$ (horizontal lines must have the same $y$-coordinate), prolog responds:

```prolog
no
```

### Proof Search

Here we will discuss the process called proof search. This is the algorithm that prolog uses to check for unifications and answer queries. Let's start with an example:

```
f(a).
f(b).

g(a).
g(b).

h(b).

k(X) :- f(X), g(X), h(X). 
```

Asking prolog the query,

```prolog
?- k(Y)
```

This gives prolog a **goal**, unifying `k(Y)` with all possible known facts or inferable facts in the knowledge base.

Whenever prolog unifies queries containing variables, it creates a new internal variable to alias it with `Y`. So in the perspective of prolog, the new goal is now:

```prolog
?- k(_G34)
```

> This query has the exact same meaning as `k(Y)`, since the variable names have no inherent meanings anyway. Prolog does this to create a goal containing variables guaranteed to be unused anywhere else. This removes any ambiguity with other variables named `Y` (no other variable is named `Y` but prolog still does this anyway).

Prolog goes through the whole knowledge base from top to bottom and from left to right, attempting to unify the current goal, `k(_G34)` to a fact or a head of a rule. In this case since there are no facts `k(_G34)` can unify with, it unifies with the head (or the conclusion) of a rule, `k(X) :- f(X), g(X), h(X)`.  

> Since there are no other facts or rule heads that can be unified with  the goal `k(_G34)` prolog's proof search doesn't branch out.

Since this is a rule, we can prove that `k(_G34)` is true by proving the premises, ` f(_G34), g(_G34), h(_G34)`. This gives prolog three new goals, proving the conjunction of the predicates,  `f(_G34), g(_G34), h(_G34)`

> By unifying `k(_G34)`  and `k(X)`, the variables `_G34` and `X` are now aliased therefore they now share the same instantiations

Prolog unifies the three goals one by one, left to right. So, starting with the goal `f(_G34)`, prolog searches the whole knowledge base again, attempting to unify `f(_G34)` with facts or rule heads. Since the knowledge base contains both facts `f(a)` and `f(b)`  there will now be two paths in this search, instantiating `_G34` to `a` and instantiating `_G34` to `b`. 

##### Path 1: `_G34 = a`

From this instantiation, prolog is now left with the new goals `g(a), h(a)`. Starting with `g(a)`, prolog searches the knowledge base again and unifies `g(a)` to `g(a)`, reducing  the goal to`h(a)`. Since `h(a)` cannot be unified with any fact or rule head, this path ends up unprovable.

##### Path 2: `_G34 = b`

From this instantiation, prolog no has the new goals `g(b), h(b)`. Starting with `g(b)`, prolog searches the knowledge base from the top again and finds the unification `g(b)` to `g(b)`, reducing the goal to `h(b)`. It then finds the unification, `h(b)` thus completing the goal and proving the query for the instantiation`_G34 = b`. 

By completing all possible paths prolog responds to the query:

```prolog
Y = b
yes
```

Since there are no other instantiations that prove the goals, `Y = b` is the only possible answer.

### Recursive Definitions

Similar to functional programming, logic programming represents repetition using recursion. While functional programming makes heavy use of recursive functions to implement complex behavior, logic programming languages like prolog uses recursive rules to model complex structures. For example: consider the following knowledge base:

```prolog
is_digesting(X,Y)  :-  just_ate(X,Y).
is_digesting(X,Y)  :-
    just_ate(X,Z),
    is_digesting(Z,Y).

just_ate(mosquito,blood(john)).
just_ate(frog,mosquito).
just_ate(stork,frog). 
```

You'll notice that the rule `is_digesting` is special since one of its goals is itself. You can interpret this rule as:

> $X$ is digesting $Y$ if $X$ just ate $Y$ or $X$ ate some $Z$ that is digesting $Y$. 
>
> The `or` part of this implications hypothesis is represented in the knowledge base by giving the conclusion `is_digesting(X,Y)` two separate hypotheses to satisfy.

Posing the query:

```prolog
?- is_digesting(stork,mosquito)
```

Following the process of proof search, the query `is_digesting(stork,mosquito)` is unified with the line 2, giving it a new goal `just_ate(X,Z), is_digesting(Z,Y)`. The goal`just_ate(X,Z)` will then match to `just_ate(stork, frog)` and the 2nd goal, `is_digesting(X,Y)` is then inferred from `just_ate(frog,mosquito)`.

#### Representing numbers using logic

Since logic calculus is a formalism for the foundation of mathematics, how do numbers emerge from predicates and propositions?

This is also another concept shared between, logic calculus and lambda calculus. You can represent numerals (specifically natural numbers) using Peano's axioms:

> 0 is a numeral
>
> the successor of 0, denoted by s(0) is also a numeral

You can represent these axioms as a knowledge base:

```prolog
numeral(0).
numeral(s(X)) :- numeral(X).
```

This knowledge base will then define all of the possible natural numbers out there, demonstrated by the query:

```prolog
numeral(X)
```

```prolog
X = 0
X = s(0)
X = s(s(0))
X = s(s(s(0)))
...
```

Using this representation, you can then define arithmetic operations such as addition and multiplication (also based on Peano's axioms)

```prolog
numeral(0).
numeral(s(X)) :- numeral(X).

add(A,0,A).
add(A,s(B),s(C)) :- add(A,B,C).

mult(_,0,0).
mult(A,s(B),C) :- mult(A,B,D), add(A,D,C).
```

### Advantages and Disadvantages of Logic Programming

Logic programming shares a lot of similarities with functional programming. It also shares its advantages and disadvantages as well. Both paradigms offer a safer and more consistent framework since they are both patterned form mathematical formalisms. Functional programming has lambda calculus and logic programming has predicate calculus. 

Being non-imperative also gives them an edge of automatically being immune to the perils of state and at the same time being prone to the perils of its absence.

Logic programming's way of expressing knowledge gives it a lot of niche uses. Most of the distributions prolog are admittedly meant for a limited use case only. The straight forward way of listing facts and rules makes it suitable for representing complex information that can be usually found in the domains of AI, NLP, and expert systems. The beauty of unification and proof search shines on these domains as they often require, complex representation involving nested rules and recursive structures.

Logic programming's disadvantages are indeed similar to functional programming, but much worse. The obvious inefficiency due to the absence of state is much more evident in logic programming because of the thorough approach of backtracking in proof search. The strangeness of logic programming as compared to the imperative way of thinking is also much worse than functional programming (at least functional and imperative share the concept of functions Prolog only has predicates, Haskell is strange but Prolog is way stranger). Because of these logic programming is relegated to solving niche problems in various domains. Just like functional programming though, the spirit of logic programming can be found in other paradigms through the existence of unification libraries. Although logic paradigm is admittedly less relevant than other paradigms, its strange features are definitely useful and worth studying.

[^1]: Blackburn P., Bos J., Streignitz K., (2012) Learn Prolog Now http://www.learnprolognow.org Accessed August 21, 2020

