:- use_module(library(clpfd)).

list_length([], 0).
list_length([Head|Tail], Length) :-
    Length #= Tail_length + 1,
    list_length(Tail, Tail_length).

list_head([Head|Tail], Head).

list_take_n(List,0,List).
list_take_n([Head|Tail], N, Leftover) :-
    N #> 0,
    M #= N - 1,
    list_take_n(Tail, M, Leftover).

list_nth_element(List, N, Elem) :-
    list_take_n(List, N, [Elem|Tail]).
