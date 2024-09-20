s :: Int -> Int
s x = x + 1

p :: Int -> Int
p x = x - 1

add :: Int -> Int -> Int
add x y = x + y

applyTwice :: (Int -> Int) -> Int -> Int
applyTwice f x = f (f x)

addMaker :: Int -> (Int -> Int)
addMaker x = (\y -> x + y)