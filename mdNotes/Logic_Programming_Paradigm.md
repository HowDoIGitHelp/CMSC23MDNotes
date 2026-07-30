# Logic Programming Paradigm

## Introduction

Just as functional programming paradigm is patterned from the formalisms of lambda calculus, logic programming is patterned from predicate calculus.
Computer Scientists usually describe families of programming languages under the logic paradigm as a sub-paradigm of declarative programming (*declarative programming being any paradigm that is not imperative*).
 In terms of application this paradigm is more closely related to knowledge base programming languages like SQL.
While SQL uses relations (not tables) to represent knowledge, logic programming uses rules of logic and predicate calculus to represent knowledge.


## Learning Outcomes

1. Create Prolog facts, rules, and queries
2. Explain the process of unification
3. Explain how proof search is used to respond to queries
4. Create recursive Prolog rules

---

For this secton we will use the programming language Prolog as the representative of logic paradigm.
Other logic programming families are answer set programming, ABYSS and Datalog.

### Facts Rules and Queries

#### Facts

There are three basic constructs in Prolog, **facts**, **rules** and **queries**.
A **knowledge base** is a collection of facts and rules in the same way a c library or a python package is a collection of function definitions.
Prolog programs are basically knowledge bases.
Here's an example of a knowledge base:

```prolog
firetype(charmander).
firetype(charizard).
watertype(squirtle).
flyingtype(charizard).
```

Each line of code you can in this particular knowledge base is a fact.
Just like facts, in logic, facts in Prolog are propositions that are known to be true.
This means that your program knows four things.
One of those are:

```prolog
firetype(charmander).
```

In Prolog `firetype(X)` represents a predicate you've learned in discrete math, e.g. $\text{firetype}(x)$.
And just like predicates, this means `X` is firetype.
Therefore, the fact `firetype(charmander)` represents the proposition, "*charmander is firetype*"

So, to summarize, the fact `firetype(charmander)` is basically a representation of the proposition, $firetype("charmander")$ where $firetype$ is a predicate and $charmander$ is a value assigned to the predicate.
Every fact in your knowledge base represents propositions that can be assumed to be true.
Facts that are not in your knowledge base represents propositions that you cannot assume to be true.

You can also write non-predicate based propositions in your knowledge base.
For example:

```prolog
this_is_a_fact.
axiom_1.
```

Facts, just like other basic Prolog constructs, are implementations of Horn clauses.
Specifically, facts represent Horn clauses with one positive literal and nothing else.
A fact like, `firetype(charmander)` can be written in the form of an implication as such:

$$
\begin{aligned}
firetype(charmander) & \equiv firetype(charmander) \lor \bot\\
 & \equiv \top \to firetype(charmander) \\
\end{aligned}
$$

Horn clauses like these are called facts because, these clauses are always assumed to be true via modus ponens.

#### Rules

Aside from facts, you can also define rules in your knowledge base.
To illustrate this, let's add one rule to our knowledge base.

```prolog
firetype(charmander).
firetype(charizard).
watertype(squirtle).
flyingtype(charizard).

resistanttofire(squirtle) :- watertype(squirtle).
```

Rules are written with the syntax: **`u :- v`**.
This is equivalent to the implication statement $v \to u$.
Prolog rules are written using the "u if v", the reverse of a conventional "if v then u" implication "statement.
A lot of people get confused here so just remember, `:-` is read as if.
In prolog, we call the conclusion `u` as the rule **head** and the hypothesis `v` as the rule **body**.

A **rule** is an implementation of a definite Horn clause.
It is a Horn clause with exactly one positive literal and one or more negative literal.

This will be more obvious if we convert the rule into its disjunction form.

$$
\begin{aligned}
watertype(squirtle) \to resistanttofire(squirtle) & \equiv \\
\neg watertype(squirtle) \lor resistanttofire(squirtle)
\end{aligned}
$$

#### Queries

We interact with a knowledge base by writing **queries** to Prolog.
You can probably guess what this Prolog construct means just by looking at its name.
Queries represent *questions* you ask Prolog.
The answer to a question depends on what Prolog knows.
And what Prolog knows is represented by the knowledge base.
For example if you load the knowledge base we created earlier.
We can ask Prolog the following question below.
Writing the following will basically ask Prolog, "hey, is it true that charmander is firetype?"

```prolog
?- firetype(charmander).
```

Based on the knowledge base loaded earlier Prolog knows that this proposition is indeed true.
Therefore, it responds with:

```prolog
true.
```

If Prolog is asked with the query

```prolog
?- firetype(squirtle).
```

Prolog checks its knowledge base again.
Realizing that none of the facts match this proposition, Prolog responds with:

```prolog
false.
```

You can also ask Prolog a conjunction as a query.
This is written multiple queries, separated by a comma.

```prolog
?- firetype(charizard), watertype(squirtle).
```

A conjunction of queries can only be true if all queries in said conjunction are true.

```prolog
true.
```

Prolog queries are also implementations of Horn clauses.
When you provide a query to prolog, prolog tries to prove that the query is true using **proof by contradiction**.
To do this, the query is negated, converting them to a goal.
For example, the query `firetype(charizard), watertype(squirtle)`, is negated into the disjunction:

$$
\begin{aligned}
\neg firetype(charizard) \lor \neg watertype(squirtle)
\end{aligned}
$$

This negated query or goal is then combined into the knowledge base.
If the knowledge base is unsatisfiable with the introduction of the goal, then that means that the goal introduced a contradiction.
Therefore, the goal's negation (the original query), must be **consistent** with the knowledge base's assumptions.
This ultimately means that it is **true** with respect to the knowledge base.

Here's an example, given the knowledge base:

```prolog
p
q
r :- q
```

And the query:

```prolog
?- r
```

To demonstrate the answer to the query easily, let's convert the clauses in the knowledge base into disjunctions and add the query as a goal[^rule_convert].

$$
\begin{aligned}
(p) \land \\
(q) \land \\
(\neg q \lor r) \land \\
(\neg r)
\end{aligned}
$$

We then check if resulting Horn formula is satisfiable:

[^rule_convert]: When converting prolog rules into definite Horn clauses, the rule body is negated. This is because a rule body can be a conjunction, while a rule head can't. To ensure that the resulting clause is a valid Horn clause, we negate the body.

$$
\begin{aligned}
(\top) \land \\
(\top) \land \\
(\bot \lor r) \land \\
(\neg r)
\end{aligned}
$$

$$
\begin{aligned}
(r) \land \\
(\neg r)
\end{aligned}
$$

$$
\begin{aligned}
(\top) \land \\
(\bot)
\end{aligned}
$$

This shows that combining our goal with the Horn formula leads to an unsatisfiable formula.
Thus proving via contradiction that $r$ is indeed true.
With this Prolog appropriately responds `true`.

```prolog
true.
```

#### Variables

Another important thing about Prolog constructs is that you can write them with **variables**[^variables].
When you write with Prolog facts or rules, you are implicitly creating a *universally instantiated predicate*.
For example, the fact `pokemon(X)`[^variable_syntax], corresponds to the proposition, $\forall x (\text{Pokemon}(x))$.
By adding this to the knowledge base, you are assuming that for any value `x`, `pokemon(X)` is true.
Therefore, asking the query, `pokemon(charizard)` will yield a `true` response.
Of course, any value supplied to the predicate `pokemon` will yield true because of the universal quantification.

```prolog
pokemon(X).
```

```prolog
?- pokemon(charizard).
```

```prolog
true.
```

[^variables]: Variables, in the context of logic programming, do not refer to the same variables in imperative programming. These variables represent, mathematical variables that can be free or bound.

[^variable_syntax]: Prolog variables must start with either a capital letter or an underscore.

You can also use variables on queries.
For example, if we use one of the previous knowledge bases and ask the query: `firetype(X)`.

```prolog
firetype(charmander).
firetype(charizard).
watertype(squirtle).
flyingtype(charizard).

resistanttofire(squirtle) :- watertype(squirtle).
```

```prolog
?- firetype(X)
```

This query basically asks, which values when substituted to `X` in the predicate `firetype(X)` will yield true statements? This can be interpreted in natural language as "which Pokémon are fire type?" Therefore, this query will yield the response:

```prolog
X = charmander
X = charizard
```

Variables used in rules allows the creation of richer knowledge bases.
Instead of the rule `resistanttofire(squirtle) :- watertype(squirtle).` we can write a more general rule using variables:

```prolog
firetype(charmander).
firetype(charizard).
watertype(squirtle).
flyingtype(charizard).

isresistantto(X,Y) :- watertype(X),firetype(Y).
isresistantto(X,Y) :- watertype(X),watertype(Y).
```

This introduces a more complicated rule `isresistanto(X,Y) :- watertype(X),firetype(Y)`.
This rule's premise is a conjunction of predicates `watertype(X)` and `firetype(Y)`.
 

If we imagine that the predicate, $isresistantto(x,y)$ means "x is resistant to y", the whole rule can be interpreted as 

> for all pairs of X and Y, X is resistant to Y, if X is water type and Y is fire type,

This statement, can be written as the following quantification statement:

$$
\forall x \forall y ((watertype(x) \land firetype(y)) \to isresistantto(x,y))
$$
By writing this rule, Prolog can infer the following facts:

```prolog
?- isresistantto(squirtle,charmander).
```

```prolog
true.
```

```prolog
?- isresistantto(squirtle,charizard).
```

```prolog
true.
```

```prolog
?- isresistantto(squirtle,squirtle).
```

```prolog
true.
```

If you ask Prolog a harder question like the following:

```prolog
?- isresistantto(squirtle,X).
```

Prolog interprets this as "which values of `X` make the proposition: squirtle is resistant to X, true? Therefore, Prolog will look for the pokémon, squirtle is resistant to, therefore you with the output:

```prolog
X = charmander
X = charizard
X = squirtle
```

You can even ask Prolog for all possible pairs of resistance relationships in the knowledge base.
You can do this by using two different variables for each argument in the predicate.

```prolog
?- isresistantto(X,Y).
```

### Unification

There are three types of Prolog terms [^1].
By composing these terms you can express rich knowledge bases.

1. Constants. These can either be atoms (known to us as strings such as `squirtle`) or numbers (such as `24`).
2. Variables. (Those that start with an underscore or any uppercase letter such as `X`, `Z3`,`_4310`, and `List`.)  
3. Complex terms. These have the form: `functor(term_1,...,term_n)`. We've seen examples of these in predicates and queries such as `firetype(charmander)` and `isresistantto(X,Y)`

The way Prolog is able to respond to interesting queries involving constants, variables and complex terms is through the **unification**.
Unification algorithmically identifies **logically consistent substitutions** involving constants, variables, and complex terms.
Unification in the context of Prolog works along the following rule:

>  Two terms unify if they are the same term or if they contain variables that can be uniformly instantiated with terms in such a way that the resulting terms are equal.

This definition gives us the unification of trivial cases such as the unification of constants `squirtle` and `squirtle`.
Prolog also unifies the complex terms `watertype(squirtle)` and `watertype(squirtle)` and the variables `X` and `X`.
On the other hand, the complex terms `watertype(squirtle)` and `watertype(blastoise)` will not unite.

Prolog also unifies the variable `X` with the constant `squirtle`.
Although they are not the same, the variable `X` can be assigned to `squirtle`.
By the same intuition, `watertype(X)` and `watertype(squirtle)` will also unify.
Unification involving variables, create what is known as an instantiation.
Unifying `watertype(X)` and `watertype(squirtle)` will create the instantiation `X=squirtle`.

On the other hand the complex terms `isresistantto(X,charmander)` and `isresistantto(squirtle,X)` do not unify since you cannot find a consistent instantiation of `X`.
The instantiation `X=squirtle` evaluates to the terms `isresistantto(squirtle,charmander)` and `isresistantto(squirtle,squirtle)`.
The instantiation `X=charmander` makes the terms `isresistantto(charmander,charmander)` and `isresistantto(squirtle,charmander)`.

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

Both scenarios cannot work because the constants `squirtle` and `charmander` do not unify with each other.

The process of unification can be summarized by the following[^1]: 

Two terms $a$ and $b$ unify if and only if

1. $a$ and $b$ are constants, and they are the same number or atom
2. $a$ is a variable and $b$ is any type of term (in this case $a$ is instantiated to $b$) or $b$ is a variable and $a$ is any type of term (in this case $b$ is instantiated to $a$). This rule automatically unifies any pair of variables
3. $a$ and $b$ are complex terms and:
   1. They have the same functors and the same number of arguments
   2. all their corresponding arguments unify
   3. the variable instatiations are uniform or compatible (you cannot instantiate $x$ to some constant $a$ when unifying a pair and instantiate $x$ to another constant $b$ when unifying another pair of arguments)

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

Unification with variables is an implementation of **universal instantiation**.
Remember that a `p(X)` in Prolog implicitly means $\forall x p(x)$.
When you assume `p(X)` in the knowledge base, you assume that $\forall x p(x)$ which implies via *universal instantiation* that $p(a)$ is also true (for some constant $a$).

When you have two variables unified to each other, they automatically unify because the actual variable name used does not matter at all.
For example, `p(X)` will unify with `p(Y)`, because there is no semantic difference between that universal quantifications $\forall x p(x)$ and $\forall y p(y)$.

Using the same intuition you'll find why the terms `p(X,a)` and `p(b,X)` will not unify.
These terms represent $\forall X p(X,a)$ and $\forall X p(b,X)$, which can only consistently instantiate if $X=a$, $X=b$, and $a=b$.


#### Programming with unification

Unification is crucial with how one can write interesting logic programs.
By creating knowledge bases that take advantage of unification, you can generalize structures based on the facts and rules of its characteristics.
For example, the following is a knowledge base describing the characteristics of vertical and horizontal lines[^functor]:

```prolog
vertical(line(point(X,Y),point(X,Z))).
horizontal(line(point(X,Y),point(Z,Y))).
```

[^functor]: Note that the functors `line`, and `point` are not predicates. These are simply used as a way to structure the arguments. Only the outermost functors `vertical` and `horizontal` are considered predicates.

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

### Selective Linear Definite Resolution

Prolog uses the algorithm called **Selective Linear Definite Resolution** (SLD Resolution) to answer queries efficiently.
The process is equivalent to checking if the combination of the knowledge base clauses and the goal leads to an unsatisfiable formula.
But this method provides a step-by-step process that can be easily implemented by computers.

Let's start with a simple example:

```prolog
q(a).
s(b).
p(X).
```

```
?- p(a), p(b)
```

Given the query, `p(a), p(b)`, Prolog produces a goal by negating the query.
The goal is now $\neg p(a) \lor \neg p(b)$.
Since the goal is a disjunction, it must prove that a contradiction arises from both $\neg p(a)$ and $\neg p(b)$.
This creates two separate sub goals $\neg p(a)$ and $\neg p(b)$.
One of the aspects of SLD resolution is how it only *selects* one **subgoal** out of the conjunction of goals.
Prolog selects the leftmost subgoal first, in this case, $\neg p(a)$.

Prolog searches the entire knowledge base from top to bottom, and tries to unify the current subgoal with any fact or rule head in the knowledge base.
A successful unification, resolves the subgoal away.
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

This resolves $\neg p(a)$, cancelling it out. Thus, leaving $\neg p(b)$ as the only remaining subgoal to be resolved.
Prolog repeats the process for $\neg p(b)$, unifying with $p(X)$ with the instantiation $X=b$.
Resolving $\neg p(b)$ leaves all subgoals resolved.
With all subgoals resolved and refuted, the query is proven `true` by contradiction.

Let's try an example that involves rules on the knowledge base.

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

The goal produced from the query is $\neg k(Y)$.

| Current Goal     |
|:-----------------|
| $\neg k(Y)$      |

Prolog goes through the entire knowledge base from top to bottom finding unification with the head of `k(X) :- f(X), g(X), h(X)`.
The resolution of a rule and a goal leaves a resolvent which serves as new subgoals[^modus_tollens].

$$
\begin{aligned}
\neg k(Y) \lor \bot\\
k(X) \lor \neg f(X) \lor \neg g(X) \lor \neg h(X)\\
\hline
\neg f(X) \lor \neg g(X) \lor \neg h(X)\\
\end{aligned}
$$

[^modus_tollens]: This resolution is just modus tollens.

With $\neg k(Y)$ resolved away, and the resolvent added to the goals, we are left with the following goal.

| Current Goal     |
|:-----------------|
| $\neg f(X) \lor \neg g(X) \lor \neg h(X)$      |

With this new goal, Prolog starts resolving the leftmost subgoal, $\neg f(X)$.
This subgoal unifies with two facts, `f(a)` and `f(b)`.
Whenever this happens, Prolog creates two branches (one for each possible unification).
Prolog tries to complete the refutation one branch at a time in a depth first manner.
The other branch will be revisited once one branch is completed.

For the unification to be consistent, the other subgoals must also unify with respect to the instantiation $X = a$.
With this, $f(X)$ is resolved away, and the goal narrows down to the following:

| Branch $X = a$ goal (current)    | Branch $X = b$ goal |
|:---------|:---------|
| $\neg g(a) \lor \neg h(a)$ | $\neg g(b) \lor \neg h(b)$ |

In this case, it tries to complete the branch formed from $X=a$ first.
The subgoal $\neg g(a)$ unifies with `g(a)` in the knowledge base.

| Branch $X = a$ goal (current)    | Branch $X = b$ goal |
|:---------|:---------|
| $\neg h(a)$ | $\neg g(b) \lor \neg h(b)$ |

The subgoal $\neg h(a)$ does not unify with any clause in the knowledge base.
Therefore, it cannot be resolved away.
This means that the branch for $X = a$ completes without refutation.

| Branch $X = a$ goal (not refuted)    | Branch $X = b$ goal |
|:---------|:---------|
| $\neg h(a)$ | $\neg g(b) \lor \neg h(b)$ |

From here, Prolog proceeds to resolve the goal of the $X = b$ branch.
In this branch both subgoals do unify, with $g(b)$ and $h(b)$.
This resolves the goal which completes the branch.

| Branch $X = a$ goal (not refuted)    | Branch $X = b$ goal (refuted)|
|:---------|:---------|
| $\neg h(a)$ | $\bot$ |

Since one branch is refuted, Prolog responds to the query with `true`.
Prolog also shows which instantiations/branches lead to a refutation.
In this case, $X=b$ is refuted and since $X = Y$:

```prolog
Y = b,
true.
```

### Recursive Definitions

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

#### Representing numbers using logic

Since logic calculus is a formalism for the foundation of mathematics, how do numbers emerge from predicates and propositions?

This is also another concept shared between, logic calculus and lambda calculus.
You can represent numerals (specifically natural numbers) using Peano's axioms:

> 0 is a numeral
>
> the successor of 0, denoted by s(0) is also a numeral

You can represent these axioms as a knowledge base:

```prolog
numeral(0).
numeral(s(X)) :- numeral(X).
```

This knowledge base will then define all the possible natural numbers out there, demonstrated by the query:

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

Logic programming shares a lot of similarities with functional programming.
It also shares its advantages and disadvantages as well.
Both paradigms offer a safer and more consistent framework since they are both patterned form mathematical formalisms.
Functional programming has lambda calculus and logic programming is based on First Order Predicate Calculus.

Being non-imperative also gives them an edge of automatically being immune to the perils of state and at the same time being prone to the perils of its absence.

Logic programming's way of expressing knowledge gives it a lot of niche uses.
Most of the distributions Prolog are admittedly meant for a limited use case only.
The straight forward way of listing facts and rules makes it suitable for representing complex information that can be usually found in the domains of AI, NLP, and expert systems.
The beauty of unification and proof search shines on these domains as they often require, complex representation involving nested rules and recursive structures.

Logic programming's disadvantages are indeed similar to functional programming, but much worse.
The obvious inefficiency due to the absence of state is much more evident in logic programming because of the thorough approach of backtracking in proof search.
The strangeness of logic programming as compared to the imperative way of thinking is also much worse than functional programming (at least functional and imperative share the concept of functions Prolog only has predicates, Haskell is strange, but Prolog is way stranger).
Because of these, logic programming is relegated to solving niche problems in various domains.
Just like functional programming though, the spirit of logic programming can be found in other paradigms through the existence of unification libraries.
Although logic paradigm is admittedly less relevant than other paradigms, its strange features are definitely useful and worth studying.

[^1]: Blackburn P., Bos J., Streignitz K., (2012) Learn Prolog Now http://www.learnProlognow.org Accessed August 21, 2020

