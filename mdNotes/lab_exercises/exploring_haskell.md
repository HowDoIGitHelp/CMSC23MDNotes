# Lab Exercise 2 (Exploring Haskell)

## Task

We've discussed functional programming paradigms using the language haskell as a representative. For this exercise, you'll familiarize yourselves on how to write pure functions in haskell. **Create a haskell file (".hs") containing the following functions below.**

### Easy functions

- `cube :: Int -> Int` - Takes an integer and returns the cube of that integer
- `double :: Int -> Int` - Takes an integer and returns the 2 times that integer

### Recursive Functions

- `modulus :: Int -> Int -> Int` - Takes two integers $x$ and $m$ and returns $x \mod m$. Do not use the built-in `mod` function in haskell
- `largestPowerOf2 :: Int -> Int` - Takes a positive integer and returns the largest power of 2 that is less than or equal to said integer.
- `factorial :: Int -> Int` - Takes an integer and returns the factorial of the integer
- `summation :: Int -> Int` - Takes a natural number and returns the summation of numbers from 1 to n. $\sum_{i=1}^{n}{i}$. 

  ```haskell
  summation :: Int -> Int
  summation n = if (n <= 1) then n else (n + (summation (n-1)))
  ```

### Higher order function

- `compose :: (Int -> Int) -> (Int -> Int) -> (Int -> Int)` - Takes two functions $f : \mathbb{Z} \to \mathbb{Z}$, and $g:  \mathbb{Z} \to \mathbb{Z}$ and returns the function $f \circ g$.
- `subtractMaker :: Int -> (Int -> Int)` - Takes an integer $x$ and returns a function that returns an integer $y$ and returns $x-y$
- `applyNTimes :: (Int -> Int) -> Int -> Int -> Int` - Takes a function $f: \mathbb{Z} \to \mathbb{Z}$ and two integers $n$ and $x$. `applyNTimes` returns an integer which is the result of the function applied to $x$, $n$-times. If $n$ is less than or equal to 0 it must produce zero applications of $f$ therefore it returns $x$.

## Assessment Criteria

- Completeness of haskell functions - 40
