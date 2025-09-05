



line :: Int -> String
line n = 
    if n == 0
        then ""
        else line (n - 1) ++ "*"

triangle :: Int -> String
triangle n = 
    if n == 0
        then ""
        else triangle (n-1) ++ "\n" ++ line n
