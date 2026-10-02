:- use_module(library(clpfd)).

person(id(artman, 16)).
person(id(bartman, 19)).
person(id(cartman, 21)).
adult(Name) :-
    person(id(Name, Age)),
    Age #>= 18.
