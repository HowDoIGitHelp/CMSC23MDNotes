# Logic Programming Formalism

Logic programming is based on the formal system of **predicate calculus**.
It is a formalism based on *logical operations* and *quantification*.

## Satisfiability and Horn Clauses

Logic programming applies the formalism of predicate calculus as means to prove statements and look for logical resolutions.
To introduce these concepts, we will first talk about the satisfiability problem and horn clauses.

### Satisfiability

A boolean expression is said to be **satisfiable** if there exists an assignment of truth values (either TRUE or FALSE) that evaluates the entire formula to TRUE.
A **boolean expression**, is an expression that uses only boolean values, variables or boolean operations.

Here's a trivial example of a satisfiable boolean expression:

$$
p \lor q \lor r
$$

This expression will evaluate to true if $p$, $q$, and $r$ are all set to TRUE.

Here's a trivial example of an unsatisfiable expression:

$$
p \land \neg p \land q
$$

It is not possible for this expression to evaluate to TRUE, since $p \land \neg p$ is a contradiction (it always evaluates to false).

These examples are trivial since, the expressions are short enough, or there is an obvious contradiction that resolves the formula.
But in general, satisfiability is one of the hardest problems in Computer Science.
There are no fast algorithms that can find solutions to a general satisfiability problem.

For example try to check is the following formula is satisfiable:

$$
\begin{aligned}
& (u \lor \neg v \lor w) \land \\
& (\neg u \lor v \lor p) \land \\
& (\neg u \lor p \lor r)
& \end{aligned}
$$

To make it a little easier to solve satisfiability problems, we usually write boolean formulas in **conjunctive normal form** (clausal normal form or CNF).
A boolean formula is in CNF if it is a *conjunction of disjunctions of boolean literals*.
Each disjunction is also known as a clause (e.g. $(u \lor \neg w \lor w)$).
We call unnegated values like $u$ as a positive literal while negated values like $\neg w$ as a negative literal.

Any boolean formula can be expanded into CNF using **logical equivalences**.
The equivalent CNF of a boolean formula is easier to solve since we just need to find satisfiable assignments for each clause consistently (which is still not difficult).

### Horn Clauses

**Horn clauses** are special clauses where there is at most one positive literal in the disjunction.
A boolean formula in CNF where area clauses are Horn clauses is called a **Horn formula**.
The satisfiability of a Horn formula is much easier to solve.

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
& (\neg r \lor \neg s \lor t) \land \\
& (\neg s \lor \neg t)
\end{aligned}
$$

If each Horn clause in the formula has at least one negative literal, then the formula is *always satisfiable* by assigning FALSE to each variable.
This is because the negative literals will all evaluate to TRUE, therefore making each clause TRUE.
This Horn formula is a trivial case for satisfiability.

On the other hand if some of the clauses have no negative literals, then we can simply reduce the clauses to either a trivial satisfiable Horn clause or a contradiction.

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
& (s) \land \\
& (\neg r \lor \neg s \lor t) \land \\
& (\neg s \lor t)
\end{aligned}
$$

In this example, one of the Horn clauses have no negative literal.
Note, that a Horn clause with no literal is a clause with exactly one positive literal.

This can be solved by assigning clause $(s)$ as TRUE, since it can be true for the entire formula to be satisfiable.

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
& (\text{TRUE}) \land \\
& (\neg r \lor \text{FALSE} \lor t) \land \\
& (\text{FALSE} \lor t)
\end{aligned}
$$

We just need to reduce the formula according to logical equivalencies:

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
& (\neg r \lor t) \land \\
& (t)
\end{aligned}
$$

After reducing, one of the Horn clauses, $(t)$, has no negative literal.
Which means it must be assigned TRUE.

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
& (\neg r \lor \text{TRUE}) \land \\
& (\text{TRUE})
\end{aligned}
$$

Reduced into:

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
\end{aligned}
$$

This formula is a trivial Horn formula (all clauses have at least one negative literal).
The remaining clauses will be assigned FALSE, while $s$ and $t$ will be assigned TRUE.
This means that this Horn formula is satisfiable.

In this other example, the Horn formula can be reduced into a contradiction, which means that it is unsatisfiable.

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
& (s) \land \\
& (t) \land \\
& (\neg s \lor \neg t)
\end{aligned}
$$

First, we assign TRUE to $s$.

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
& (\text{TRUE}) \land \\
& (t) \land \\
& (\text{FALSE} \lor \neg t)
\end{aligned}
$$

Apply logical equivalencies to reduce.

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
& (t) \land \\
& (\neg t)
\end{aligned}
$$

Assign TRUE to $t$.

$$
\begin{aligned}
& (\neg p) \land \\
& (\neg q \lor r) \land \\
& (\text{TRUE}) \land \\
& (\text{FALSE})
\end{aligned}
$$

In this case, we end up with a FALSE clause, which means that the conjunction cannot be true.
Therefore, this Horn formula is not satisfiable.

#### Horn Clauses as Implications

Horn clauses also have the advantage of being neatly rewritten as *implication statements*.
If you apply the logical equivalency that converts disjunctions into implications.
You can convert your Horn clauses into implication statements.

$$
\neg p \lor q \equiv p \lor q
$$

A Horn clause that has exactly one positive literal can be converted into an implication with the sole positive literal as the conclusion, and the rest as the hypothesis.

$$
\begin{aligned}
\neg p_1 \lor \neg p_2 \lor \cdots \lor \neg p_n \lor q &\equiv \\
(p_1 \land p_2 \land \cdots \land p_n) \to q
\end{aligned}
$$

A Horn clause with exactly one positive literal and one or more negative literal is known as a **definite Horn clause**.

A Horn clause that is only made up of one positive literal and nothing else is called a **fact**.
To convert it into an implication, we first write it as a disjunction using the identity property of disjunctions.

$$
\begin{aligned}
q & \equiv \\
\text{FALSE} \lor q & \equiv \\
\text{TRUE} \to q
\end{aligned}
$$

A Horn clause that is made up 1 or more negative literals, is called a **goal clause**.

$$
\begin{aligned}
\neg p_1 \lor \neg p_2 \lor \cdots \lor \neg p_n & \equiv \\
\neg p_1 \lor \neg p_2 \lor \cdots \lor \neg p_n \lor \text{FALSE} & \equiv \\
(p_1 \land p_2 \land \cdots \land p_n) \to \text{FALSE}
\end{aligned}
$$

#### Resolution

Resolution is one of the inference rules of logic.
According to resolution, assuming two clauses with complementary literals (a pair of literals that are negations of each other), you can conclude the resolvent.
The resolvent is a disjunction of all the non-complementary literals from both clauses.

$$
\begin{aligned}
p_1 \lor p_2 \lor \cdots \lor r & \\
q_1 \lor q_2 \lor \cdots \lor \neg r & \\
\hline
p_1 \lor p_2 \lor q_1 \lor q_2 \cdots \\
\end{aligned}
$$

In this example, the complements $r$ and $\neg r$ are cancelled out from the resolvent.

Horn clauses are closed over resolution.
This means that the resolution of two Horn clauses is guaranteed to be a horn clause.
This property ensures that conclusions inferred from Horn clauses will always be Horn clauses.
Because of this, it will always be easy to check the satisfiability of Horn formula regardless of any additional conclusions.

As we will discuss later, Horn clauses make up Logic programs.
When you are writing Logic programs, you are simply writing a set of Horn clauses.
