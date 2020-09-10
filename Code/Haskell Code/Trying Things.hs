s :: Int -> Int
s x = x + 1

p :: Int -> Int
p x = x - 1

applyTwice :: (Int -> Int) -> Int -> Int
applyTwice f x = f (f x)

addThree :: Int -> Int
addThree x = x + 3

addFour :: Int -> Int
addFour x = x + 4

addone :: [Int] -> [Int]
addone l =
  if length l == 0 then []
  else [(head l) + 1] ++ addone (tail l)

addTwo :: Int -> Int
addTwo x = x + 2

addMaker :: Int -> (Int -> Int)
addMaker x = (\y -> y + x)

summation :: Int -> Int
summation n = if (n <= 0) then 0 else (n + (summation (n-1)))
