



line :: Int -> String
line n = 
    if n == 0 
        then "" 
        else "*" ++ line (n - 1)

triangle :: Int -> String
triangle n =
    if n == 0
        then ""
        else triangle (n - 1) ++ "\n" ++ line n


maxElement :: [Int] -> Int
maxElement l =
    if (length l) == 0
        then error "empty list"
        else if (length l) == 1
            then (head l)
            else max (head l) (maxElement (tail l))


