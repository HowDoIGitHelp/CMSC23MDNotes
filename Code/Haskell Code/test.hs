

s :: Int -> Int
s x = x + 1

p :: Int -> Int
p x = x - 1

applyTwice :: (Int -> Int) -> Int -> Int
applyTwice f x = f (f x)

applyThrice :: (Int -> Int) -> Int -> Int
applyThrice f x = f (f (f x))

addTwo :: Int -> Int
addTwo x = x + 2

addThree :: Int -> Int
addThree x = x + 3

add :: Int -> Int -> Int
add x y = x + y

addFour :: Int -> Int
addFour x = x + 4

addMaker :: Int -> (Int -> Int)
addMaker x = (\y -> y + x)


subtractMaker :: Int -> (Int -> Int)
subtractMaker x = (\y -> x - y)




