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
        else [take pos chain] ++ (tokens remainderString delimeter) 
    where 
        pos = position delimeter chain
        remainderString = drop (pos + (length delimeter)) chain

paragraphList :: String -> [String]
paragraphList text = tokens text "\n"

sentenceList :: String -> [String]
sentenceList paragraph = tokens paragraph "."

wordList :: String -> [String]
wordList sentence = tokens sentence " "

kthParagraph :: String -> Int -> String
kthParagraph text x =  (paragraphList text)!!(x - 1)

kthSentenceInMthParagraph :: String -> Int -> Int -> String
kthSentenceInMthParagraph text k m = (sentenceList (kthParagraph text m))!!(k - 1)

kthWordInMthSentenceInNthParagraph :: String -> Int -> Int -> Int -> String
kthWordInMthSentenceInNthParagraph text k m n = (wordList (kthSentenceInMthParagraph text m n))!!(k - 1)
