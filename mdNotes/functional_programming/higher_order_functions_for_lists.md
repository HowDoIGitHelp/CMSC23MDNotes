# Higher-order Functions for Lists

In an imperative programming language, you generally use recursion when working with the elements of lists.
On a functional programming language, you have to rely on recursion.

## Map

Suppose that you have some list of $A$, $l$, and you need to perform some sort of operation on each element of list.
Given the elements of $l = [l_1, l_2, \cdots l_n]$, and some function $f:A \to B$, the higher order function `map f l` returns the list $[f(l_1), f(l_2), \cdots f(l_n)]$.

For example, you can calculate the square of all the elements in a list with `map` in haskell this way:

```haskell
> map (\x -> x * x) [1,2,3,4,5]
[1,4,9,16,25]
```

The `map` function can be used with any function that accepts one parameter of any type and returns one parameter of any type.
For example, given a list of lists, you create a list consisting of their first elements by mapping `head`.

```haskell
> map head [[1,2,3], [4,5,6,7], [8]]
[1,4,8]
```

You can use the `map` function to generate a string of asterisks of any length.

```haskell
> map (\x -> '*') [1..4]
"****"
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
> filter ((> 0) . length) [[1,2,3],[],[],[1],[],[4,5,6]]
[[1,2,3],[1],[4,5,6]]
```

## Fold

The fold function converts a list of `A` into one `A`.
There are two types of fold, fold right called `foldr` and fold left called `foldl`.
Given a list of $A$, $l = [l_1, l_2, \cdots l_n]$, seed value $u \in A$, and a function $f: A,A \to A$, `foldr u f l` returns $f(l_1,f(l_2,f(l_3,\cdots f(l_n,u) \cdots )))$.
On the other hand, `foldl u f l` returns $f(\cdots f(f(f(u,l_1), l_2),l_3) \cdots, l_n)$.
When `l` is an empty list, both functions return `u`.

You can see the difference between `foldl` and `foldr` when both are used with subtraction

```haskel
> foldl (-) 0 [1,2,3,4]
-10
```

```haskell
> foldr (-) 0 [1,2,3,4]
-2
```

You can check how these elements are folded using the fold property:

$$
\begin{aligned}
f(x,y) &= x - y\\
\\
f(f(f(f(u,l_1), l_2),l_3),l_4) &= ((((0 - 1) - 2) - 3) -4)\\
&= -10
\end{aligned}
$$

$$
\begin{aligned}
f(x,y) &= x - y\\
\\
f(l_1,f(l_2,f(l_3,f(l_n,u)))) &= (1 - (2 - (3 - (4 - 0))))\\
&= -2
\end{aligned}
$$

The functions `foldl` and `foldr` will have the same result when used on a commutative function.

```haskell
> foldl (+) 0 [1,2,3,4]
15
```

```haskell
> foldr (+) 0 [1,2,3,4]
15
```

You can use fold to concatenate lists:

```haskell
> foldl (++) [] [[1,2,3],[4],[],[5,6],[7]]
[1,2,3,4,5,6,7]
```

You can use fold to find the smallest value in a list:

```haskell
> foldl min 1000000  [-1,2,4,-2,3,1,-5,0,2]
-5
```

## Some more useful higher order list functions

Here are some higher order functions available in haskells standard library.

- `any :: (a -> Bool) -> [a] -> Bool`, returns true if at least one element satisfies the predicate otherwise returns false
- `all :: (a -> Bool) -> [a] -> Bool`, returns true if all elements satisfy the predicate, otherwise returns false
- `takeWhile :: (a -> Bool) -> [a] -> [a]`, takes elements from the given list until predicate is not satisfied
- `dropWhile :: (a -> Bool) -> [a] -> [a]`, drops elements from the given list until predicate is not satisfied
- `zipWith :: (a -> b -> c) -> [a] -> [b] -> [c]`, given `zipWith f l m`, it returns `[(f l_1 m_1) (f l_2 m_2) ... (f l_n m_n)]`

## Using higher order list functions

These functions are very useful in functional programming, that most modern programming languages adopt these higher order functions into their own libraries.
These higher order list functions can quickly replace most recursive functions that involve lists.
You can even define a complex function involving list as a combination of these higher order functions.

You can define the factorial function from `foldl` or `foldr`

```haskell
factorial :: Int -> Int
factorial 0 = 1
factorial n = foldl (*) 1 [1..n]
```

You can define a 2-dimensional map, i.e. a map function that maps a function to a list of lists.

```haskell
map2d :: (a -> b) -> [[a]] -> [[b]]
map2d f ll = map (map f) ll
```

Fun fact, this definition is actually equivalent to the following:

```haskell
map2d = (map . map)
```

You can see this equivalency in the following examples.
Both definitions evaluate into the same expression.

```haskell
map2d (+ 1) [[1],[2,3]]
map (map (+ 1)) [[1],[2,3]]
map (\m -> map (+ 1) m) [[1],[2,3]] -- apply currying to map (+ 1)
```

```haskell
map2d (+ 1) [[1],[2,3]]
(map . map) (+ 1) [[1],[2,3]]
((\f -> (\l -> map f l)) . (\g -> (\m -> map g m))) (+ 1) [[1],[2,3]] -- apply currying to both maps
(\f -> (\l -> map ((\g -> (\m -> map g m)) f) l)) (+ 1) [[1],[2,3]] -- compose lambdas
(\l -> map ((\g -> (\m -> map g m)) (+ 1)) l) [[1],[2,3]] -- beta reduce (+ 1) to \f
(\l -> map (\m -> map (+ 1) m) l) [[1],[2,3]] -- beta reduce (+ 1) to \g
map (\m -> map (+ 1) m) [[1],[2,3]] -- beta reduce [[1],[2,3]] to \l
```

You can define a prime checker using `any`

```haskell
isPrime :: Int -> Bool
isPrime 2 = True
isPrime n = not (any (\x -> (mod n x) == 0) [2..(n-1)])
```
