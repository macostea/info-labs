domains
list=integer*
elem=i(integer);l(list)
llist=elem*

predicates

even(integer)
double(list, list)
doubleAll(llist, llist)

goal
doubleAll([i(2),l([1,2,3,6,1]),l([3,4,5]),i(6)], R),
write(R).

clauses
even(E):-E mod 2 = 0.

double([],[]).
double([A|X],[B|R]):-double(X,R), even(A), B=A*2,!.
double([A|X],[A|R]):-double(X,R).

doubleAll([],[]).
doubleAll([i(N)|T], [i(N)|R]):-doubleAll(T,R).
doubleAll([l(L)|T], [l(L1)|R]):-doubleAll(T,R), double(L,L1),!.
doubleAll([l(L)|T], [l(L)|R]):-doubleAll(T,R).
