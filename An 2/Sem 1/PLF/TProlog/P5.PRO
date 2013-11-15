domains
	point=p(integer, integer)	
	list=point*
	llist=list*
	
predicates
	col(point, point, point)
	cond(list)
	subset(list, list)
	subset_col(list,list)
	run(list, llist)

goal
    run([p(1,1),p(1,3),p(2,2),p(3,3),p(2,4),p(3,5)],R),
    write(R).	

clauses
	col(p(X1,Y1),p(X2,Y2),p(X3,Y3)):-(X3-X1)*(Y3-Y2) = (X3-X2)*(Y3-Y1).
		
	cond([p(X1,Y1),p(X2,Y2),p(X3,Y3)]):-col(p(X1,Y1),p(X2,Y2),p(X3,Y3)).	
	cond([p(X1,Y1)|[p(X2,Y2)|[p(X3,Y3)|T]]]):-col(p(X1,Y1),p(X2,Y2),p(X3,Y3)),cond([p(X2,Y2)|[p(X3,Y3)|T]]).
		
	subset([],[]).
	subset([p(_,_)|L],L1):-subset(L,L1).
	subset([p(X,Y)|L],[p(X,Y)|L1]):-subset(L,L1).
	
	subset_col(L,L1):-subset(L,L1),cond(L1).
	
	run(L,LD):-findall(X,subset_col(L,X),LD).
