# Lab Exercise 2

## Task

We've discussed functional programming paradigms using the language haskell as a representative. For this exercise, you'll familiarize yourselves on how to write pure functions in haskell. **Create a haskell file (".hs") containing the following functions below.**

For those of you using REPL's haskell compiler add the following snippet of code the the bottom of your function definitions. For those of you using ghc in your computers ignore this.

```haskell
main :: IO ()
main = return ()
```

#### Easy functions

- `cube :: Int -> Int` - Consumes an integer and produces the cube of that integer
- `double :: Int -> Int` - Consumes an integer and produces the 2 times that integer

#### **Recursive Functions**

- `modulus :: Int -> Int -> Int` - Consumes two integers $x$ and $m$ and produces $x \mod m$ 

- `factorial :: Int -> Int` - Consumes an integer and produces the factorial of the integer

- `summation :: Int -> Int` - Consumes a natural number and produces the summation of numbers from 1 to n. $\sum_{i=1}^{n}{i}$. 

  ```haskell
  summation :: Int -> Int
  summation n = if (n <= 1) then n else (n + (summation (n-1)))
  ```

#### Higher order function

- `compose :: (Int -> Int) -> (Int -> Int) -> (Int -> Int)` - Consumes two functions $f : \mathbb{Z} \to \mathbb{Z}$, and $g:  \mathbb{Z} \to \mathbb{Z}$ and produces the function $f \circ g$.
- `subtractMaker :: Int -> (Int -> Int)` - Consumes an integer $x$ and produces a function that consumes an integer $y$ and produces $x-y$
- `applyNTimes :: (Int -> Int) -> Int -> Int -> Int` - Consumes a function $f: \mathbb{Z} \to \mathbb{Z}$ and and two integers $n$ and $x$. `applyNTimes` produces an integer which is the result of the function applied to $x$, $n$-times. If $n$ is less than or equal to 0 it must produce zero applications of $f$ therefore it produces $x$.

## Assessment Criteria

- Completeness of haskell functions - 40