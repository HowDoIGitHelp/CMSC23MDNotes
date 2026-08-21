# Higher-order Functions for Lists

In an imperative programming language, you generally use recursion when working with the elements of lists.
On a functional programming language, you have to rely on recursion.

## Map

Suppose that you have some list of $A$, $l$, and you need to perform some sort of operation on each element of list.
Given the elements of $l = \[l_1, l_2, \cdots l_n\]$, and some function $f:A \to B$, the higher order function `map f l` returns the list $\[f(l_1), f(l_2), \cdots f(l_n)\]$.

For example, you can calculate the square of all the elements in a list with map in haskell this way:

```haskell
> map (\x -> x * x) [1,2,3,4,5]
[1,4,9,16,25]
```

The map function can be used with any function that accepts one parameter of any type and returns one parameter of any type.
For example, given a list of lists, you create a list consisting of their first elements by mapping `head`.

```haskell
> map head [[1,2,3], [4,5,6,7], [8]]
[1,4,8]
```

## Filter

Given, some list of $A$, $l$, and some predicate $p:A \to \{True,False\}$, you filter out the elements of $l$, that do not satisfy the predicate $p$ using the `filter` function.

For example, give a list of lists, you can filter out all the empty lists in using haskell's `filter`.

```haskell
> filter (\l -> length l > 0) [[1,2,3],[],[],[1],[],[4,5,6]]
[[1,2,3],[1],[4,5,6]]
```

This can be rewritten using partial application and compose (`.`).

```haskell
filter ((> 0) . length) [[1,2,3],[],[],[1],[],[4,5,6]]
[[1,2,3],[1],[4,5,6]]
```


