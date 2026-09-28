int(0).
int(s(X)) :- int(X).


add(A,0,A).
add(A,s(B),s(C)) :- add(A,B,C).


sub(A,B,C) :- add(B,C,A).

mult(_,0,0).
mult(A,s(B),C) :-
    mult(A,B,D), add(A,D,C).

