s :: Int -> Int
s x = x + 1

p :: Int -> Int
p x = x - 1

applyTwice :: (Int -> Int) -> Int -> Int
applyTwice f x = f (f x)

addMaker :: Int -> (Int -> Int)
addMaker x = (\y -> x + y)