numeral(0).
numeral(s(X)) :- numeral(X).

add(A,0,A).
add(A,s(B),s(C)) :- add(A,B,C).

mult(_,0,0).
mult(A,s(B),C) :- mult(A,B,D), add(A,D,C).