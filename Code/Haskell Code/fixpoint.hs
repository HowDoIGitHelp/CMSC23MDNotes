fix :: (a -> a) -> a
fix f = f (fix f)

s :: (Int -> Int) -> Int -> Int
s r 0 = 0
s r n = n + r (n - 1)


summation :: Int -> Int
summation = fix s

{-
summation 2
fix s 2
s (fix s) 2
2 + (fix s) 1
2 + (s (fix s)) 1
2 + 1 + (fix s) 0
2 + 1 + (s (fix s)) 0
2 + 1 + 0
-}
