Given the following definitions:

```haskell
p :: Int -> Int
p x = x - 1

add :: Int -> Int -> Int
add x y = x + y

applyTwice :: (Int -> Int) -> Int -> Int
applyTwice f x = f (f x)

addMaker :: Int -> (Int -> Int)
addMaker x = (\y -> x + y)
```

What do the following expressions evaluate into? If the expression doesn't evaluate into anything, write "nothing", if it evaluates into an error, write "error"

1. ```haskell
   add (s 3) (p 2)
   ```

2. ```haskell
    p (p (s 10))
   ```

3. ```haskell
   applyTwice p 9
   ```

4. ```haskell
   applyTwice (\x -> x + x) 3
   ```

5. ```haskell
   applyTwice (\x -> x) 19
   ```

6. ```haskell
   (addMaker 6) 7
   ```

7. ```haskell
   applyTwice (addMaker 4) 5
   ```

8. ```haskell
   (\z -> z + z) ((addMaker 5) 3)
   ```

9. ```haskell
   ((\y -> (\x -> x - y)) 3) 2
   ```

   

