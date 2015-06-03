trace

domains
elem=integer
list=elem*

predicates
max(elem,list)

clauses
max(A,[A|L]):-
max(A,L).

max(E,[A|L]):-
E>A,
max(E,L),
!.

max(E,[A|L]):-
E<A,
max(A,L),
!.

goal
max(E,[1,5,7,43,6,3,8,54,43]),
write(E).