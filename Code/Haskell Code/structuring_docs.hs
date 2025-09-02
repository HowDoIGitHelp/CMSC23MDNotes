isPrefix :: String -> String -> Bool
isPrefix prefix word = prefix == take (length prefix) word

position :: String -> String -> Int
position sub string = 
    if (length string) == 0 
        then 0
        else (if isPrefix sub string 
            then 0 
            else (1 + position sub (tail string))
            )

tokens :: String -> String -> [String]
tokens chain delimeter = 
    if pos >= (length chain)
        then [chain]
        else [take pos chain] ++ (tokens remainder_string delimeter) 
    where 
        pos = position delimeter chain
        remainder_string = drop (pos + (length delimeter)) chain
