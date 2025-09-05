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
    if n == 2 || n == 3
        then True
    else not (hasFactors n (range 2 (n - 1)))
