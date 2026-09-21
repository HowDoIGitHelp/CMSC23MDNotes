# Unification and SLD Resolution

## Unification

There are three types of Prolog terms [@blackburn_learn_2006].
By composing these terms you can express rich knowledge bases.

1. Constants. These can either be atoms (known to us as strings such as `squirtle`) or numbers (such as `24`).
2. Variables. Those that start with an underscore or any uppercase letter such as `X`, `Z3`,`_4310`, and `List`.
3. Complex terms. These have the form: `functor(term_1,...,term_n)`. We've seen examples of these in predicates and queries such as `firetype(charmander)` and `isresistantto(X,Y)`

The way Prolog is able to respond to interesting queries involving constants, variables and complex terms is through the **unification**.
Through unification, prolog can identify which pairs of terms can be **resolved** away using resolution.
Unification algorithmically identifies **logically consistent substitutions** involving constants, variables, and complex terms.
If a pair of terms unify, it means that the *negation of one of them produces a contradicting pair*.

Unification in the context of Prolog works along the following rule:

> Two terms unify if they are the same term or if they contain variables that can be uniformly instantiated with terms in such a way that the resulting terms are equal.

This definition gives us the unification of trivial cases such as the unification of constants `squirtle` and `squirtle`.
Prolog also unifies the complex terms `watertype(squirtle)` and `watertype(squirtle)` and the variables `X` and `X`.
On the other hand, the complex terms `watertype(squirtle)` and `watertype(blastoise)` will not unite.

Prolog also unifies the variable `X` with the constant `squirtle`.
Although they are not the same, the variable `X` can be assigned to `squirtle`.
By the same intuition, `watertype(X)` and `watertype(squirtle)` will also unify.
Unification involving variables, create what is known as an instantiation.
Unifying `watertype(X)` and `watertype(squirtle)` will create the instantiation `X=squirtle`.

On the other hand, the complex terms `isresistantto(X,charmander)` and `isresistantto(squirtle,X)` do not unify since you cannot find a consistent instantiation of the variable `X`.
The instantiation `X=squirtle` evaluates to the terms `isresistantto(squirtle,charmander)` and `isresistantto(squirtle,squirtle)`.
The instantiation `X=charmander` evaluates to the terms `isresistantto(charmander,charmander)` and `isresistantto(squirtle,charmander)`.

$$
\begin{aligned}
\text{Let } x = \text{ squirtle}\\
\text{isresistantto}(\text{squirtle}, \text{charmander})\\
\text{isresistantto}(\text{squirtle}, \text{squirtle})
\end{aligned}
$$

$$
\begin{aligned}
\text{Let } x = \text{ charmander}\\
\text{isresistantto}(\text{charmander}, \text{charmander})\\
\text{isresistantto}(\text{squirtle}, \text{charmander})
\end{aligned}
$$

Both scenarios cannot work because the constants `squirtle` and `charmander` *do not unify* with each other.

The process of unification can be summarized as the following algorithm [@blackburn_learn_2006]:

Two terms $a$ and $b$ unify if and only if:

1. $a$ and $b$ are constants, and they are the same number or atom
2. $a$ is a variable and $b$ is any type of term (in this case $a$ is instantiated to $b$) or $b$ is a variable and $a$ is any type of term (in this case $b$ is instantiated to $a$). This rule automatically unifies any pair of variables
3. $a$ and $b$ are complex terms and:
   1. They have the same functors and the same number of arguments
   2. all their corresponding arguments unify
   3. the variable instantiations are uniform or compatible (you cannot instantiate $x$ to some constant $a$ when unifying a pair and instantiate $x$ to another constant $b$ when unifying another pair of arguments)

You can demonstrate unification in the Prolog terminal using the predicate `=/2` (this means the `=` functor with two arguments).

```prolog
?- =(squirtle,squirtle)
true.
```

```prolog
?- =(squirtle,charmander)
no
```

```prolog
?- =(squirtle,X)
X=squirtle
true.
```

```prolog
?- =(X,Y)
X=_5071
Y=_5071
true.
```
[^dummy]

[^dummy]: The instantiations `X=_5071` and `Y=_5071` show that both `X` and `Y` are instantiated to the same dummy variable created by Prolog.
This is also known as `X` and `Y` being aliased to each other, meaning that they share each other's instantiations.

```prolog
?- =(watertype(X),watertype(squirtle))
X=squirtle
true.
```

```prolog
?- =(f(g(X),X),f(Y,a))
X=a
Y=g(X)
true.
```

Unification with variables is an implementation of the inference rule **universal instantiation**.
Remember that a fact `p(X)` in Prolog implicitly means $\forall X p(X)$.
When you assume `p(X)` in the knowledge base, you assume that $\forall X p(X)$ which implies via *universal instantiation* that $p(a)$ is also true (for some constant $a$).
As a unifying pair, if one of them gets negated, you end up with a *contradiction*.
Indeed, if we negate $p(a)$ we end up with a contradiction.

$$
\begin{aligned}
\forall X p(X) \land \neg p(a) &\equiv p(a) \land \neg p(a)\\
\forall X p(X) \land \neg p(a) &\equiv \bot
\end{aligned}
$$

Given `p(A)` in the knowledge base and a query `p(X)`, the query is translated into the existential quantification, $\exists X p(X)$.
Negating said quantification, we also end up with a contradicting pair:

$$
\begin{aligned}
p(a) \land \neg \exists X p(X) &\equiv p(a) \land \forall X \neg p(X) \\
p(a) \land \neg \exists X p(X) &\equiv p(a) \land \neg p(a) \\
&\equiv \bot
\end{aligned}
$$

This is why variables automatically unify with non-variables.

When you compare two variables, they automatically unify because the actual variable name used does not matter at all.
For example, `p(X)` will unify with `p(Y)`, because there is no semantic difference between the universal quantifications $\forall x p(x)$ and $\forall y p(y)$.

Using the same intuition you'll find why the terms `p(X,a)` and `p(b,X)` will not unify.
These terms represent $\forall X p(X,a)$ and $\forall X p(b,X)$, which can only consistently instantiate if $X=a$, $X=b$, and $a=b$.

### Programming with unification

Unification is crucial with how one can write interesting logic programs.
By creating knowledge bases that take advantage of unification, you can generalize structures based on the facts and rules of its characteristics.
For example, the following is a knowledge base describing the characteristics of vertical and horizontal lines[^functor]:

```prolog
vertical(line(point(X,Y),point(X,Z))).
horizontal(line(point(X,Y),point(Z,Y))).
```

[^functor]: Note that the functors `line`, and `point` are not predicates. These are simply used as a way to structure the arguments. Only the outermost functors `vertical/1` and `horizontal/1` are considered predicates.

Therefore, asking the query:

```prolog
?- vertical(line(point(1,2),point(1,3)))
```

Will yield the response:

```prolog
true.
```

It is indeed a vertical line.
And the knowledge base makes sense, since any line that has the same x coordinate is vertical and any line that has the same y coordinate is horizontal.


We can even ask more general queries to Prolog such as:

```prolog
?- horizontal(line(point(2,3),point(U,4)))
```

This query corresponds to asking Prolog for horizontal lines starting at $(2,3)$ and ends at a point with $4$ as the $y$-coordinate.
Prolog attempts the following variable unifications:

```prolog
=(X,2)
=(Y,3)
=(U,Z)
=(Y,4)
```

Individually, these queries unify.
But the unification creates inconsistent instantiations, namely, `Y=3` and `Y=4`.
These instantiations are inconsistent because `3` does not unify with `4`.
Since Prolog can't unify this query with any value for `U` (horizontal lines must have the same $y$-coordinate), Prolog responds:

```prolog
false.
```

## Selective Linear Definite Resolution

Prolog uses the algorithm called **Selective Linear Definite Resolution** (SLD Resolution) to answer queries efficiently.
The process is equivalent to checking if combining the knowledge base clauses and the goal leads to an unsatisfiable formula.
This method can also be written as a step-by-step algorithm that can be easily implemented by computers.

Let's start with a simple example:

```prolog
q(a).
s(b).
p(X).

?- p(a), p(b)
```

Given the query, `p(a), p(b)`, Prolog produces a goal by negating said query.
The goal is now $\neg (p(a) \lor p(b))$.
Since the goal is a disjunction, its negation becomes a conjunction via DeMorgan's Law, $\neg p(a) \land \neg p(b)$.
This creates *two separate sub goals* $\neg p(a)$ and $\neg p(b)$.
One of the aspects of SLD resolution is how it only **selects one subgoal** out of the conjunction of goals.
Prolog selects the **leftmost** subgoal first, in this case, $\neg p(a)$.

Prolog searches the entire knowledge base from **top to bottom**, and tries to unify the current subgoal with any fact or rule head in the knowledge base.
A successful unification, *resolves the subgoal away* (i.e. the subgoal gets cancelled out via resolution).
In this case, $\neg p(a)$, unifies with $p(X)$, creating the instantiation $X = a$.

If the resolution is unclear, you can write $\neg p(a)$ and $p(X)$ as separate clauses[^comp_unif].

$$
\begin{aligned}
p(X) \lor \bot\\
\neg p(a) \lor \bot\\
\hline
\bot \lor \bot
\end{aligned}
$$

[^comp_unif]: $p(X)$ and $\neg p(a)$ are complements of each other through unification. Remember that `p(X)` in Prolog implicitly means $\forall x p(x)$. Through universal instantiation, we know that $\forall x p(x)$ implies that $p(a)$ is true. And we know that $p(a)$ is a complement of $\neg p(a)$.

This resolves $\neg p(a)$, cancelling it out.
Therefore, this leaves $\neg p(b)$ as the only remaining subgoal to be resolved.
Prolog repeats the process for $\neg p(b)$, unifying with $p(X)$ with the instantiation $X=b$.
Resolving $\neg p(b)$ leaves all subgoals resolved away.
With all subgoals resolved and refuted, the query is proven `true` by contradiction.

Let's try an example that involves rules on the knowledge base (example from @blackburn_learn_2006).

```prolog
f(a).
f(b).

g(a).
g(b).

h(b).

k(X) :- f(X), g(X), h(X).
```

With the query:

```prolog
?- k(Y).
```

When you ask prolog the query `k(Y)`, remember that you are actually asking, "which values of `Y` can make the query true"?
For the query to be true, prolog just needs to find one example that makes this query true.
This means that the query is actually an existential quantifier:

$$
\begin{aligned}
\exists Y k(Y)
\end{aligned}
$$

To prove via contradiction, we end up with a goal which is a negation of the query:

| Current Goal           |
|:-----------------------|
| $\forall Y \neg k(Y)$  |

Prolog searches through the entire knowledge base from top to bottom, searching for unifying facts and rule heads.
Prolog ends up finding unification with the head of `k(X) :- f(X), g(X), h(X)`.
The resolution of this rule and the current goal leaves a resolvent which serves as new subgoals [^modus_tollens].

$$
\begin{aligned}
\forall Y \neg Y k(Y) \land \\
\forall X (k(X) \lor \neg f(X) \lor \neg g(X) \lor \neg h(X))\\
\hline
\forall X (\neg f(X) \lor \neg g(X) \lor \neg h(X))
\end{aligned}
$$

[^modus_tollens]: This resolution is just modus tollens.

If you are confused with this resolution, remember that we are unifying the complex term `k(Y)` and `k(X)`.
They do unify since their variable arguments unify automatically.
You can also see that this is true using logical equivalencies:

$$
\begin{aligned}
\forall Y \neg k(Y) \equiv \forall X \neg k(X) \\
\\
\forall X \neg k(X) \land \forall X (k(X) \lor \neg f(X) \lor \neg g(X) \lor \neg h(X))\\
\forall X (\neg k(X) \land (k(X) \lor \neg f(X) \lor \neg g(X) \lor \neg h(X))) \\
\forall X (\neg f(X) \lor \neg g(X) \lor \neg h(X))
\end{aligned}
$$

With $\neg k(Y)$ resolved away, and the resolvent added to the goals, we are left with the following goal.

| Current Goal     |
|:-----------------|
| $\forall X (\neg f(X) \lor \neg g(X) \lor \neg h(X)$      |

With this new goal, Prolog starts resolving the leftmost subgoal, $\neg f(X)$.
This subgoal can be resolved with two distinct complements in the knowledge base, `f(a)` and `f(b)`.
The goal $\neg f(X)$ can resolve with $f(a)$ with the instantiation $X = a$, and resolve with $f(b)$ with the instantiation $X = b$.
Whenever this happens, Prolog creates two branches (one for each distinct instantiation).
Prolog tries to complete the refutation one branch at a time in a depth first manner.
The other branch will be revisited once one branch is completed.

Prolog resolves with the $f(a)$ first since it is found first (when searching top to bottom).
With this, $\neg f(X)$ is resolved away, and the goal narrows down to the following:

| Branch $X = a$ (current)    | Branch $X = b$ |
|:---------|:---------|
| $\neg f(a) \lor \neg g(a) \lor h(a)$ | $\neg f(b) \lor \neg g(b) \lor h(b)$ |
| $\neg g(a) \lor \neg h(a)$ | $\neg g(b) \lor \neg h(b)$ |

Continuing, it tries to complete the branch formed from $X=a$ first.
The subgoal $\neg g(a)$ unifies with `g(a)` in the knowledge base.

| Branch $X = a$ (current)    | Branch $X = b$ |
|:---------|:---------|
| $\neg f(a) \lor \neg g(a) \lor h(a)$ | $\neg f(b) \lor \neg g(b) \lor h(b)$ |
| $\neg g(a) \lor \neg h(a)$ | $\neg g(b) \lor \neg h(b)$ |
| $\neg h(a)$ | ... |

The subgoal $\neg h(a)$ cannot be resolved away with any clause in the knowledge base.
Therefore, it cannot be resolved away.
This means that the branch for $X = a$ completes without refutation/proof by contradiction.

From here, Prolog proceeds to resolve the goal of the $X = b$ branch.
In this branch both subgoals do unify, with $g(b)$ and $h(b)$.
This resolves the goal which completes the branch.

| Branch $X = a$ | Branch $X = b$ (current) |
|:---------|:---------|
| $\neg f(a) \lor \neg g(a) \lor h(a)$ | $\neg f(b) \lor \neg g(b) \lor h(b)$ |
| $\neg g(a) \lor \neg h(a)$ | $\neg g(b) \lor \neg h(b)$ |
| $\neg h(a)$ | $\neg h(b)$ |
| not refuted | $\bot$ (refuted) |

Since one branch is refuted, Prolog responds to the query with `true`.
Prolog also shows which instantiations/branches lead to a refutation.
In this case, the instantiation branch $X=b$ is refuted.
Prolog shows the relevant instantiations that lead to a refuted outcome.
In this case, $X = Y = b$, since $X$ is an internal variable from the knowledge base, it is ommmited.

```prolog
Y = b,
true.
```

The SLD resolution algorithm can be broken down like this:

The initial goal is created by negating the query.

1. The first/leftmost subgoal is selected as the current goal.
2. The knowledge base is searched for complements of the current goal.
3. For every complement in the knowledge perform the following:
    1. Resolve away the complement and the current goal, while keeping the instantiations formed from the resolution.
    2. Perform SLD resolution with the new goal as the resolvent.

Here's another example knowledge base and query:

```prolog
f(a).
g(b).
g(Z) :- f(Z).
h(X) :- g(X).

?- h(Y).
```

As a horn formula:

$$
\begin{aligned}
f(a) \land \\
g(b) \land \\
\forall Z (g(Z) \lor \neg f(Z)) \land \\
\forall X (h(X) \lor \neg g(X)) \land \\
\forall Y \neg h(Y)
\end{aligned}
$$

| Current Goal |
|:-------------|
| $\forall Y \neg h(Y)$ |
| $\forall X \neg g(X)$ (with instantiation ($X = Y$)) |

| Branch ($X=b$)    | Branch ($X=Z$) (Current Goal)  |
|:--------------------------------|:-----------------|
| $\bot$ (refuted)          |  $\forall Z \neg f(Z)$ |
| ...                       | $\bot$ (refuted) (with instantiation ($X = a$))|

Prolog responds with the following.

```prolog
?- h(Y).
Y = b ;
Y = a.
```

