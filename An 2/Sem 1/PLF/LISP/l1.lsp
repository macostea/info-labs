(defun replaceelem (e k l)
(cond 
((null l) nil)
((= e (car l)) (cons k (cdr l)))
(t (cons (car l) (replaceelem e k (cdr l))))
)
)

(defun sortNoDoubles (l)
(remove-duplicates (sort l #'<))
)

(defun mergeLists (l1 l2)
(cond
((null l1) l2)
((null l2) l1)
((< (car l1) (car l2)) (cons (car l1) (mergeLists (cdr l1) l2)))
((= (car l1) (car l2)) (cons (car l1) (mergeLists (cdr l1) (cdr l2))))
(t (cons (car l2) (mergeLists l1 (cdr l2))))
)
)

(defun isLinear (l)
(cond
((null l) T)
((null (cdr l)) T)
((> (car l) (car (cdr l))) nil)
(T (isLinear (cdr l)))
)
)
