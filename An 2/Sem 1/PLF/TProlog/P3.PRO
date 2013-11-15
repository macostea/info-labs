domains
element=integer
treetype=tree(element, treetype, treetype);nil
list=element*

predicates
traverseaza(treetype,integer,integer)
create_tree(element,treetype)
insert_left(treetype,treetype,treetype)
insert_right(treetype, treetype,treetype)

goal
create_tree(10,Ten),
create_tree(11,Eleven),
create_tree(3,Three),
create_tree(5,Five),
create_tree(8,Eight),
create_tree(4,Four),
create_tree(1,One),

insert_left(Ten,Eleven,Eleven1),
insert_right(Three,Eleven1,Eleven2),
insert_left(Five,Eight,Eight1),
insert_right(Four,Eight1,Eight2),
insert_left(Eleven2,One,One1),
insert_right(Eight2,One1,FinalTree),

%write(FinalTree), nl,
Nivel=0,
traverseaza(FinalTree,1,Nivel),nl.

clauses

traverseaza(nil,_,_).

traverseaza(Tree,N,N):-write(Tree),nl,!.

traverseaza(tree(_,S,D),X,N):-N1=N+1,traverseaza(S,X,N1),traverseaza(D,X,N1).

create_tree(A,tree(A,nil,nil)).
insert_left(X,tree(A,_,B),tree(A,X,B)).
insert_right(X,tree(A,B,_),tree(A,B,X)).