
#include "vectorDinamic.h"
#include <stdlib.h>
#include <stdio.h>


void create(vectorDinamic* v, CmpFun cmp, CpyFun cpy, DelFun del)
{
	if(v!=0){
		v->len=0;
		v->capacity=10;
		v->elems=(TElem)malloc(sizeof(TElem));
		v->cmp=cmp;
		v->cpy=cpy;
		v->del=del;
	}
}

void resize(vectorDinamic* v)
{
	v->capacity = 2 * v->capacity; //dublam capacitatea
	TElem* elems = v->elems; //salvam elementele
	v->elems= malloc(v->capacity*(sizeof(TElem))); //realocam memorie -capacitate mai mare
	int i;
	for(i = 0; i < v->len; i++) //copiem elementele in noul vector de elementele, avand capacitate mai mare
	{
		v->elems[i] = elems[i];
	}
	free(elems);
}

void add(vectorDinamic* v, TElem e)
{
	if (v->len==v->capacity)
		resize(v);
	TElem newElem=v->cpy(e);
	v->elems[v->len]=newElem;
	(v->len)++;
}


TElem get(vectorDinamic* v, int pos)
{
	TElem clone;
	if(v->elems[pos]!=0)
		clone=v->cpy(v->elems[pos]);
	else
		clone=0;
	return clone;
}

void destroy(vectorDinamic* v)
{
	int i;
	for (i=0; i<v->len; i++){
		v->del(v->elems[i]);
	}
	free(v);
}




