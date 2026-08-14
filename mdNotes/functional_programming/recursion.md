# Recursion

Since functional programming does not have the capability to change the state using assignment statement, it does not support your typical for loop.
A for loop like the following example, inherently contains state changes.

```c
for (int i = 0; i < 10; i++) {
    println("hello");
}
```

In the previous example, `i++` is an assignment statement that changes the value of `i`, thus changing the overall state.

In general, loops need to have some state change.
If the state does not change within the loop, the boolean expression will not change as well.
Therefore, the loop will either not start or never stop.

```c
while (boolean_expression) {
    no_state_change
}
```

To model repetition in a functional language like haskell, we use recursion.
Recursion, is repetition through recursive calls.
A recursive call, is a function call that happens inside a function definition.
The function being called is the same function being defined.
A function with a recursive call is called a recursive function.
In the definition of the recursive function `summation`, we call `summation` itself.

```haskell
summation :: Int -> Int
summation n = 
    if n <= 0 then 0
    else n + summation (n - 1)
```

Recursive functions also have a base case.
Depending on the value being passed to the recursive function, it will either evaluate to the base case or the recursive case.
Without a base case, the evaluation will never stop due to the infinite recursion.
In the `summation` example, the base case is evaluated if the value passed is `0`.

## Recurrence Relations

Recursive functions model recurrence relations.
For example, the definition of factorial is a recurrence relation itself, so it is easy to convert it into a recursive function in haskell.

$$
\begin{aligned}
0! &= 1\\
n! &= n(n-1)!
\end{aligned}
$$

```haskell
factorial :: Int -> Int
factorial n = 
    if n == 0 then 1
    else n * factorial (n - 1)
```

When solving problems through recursion, it is best to think of recursive relations that represent the solution of the problem.
For example, if you want to create a string of asterisks with a length of $n$, you can think of a recursive relation that constructs any asterisk of length $n$.
In this case, an asterisk of length $n$ can be constructed by concatenating `*` and an asterisk of length $n - 1$.
From that recurrence relation we can define:

```haskell
n_asterisks :: Int -> String
n_astersisk n = "*" ++ nasterisk (n - 1)
```

To complete the recursive function, we just need a base case that represents the simplest and most trivial case for the problem.
We can choose the base case: an asterisk of length 1 is `*`.
But we can choose an even simpler case: an asterisk of length 0 is an empty string.
By choosing the latter as the base case, we cover both asterisks of length 1 and asterisks of length 0.
The simpler the base case, the more complete the function is.

```haskell
n_asterisks :: Int -> String
n_asterisks n = 
    if n <= 0 then ""
    else "*" ++ n_asterisks (n - 1)
```

Another example, let's create a function that checks if a list of integers is sorted in an increasing manner.
Here's a suitable recurrence relation: a list is sorted if the first element is less than or equal to the rest of the list and if the rest of the list is also sorted.

To make it easier and more readable, let's first create a helper function for one of the tasks: checking if one element is less than or equal a list of elements.
This will also be a recursive function.

```haskell
is_not_greater :: Int -> [Int] -> Bool
is_not_greater n list = 
    if (length list) == 0 then True
    else (n <= (head list)) && is_not_greater n (tail list)
```
[^base_case]

[^base_case]: In the base case for `is_not_greater` we define that an element is not greater than an empty list, since any number is indeed not greater than any element in an empty list. This base case also helps for our `is_sorted` definition.

Here's an explanation of the builtin helper functions used in the definition if `is_not_greater`.

- `length :: [a] -> Int` - returns the length of the list, or the number of elements
- `head :: [a] -> a` - returns the first element in the list
- `tail :: [a] -> [a]` - returns the entire list excluding the first element

With the helper function, `is_not_greater` defined, we can now define `is_sorted`.

```haskell
is_sorted :: [Int] -> Bool
is_sorted list = 
    if (length list) == 0 then True
    else (is_not_greater (head list) (tail list)) && (is_sorted (tail list))
```

One other paradigmatic design choice being used here is how we are breaking down a problem into subproblems.
When there is a complex function you need to define, you can build it up as a composition of smaller functions.
In this case, `is_sorted` is built by composing `is_not_greater` in its definition.

You can also define a more efficient `is_sorted` function by checking the sortedness of all adjacent pairs of elements.
I will leave the definition for such function as an exercise for you.

Next, let's create a function that returns the index of an element in a list.

## Accumulators

```haskell
index_of :: Int -> [Int] -> Int
index_of elem list = 
    if (length list) == 0 then error "element not found"
    else if ((head list) == elem) then <index_of_elem>
        else (index_of elem (tail list))
```

In this example, we encounter a problem.
How do we find the current index of the element being compared to `elem`?
Since we are recursively calling `index_of` to the tail of `list` the current element being compared is always the first element.
If this was imperative programming, we would be able to keep a variable in memory that increments every time we perform a recursive call.
This is something that we cannot do in functional programming.
To simulate a similar mechanism, we instead add an argument called `index` that always starts as zero, and gets "incremented" for every recursive call.

```haskell
index_of :: Int -> [Int] -> Int -> Int
index_of elem list index = 
    if (length list) == 0 then error "element not found"
    else if ((head list) == elem) then index
        else (index_of elem (tail list) (index + 1))
```

For the function to work properly, the `index_of` function must always be called with the argument `index = 0`.
Otherwise, it will return an incorrect value.

To ensure proper usage, we can rename our current `index_of` to a different name (e.g. `index_of_inner`), and create an outer function that calls simply `index_of_inner` while passing 0 to the `index` argument.

```haskell
index_of_inner :: Int -> [Int] -> Int -> Int
index_of_inner elem list index = 
    if (length list) == 0 then error "element not found"
    else if ((head list) == elem) then index
        else (index_of_inner elem (tail list) (index + 1))

index_of :: Int -> [Int] -> Int
index_of elem list = index_of_inner elem list 0
```

If you really want to hide `index_of_inner`, you can place it inside the scope of `index_of` using a `where` clause.

```haskell
index_of :: Int -> [Int] -> Int
index_of elem list = index_of_inner elem list 0
    where
        index_of_inner elem list index = 
            if (length list) == 0 then error "element not found"
            else if ((head list) == elem) then index
                else (index_of_inner elem (tail list) (index + 1))
```

What we ended up with `index_of_inner` is known as **accumulator passing style** [@sturm_accumulator_2011].
This, not only allows us to simulate a mutating variable in functional programming, it also allows us to perform tail call recursion for optimization.

## Tail Call Recursion

When the last evaluation performed in your recursive function is the recursive call, it is known as **tail call recursion**.

Consider the `summation` function previously defined.
This is not tail call recursion, because, after `summation (n - 1)` is called, it will still need to evaluate `n + sumamtion (n - 1)`

```haskell
summation :: Int -> Int
summation n = 
    if n <= 0 then 0
    else n + summation (n - 1)
```

Let's try expanding tracing the evaluation of such recursive function in the call stack, starting with `summation 3`

```haskell
summation 3
(3 + summation 2)
3 + (2 + summation 1)
3 + (2 + (1 + summation 0))
3 + (2 + (1 + 0))
3 + (2 + 1)
3 + 3
6
```

Every time a function is called, we push a function call to the call stack, freezing the current evaluation.
For example, when we evaluate `(3 + summation 2)`, we push the call `summation 2` to the call stack.
The evaluation `(3 + summation 2)` is frozen until `(summation 2)` is evaluated.
To evaluate `(summation 2)`, `(2 + summation 1)` is pushed to the stack.
This freezes `(2 + summation 1)` as well.

This continues until the base case is reached.
The call `summation 0` is pushed to the stack, and pops when it evaluates to `0`.
The completion of each evaluation, pops each call in the stack, unfreezing the pending evaluations until we evaluate to the final answer, `6`.

Let's compare this evaluation, with a tail call recursive version of `summation`. 
To achieve tail call recursion, we will need to apply the accumulator passing style.

```haskell
tail_summation :: Int -> Int
tail_summation n = s n 0
    where
        s n sum =
            if n <= 0 then sum
            else s (n - 1) (n + sum)
```

Let's trace the evaluation of `tail_summation` in the call stack.

```haskell
tail_summation 3
s 3 0
s (3 - 1) (3 + 0)
s 2 3
s (2 - 1) (2 + 3)
s 1 5
s (1 - 0) (1 + 5)
s 0 6
6
```

With tail call recursion you don't really need to keep the previous function calls in memory to evaluate the recursive call.
This is because the evaluation of every recursive call is the solution itself.
For example, the last call `s 0 6` will evaluate to 6, the second to the last call, `s 1 5` will also evaluate to `6`.
Because of the tail call recursion, the recurrence relation we end up with the property: `s n sum = s (n - 1) (n + sum)`.

When you compare this with non-tail call recursion: `summation n = n + summation n - 1)`.
The evaluation `n + ...` has to be kept in memory to evaluate to the final answer.
Because of this the more recursive calls you have, the more memory you need to use.

When programming languages (not just functional languages) detect tail call recursion, instead of keeping the previous calls in memory, they are automatically discarded.
Instead of using $O(n)$ memory for $n$ recursive calls, it only needs to use $O(1)$.
This process is known as **tail call optimization** [@abelson_linear_2002].

One caveat for haskell: since haskell is lazily evaluated, tail call recursion may not lead to memory optimization.
You might need to force some more optimzation to force haskell to eagerly evaluate expressions and avoid memory leaks.

## Extra Reading

[Haskell Introduction](/haskell-introduction) - for some helpful haskell syntax
[Recursion and Fixed Point Combinators](/recursion-and-fixed-point-combinators) - to learn how recursion emerges from the lambda calculus formalism
