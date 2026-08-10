# Advantages and Disadvantages of Logic Programming

## Advantages

Logic programming shares a lot of similarities with functional programming.
It also shares its advantages and disadvantages as well.
Both paradigms offer a safer and more consistent framework since they are both patterned form mathematical formalisms.
Functional programming has lambda calculus and logic programming is based on *first-order predicate calculus*.

Being non-imperative also gives them an edge of automatically being *immune to the perils of state* and at the same time being *prone to the perils of its absence*.

Beyond the general advantages of declarative programming, Logic programming can be a powerful solver in problems like **automated theorem proving**, **constraint related search problems**, **domain specific expert systems** and more.
The straight forward way of listing facts and rules makes it suitable for representing complex information that can be usually found in these use cases.
The beauty of unification and SLD resolution shines on these domains as they often require, complex representation involving multiple interconnected rules, recursive structures, and constraints.

Logic programs also use predicates as *pure relations*.
Because of this, the predicate definitions can support wide uses.

## Disadvantages

Logic programming's way of expressing knowledge gives it a lot of niche uses.
Most of the distributions Prolog are admittedly meant for a *limited* use cases only.
You are not going to use it for web-development, embedded systems, or scripting.

Logic programming's disadvantages are also similar to functional programming, but can be much *worse*.
The obvious *inefficiency* due to the absence of state is much more evident in logic programming because of the thorough approach of backtracking in SLD resolution.
The *strangeness* of the paradigm itself as compared to the imperative way of thinking is also felt more in imperative programming.
At least functional paradigm and imperative paradigm share the concept of functions Prolog only has predicates.
To get used to writing logic programs, you have to get used thinking in terms of *relations* and *declarative definitions*.

Just like functional programming though, the spirit of logic programming can be found in other paradigms through the existence of unification libraries.
Although logic paradigm is admittedly less relevant than other paradigms, its strange features are definitely useful and worth studying.
