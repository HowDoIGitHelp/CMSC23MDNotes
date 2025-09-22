firetype(charmander).
firetype(charizard).
watertype(squirtle).
flyingtype(charizard).


foo.
isresistantto(X,Y) :- watertype(X),firetype(Y).
isresistantto(X,Y) :- watertype(X),watertype(Y).
