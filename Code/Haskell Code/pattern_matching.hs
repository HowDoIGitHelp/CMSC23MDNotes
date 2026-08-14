secondElement :: [a] -> a
secondElement (e1:e2:list) = e2

isSolo :: [a] -> Bool
isSolo (_:[]) = True
isSolo _ = False

fullname :: [Char] -> [Char] -> [Char] -> [Char]
fullname fname mname lname = fname ++ " " ++ [minitial] ++ ". " ++ lname
    where (minitial:_) = mname

