domains
elem=integer
list=elem*

predicates
sort(list,list)

sortAux(list,list)

insertSort(elem,list,list)

goal
sort([5,2,2,2,3,4,4],L),
write(L).

clauses
sort(L,X):-sortAux(L,X).

sortAux([],[]).
sortAux([E|L],X):-sortAux(L,X2),insertSort(E,X2,X).

insertSort(E,[],[E]).
insertSort(E,[A|L],[E,A|L]):-E<A,!.
insertSort(E,[A|L],[A|X]):-insertSort(E,L,X).
