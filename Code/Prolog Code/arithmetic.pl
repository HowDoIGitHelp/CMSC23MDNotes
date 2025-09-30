numeral(0).
numeral(s(X)) :- numeral(X).


sum(A,0,A).
sum(A,s(B),s(C)) :- sum(A,B,C).


difference(A,B,C) :- sum(B,C,A).

product(_,0,0).
product(A,s(B),C) :- product(A,B,D), sum(A,D,C).

