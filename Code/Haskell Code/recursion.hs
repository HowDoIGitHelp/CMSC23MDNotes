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
