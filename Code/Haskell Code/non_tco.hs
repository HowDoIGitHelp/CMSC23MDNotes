module Non_tco where

summation :: Int -> Int
summation 0 = 0
summation n = n + summation (n - 1)

main :: IO()
main = do 
    print (summation 100000)
