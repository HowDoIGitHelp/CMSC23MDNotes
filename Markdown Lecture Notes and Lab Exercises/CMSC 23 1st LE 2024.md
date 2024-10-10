# CMSC 23 1st LE 2024

## I. True or False

1. All declarative paradigms are functional paradigms
2. The functional paradigm allows assignment statements through the '=' operator.
3. An imperative program moves from one state to another through assignment statements.
4. The imperative paradigm is called "imperative" because it is more efficient than declarative languages.
5. All functions in Haskell are pure functions.

## II. Program evaluation

### Program 1 (C)

```c
int i = 1;
printf(i++);
if (i == 1){
	i = i + i;   
}
int j = i + 2;
```

1. What is the state of the program **after** the second line
2. What is the **final** state of the program

### Program II (Haskell)

```haskell
f :: Int -> (Int -> Int)
f x = (y -> x - y)

g :: Int -> Int -> Int
g x y = x - y
```

3. What is the evaluation of `(f 3) 2`?
4. What is the evaluation of `(g 3) (2 + 1)`?

### Program III (Haskell)

```haskell
double :: Int -> Int
double x = x + x

fMultiply :: (Int -> Int) -> Int -> (Int -> Int)
fMultiply f n = if (n == 0) then (\x -> x) else (\x -> (f (fMultiply f (n-1) x)))
```

5. What is the evaluation of `(fMultiply double 0) 2`?
6. What is the evaluation of `(fMultiply double 3) 3`?
7. What is the evaluation of `map double [1,2,3,4,5]`
8. What is the evaluation of `map (map (* 2)) [[],[1],[2,3],[4,5,6]]`

### Program IV (Haskell)

```haskell
isEven :: Int -> Bool
isEven x = (mod x 2) == 0
--(mod a b) returns the remainder of a divided by b

range :: Int -> Int -> [Int]
range s e = if e <= s then [] else [s] ++ (range (s+1) e)
```

9. What is the evaluation of `range 3 8`?
10. What is the evaluation of `filter isEven (range 0 10)`?
11. What is the evaluation of `foldl (\x y -> x ++ y) [] [(range 0 5),(range 5 10),(range 11 12)]`?

