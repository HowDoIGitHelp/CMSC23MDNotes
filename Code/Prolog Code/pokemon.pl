firetype(charmander).
firetype(charizard).
fact1.
watertype(squirtle).
flyingtype(charizard).

resistanttofire(squirtle) :- watertype(squirtle).
