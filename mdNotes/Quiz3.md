Given the following knowledge base:

```commonlisp
g(a,b).
g(b,a).
g(b,c).

h(X,Z) :- g(X,Y),g(Y,Z).
```

1. List every fact and rule as predicates in predicate calculus form.
2. Show the resolution of the following query: `h(X,Y)?`.
3. Show the proof search tree of the query in item 2.