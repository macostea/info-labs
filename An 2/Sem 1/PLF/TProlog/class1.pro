domains
elem=integer
list=elem*

predicates
removeElem(list, integer, list, integer)

goal

removeElem([1,2,3,4,5,6,7,8], 2, X, 1),
write(X).

clauses
removeElem([],_,[],_).

removeElem([_|L], R, X, R):-removeElem(L, R, X, 1),!.

removeElem([A|L], R, [A|X], R1):-B=R1+1,removeElem(L,R,X,B).