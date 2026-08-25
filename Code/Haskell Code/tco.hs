module Tco where

tail_summation :: Int -> Int
tail_summation n = s n 0
    where
        s 0 !sum = sum
        s n !sum = s (n - 1) (n + sum)

main :: IO()
main = do 
    print (tail_summation 100000)
