%trace

domains
	elem=integer
	list=elem*

predicates
	max(list,elem)
	removeElem(elem,list,list)
	removeMax(list,list)
goal
	removeMax([1,2,5,6,23,6,7,23,6,4,3],R),
	write(R).
	
clauses
	max([E],E).
	max([L1|L],L1):-max(L,E),L1>=E,!.
	max([L1|L],E):-max(L,E),E>L1.
	
	removeElem(_,[],[]).
	removeElem(E,[E|X],R):-removeElem(E,X,R),!.
	removeElem(E,[A|X],[A|R]):-removeElem(E,X,R).
	
	removeMax(L,X):-max(L,R),removeElem(R,L,X).