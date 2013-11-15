%trace

domains
elem=integer
n=integer
list=elem*

predicates
removeElem(elem,list,list)
noAp(elem,list,n)
deleteDups(list,list)

goal
deleteDups([2,2,2,23,1,2,4,5,2,1,6],L),
write(L).

clauses

%remove all apparitions of an element
removeElem(_,[],[]).
removeElem(E,[E|X],R):-
removeElem(E,X,R),
!.
removeElem(E,[A|X],[A|R]):-
removeElem(E,X,R).

%number of apparitions
noAp(_,[],0).
noAp(E,[E|X],R1):-
noAp(E,X,R),
R1=R+1,
!.
noAp(E,[_|X],R):-
noAp(E,X,R).

%delete duplicates
deleteDups([E|X],R):-
noAp(E,[E|X],No),
No>1,
removeElem(E,X,R1),
!,
deleteDups(R1,R).

deleteDups([E|X],[E|R]):-
noAp(E,[E|X],No),
No=1,
!,
deleteDups(X,R).

deleteDups([],[]):-
!.
