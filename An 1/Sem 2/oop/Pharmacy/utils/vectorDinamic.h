

#ifndef VECTORDINAMIC_H_
#define VECTORDINAMIC_H_

typedef  void* TElem;

typedef int (*CmpFun)(TElem,TElem);
typedef TElem(*CpyFun)(TElem);
typedef void(*DelFun)(TElem);

typedef struct
{
	TElem* elems;
	int len;  //nr de elemente din vector
	int capacity; //capacitatea maxima
	CmpFun cmp;
	CpyFun cpy;
	DelFun del;
}vectorDinamic;

/*
 * desc: creeaza un vector dinamic de elemente generice
 * pre: cmp - functie pentru compararea a doua elemente generice
 *      cpy - funtie de clonare
 *      del - functie de dealocare a unui element generic
 * post: v: VectorDinamic, v.size=0
 */
void create(vectorDinamic* v, CmpFun cmp, CpyFun cpy, DelFun del);


/*
 * desc: adauga un element in vector
 */
void add(vectorDinamic* v, TElem e);

/*
 * desc: returneaza elementul de pe pozitia specificata ca parametru
 * pre: VectorDinamic, pos:integer, 0 <= pos < len(v)
 * returneaza: v[pos]
 */
TElem get(vectorDinamic* v, int pos);


/*
 * dealoca vectorul
 */
void destroy(vectorDinamic* v);

#endif /* VECTORDINAMIC_H_ */
