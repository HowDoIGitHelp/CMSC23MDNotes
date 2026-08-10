:- use_module(library(clpfd)).

list_length([], 0).
list_length([Head|Tail], Length) :-
    list_length(Tail, Tail_length),
    Length #= Tail_length + 1.

list_head([Head|Tail], Head).

list_suffix(List,0,List).
list_suffix([Head|Tail], 1, Tail) :-
    list_length(Tail, Tail_length).
