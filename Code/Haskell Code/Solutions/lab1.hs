cube :: Int -> Int
cube x = x * x * x

double :: Int -> Int
double x = x + x

modulus :: Int -> Int -> Int
modulus x m = if (x < m) then x else (modulus (x-m) m)

factorial :: Int -> Int
factorial x =  if (x <= 1) then 1 else x*(factorial (x-1))

compose :: (Int -> Int) -> (Int -> Int) -> (Int -> Int)
compose f g = \x -> f (g x)

subtractMaker :: Int -> (Int -> Int)
subtractMaker x = (\y -> x - y)

applyNTimes :: (Int -> Int) -> Int -> Int -> Int
applyNTimes f n x = if (n <= 0) then x else f (applyNTimes f (n-1) x)

my_map :: (a -> b) -> [a] -> [b]
my_map f l = 
  if (length l) == 0 then []
  else [f (head l)] ++ my_map f (tail l)

my_filter :: (a -> Bool) -> [a] -> [a]
my_filter p l = 
  if length l == 0 then []
  else (if p (head l) then [head l] else []) ++ my_filter p (tail l)

my_foldl :: (a -> a -> a) -> a -> [a] -> a
my_foldl f u l =
  if (length l) == 0 then u
  else f (my_foldl f u (init l)) (last l) 

my_foldr :: (a -> a -> a) -> a -> [a] -> a
my_foldr f u l =
  if (length l) == 0 then u
  else f (head l) (my_foldr f u (tail l))

my_zip :: (a -> b -> c) -> [a] -> [b] -> [c]
my_zip f l m = 
  if ((length l) == 0 || (length m) == 0) then []
  else [f (head l) (head m)] ++ my_zip f (tail l) (tail m)