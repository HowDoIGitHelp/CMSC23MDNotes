# Prolog Cheat Sheet

## Setting up prolog

Install the prolog compiler using the installer included in the course pack. To able to use swi-prolog anywhere, add the path to the directory of `swipl` executable in your PATH environment variable. It is generally found in the `bin` folder of the installation.

Once prolog is has been set-up you can run the `swipl` command to start swi-prolog

```
> swipl
```

to exit, use the `halt.` command (swipl will wait for a `.` for every query/command)

```prolog
?- halt.
```

To load prolog knowledge bases (`*.pl`), use the `swipl` command again but this time include the path to the prolog file as an argument.

```
> swipl knowledegebase.pl
```

## Writing Prolog knowledge bases

Knowledge bases are made of facts and rules.

### Prolog facts

Every prolog fact ends with a period. A prolog fact can be a constant, or a complex term.

#### Constant

Constants start with a lowercase letter

```
truth.
```

#### Complex Term

Complex terms follow the pattern `functorName(arg1,arg2,...,arg3)`. Arguments can be a constants, variables, or complex terms. Here are examples

```
isresistantto(squirtle,X).
```

```
vertical(line(point(X,Y),point(X,Z)))
```

> Functors are prologs representations for predicates. Whenever you see `functorName/2` mentioned, it means it is a functor called "functor" that accepts two parameters.

### Rules

Rules follow the following pattern `head :- tail1,tail2,...tail3`. `head` is the conclusion of the implication statement, it can be any fact. The tails represent the hypothesis of the implication. Writing more than one tail means that the hypothesis is a conjunction of the tails. Examples:

```
isresistantto(X,Y) :- watertype(X),watertype(Y).
```

```
is_digesting(X,Y)  :-  just_ate(X,Y).
```

## Writing queries

To ask the knowledge base some questions, you load the knowledge base file using `swipl` and write queries in the `?-` dialog inside `swipl`. Remember to always end the query with a period. Queries are written with the same pattern as prolog facts. For example.

```
?- truth.
```

```
true.
```

If the query has multiple lines of answer, prolog will wait for you to either press semicolon/space to show the next answer or the enter key to stop showing more answers.