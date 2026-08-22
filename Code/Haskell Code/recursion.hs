{-# LANGUAGE BangPatterns #-}

line :: Int -> String
line n = 
    if n < 1 
        then ""
        else "*" ++ line (n - 1) 

triangle :: Int -> String
triangle n =
    if n < 1
        then ""
        else (triangle (n - 1)) ++ "\n" ++ (line n)

range :: Int -> Int -> [Int]
range m n = 
    if m > n
        then []
        else [m] ++ range (m + 1) n


hasFactors :: Int -> [Int] -> Bool
hasFactors n l =
    if (length l) == 0 then False
    else if (mod n (head l)) == 0
        then True
        else hasFactors n (tail l)

isPrime :: Int -> Bool
isPrime n = 
    if n == 2
        then True
    else not (hasFactors n (range 2 (n - 1)))

maxElement :: [Int] -> Int
maxElement l = 
    if (length l) == 0
        then error "empty list"
        else if (length l) == 1
            then (head l)
            else (max (head l) (maxElement (tail l)))  

line2 :: Int -> String
line2 n = map (\x -> '*') [1..n]

triangle2 :: Int -> String
triangle2 n = foldr concatnl "" lineList
    where 
        concatnl = (\x y -> x ++ "\n" ++ y)
        lineList = (map line2 [0..n])

factors :: Int -> [Int]
factors n = filter (\x -> mod n x == 0) [1..n]

lastCommon :: [Int] -> [Int] -> Int
lastCommon l m =
    if (length l) == 0 || (length m) == 0
        then error "nothing in common"
        else if (last l) > (last m)
            then lastCommon (init l) m
            else if (last l) < (last m)
                then lastCommon l (init m)
                else (last l)

lastCommon2 l m
    | (length l) == 0 || (length m) == 0 = error "nothing in common"
    | (last l) > (last m) = lastCommon (init l) m
    | (last l) < (last m) = lastCommon l (init m)
    | otherwise = (last l)

gcf :: Int -> Int -> Int
gcf a b = lastCommon (factors a) (factors b)

maxElement2 :: [Int] -> Int
maxElement2 l = foldr max (head l) (tail l)



addOne :: [Int] -> [Int]
addOne l =
    if length l == 0
        then []
        else [(head l) + 1] ++ addOne (tail l)



double :: [Int] -> [Int]
double l = map f l
    where
        f = (\x -> x * 2)

candidateFactors :: Int -> [Int]
candidateFactors x = [2..((ceiling.sqrt.fromIntegral) x)]

isDivisible :: Int -> Int-> Bool
isDivisible x y = (mod x y) == 0

n_asterisks :: Int -> String
n_asterisks n = 
    if n <= 0 then ""
    else "*" ++ n_asterisks (n - 1)

is_not_greater :: Int -> [Int] -> Bool
is_not_greater n list = 
    if (length list) == 0 then True
    else (n <= (head list)) && is_not_greater n (tail list)

is_sorted :: [Int] -> Bool
is_sorted list = 
    if (length list) == 0 then True
    else (is_not_greater (head list) (tail list)) && (is_sorted (tail list))

index_of :: Int -> [Int] -> Int
index_of elem list = index_of_inner elem list 0
    where
        index_of_inner elem list index = 
            if (length list) == 0 then error "element not found"
            else if ((head list) == elem) then index
                else (index_of_inner elem (tail list) (index + 1))

summation :: Int -> Int-> Int
summation 0 0 = 0
summation n m = n + summation (n - 1) (m - 1)

tail_summation :: Int -> Int
tail_summation n = s n 0
    where
        s 0 !sum = sum
        s n !sum = s (n - 1) (n + sum)

merge :: Ord a => [a] -> [a] -> [a]
merge l [] = l
merge [] l = l
merge (x:xs) (y:ys)
    | x <= y = x : merge xs (y:ys)
    | otherwise = y : merge (x:xs) ys

mergeSort :: Ord a => [a] -> [a]
mergeSort [] = []
mergeSort [x] = [x]
mergeSort l = merge (mergeSort left) (mergeSort right)
    where
        n = quot (length l) 2
        left = take n l
        right = drop n l
