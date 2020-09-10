# Lab Exercise 4 (Drama in the Clue Mansion)

## Task

You can use a knowledge base to represent human relationship networks. This is what you will be doing for this exercise. I've written a few example facts, and rules as your guide below. **Create a prolog knowledge base (".pl") containing the following facts. Also, create a text file containing the answers to to queries you can below.**

- Create a knowledge base and place them all inside a file with a ".pl" extension
  1. *Miss Scarlet, Mrs. White, Mrs. Peacock, Dr. Orchid are female*
  2. Prof. Plum, Colonel Mustard, and Rev. Green are all male
  3. *Miss Scarlet hates Rev. Green.*
  4. Rev. Green and Mrs. White hate each other.
  5. Prof. Plum and Mrs. White hate each other.
  6. Col. Mustard hates all females and Prof. Plum.
  7. Miss Scarlet and Mrs. Peacock both like Dr. Orchid.
  8. Dr. Orchid likes Mrs. Peacock
  9. Miss Scarlet likes Mrs. White
  10. Miss Scarlet and Prof. Plum like each other.
  11. Prof. Plum likes everyone Col. Mustard hates.
  12. *People who hate each other are enemies*
  13. People who like each other are friends
  14. The enemies of someone's enemies is his/her friend.
  
- Based on the knowledge base you created, ask it the following queries by running `swipl labExer4.pl`. Write the solutions to each query into a text file.
  1. *Which pairs are enemies?*
  2. Which pairs are friends?
  3. Which people are liked by Prof. Plum.
  4. Which people like themselves?
  5. Which males are liked by females? (this query must be written as a conjunction)
  6. Which people are hated by the one they like? (this query must be written as a conjunction)
  
## Some example facts and rules as guide

(don't skip writing these facts and rules in your knowledge base so that it works)

```
%Propositions in item 1
female(scarlet).
female(peacock).
female(orchid).

%Proposition in item 2 (Miss Scarlet hates Rev. Green)
hates(scarlet, green).

%Rule in item 12 (People who hate each other are enemies)
enemies(X,Y) :- hates(X,Y), hates(Y,X).
```

## Some example queries

(Although the answers are already provided here, still, copy them on the text file containing the answers from the other queries).

You should get similar answers to the following queries

Which pairs are enemies?

```
?- enemies(A,B).
```

```
A = scarlet,
B = green ;
A = green,
B = scarlet ;
A = plum,
B = white ;
A = white,
B = plum ;
false.
```

Which males are liked by females?

```
?- likes(A,B), female(A), male(B).
```

```
A = scarlet,
B = plum ;
false.
```

## Assessment Criteria

- Completeness of knowledge base - 20
- Accuracy of query results - 20

**Deadline November 30, 2020**