smallest :: Ord a => [a] -> a
smallest l = foldl (\x y -> if (x < y) then x else y) (head l) l

range :: Int -> Int -> [Int]
range u v = 
  if (u >= (v-1)) 
    then [v-1] 
  else [u] ++ range (u+1) v

isPrime :: Int -> Bool
isPrime x = length (filter (\y -> (mod x y) == 0) (range 2 x)) == 0

primes :: [Int] -> [Int]
primes l = filter isPrime l