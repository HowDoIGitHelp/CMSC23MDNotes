# Logic Programming Paradigm

## Introduction

Just as functional programming paradigm is patterned from the formalisms of lambda calculus, logic programming is patterned from predicate calculus.
Computer Scientists usually describe families of programming languages under the logic paradigm as a sub-paradigm of declarative programming (*declarative programming being any paradigm that is not imperative*).

## Learning Outcomes

1. Create Prolog facts, rules, and queries
2. Explain the process of unification
3. Explain how proof search is used to respond to queries
4. Create recursive Prolog rules

---

For this section we will use the programming language Prolog as the representative of logic paradigm.
Other logic programming families are answer set programming, ABYSS and Datalog.

## Facts

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

So, to summarize, the fact `firetype(charmander)` is basically a representation of the proposition, $firetype("charmander")$ where `firetype/1`[^predicate] is a predicate and `charmander` is a value assigned to the predicate.
Every fact in your knowledge base represents propositions that can be assumed to be true.
Facts that are not in your knowledge base represents propositions that you cannot assume to be true.

[^predicate]: `firetype/1` indicates a predicate named `firetype` with `1` argument.

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

## Rules

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

## Queries

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

## Variables

Another important thing about Prolog constructs is that you can write them with **variables**[^variables].
When you write with Prolog facts or rules, you are implicitly creating a *universally instantiated predicate*.
For example, the fact `pokemon(X)`[^variable_syntax], corresponds to the proposition, $\forall x (\text{Pokemon}(x))$.
By adding this to the knowledge base, you are assuming that for any value `x`, `pokemon(X)` is true.
Therefore, asking the query, `pokemon(charizard)` will yield a `true` response.
Of course, any value supplied to the predicate `pokemon/1` will yield true because of the universal quantification.

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
