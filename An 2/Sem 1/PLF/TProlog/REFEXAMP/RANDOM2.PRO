%====================== random/2              ===============================
PREDICATES
  run
  get(INTEGER,CHAR)

GOAL makewindow(1,116,96,"Labyrinth generator",5,5,15,60), run.

CLAUSES
  run:-	not(keypressed),
	random(13,ROW),	random(58,COL),	random(11,SEL),
	get(SEL,CHAR), scr_char(ROW,COL,CHAR),
	run.

  get(0,'�').  get(1,'�').  get(2,'�').  get(3,'�').  get(4,'�').
  get(5,'�').  get(6,'�').  get(7,'�').  get(8,'�').  get(9,'�').
  get(10,'�').
