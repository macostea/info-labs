
#include "medicationRepository.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#include "../utils/pointers.h"

MedicationRepository* initRepository(char* fileName)
{
	MedicationRepository* repo=malloc(sizeof(MedicationRepository));
	repo->medList=(vectorDinamic*)malloc(sizeof(vectorDinamic));
	repo->fileName=copyString(fileName);
	create(repo->medList, cmpMedications, cpyMedication, delMedication);
	loadMedications(repo);
	return repo;
}

void destroyRepository(MedicationRepository* repo)
{
	if(repo!=0){
		free(repo->fileName);
		destroy(repo->medList);
		free(repo);
	}
}

void saveMedications(MedicationRepository* repo)
{
	int i;
	FILE* f=fopen(repo->fileName,"w");
	for(i=0;i<repo->medList->len;i++){
		Medication* m=get(repo->medList,i);
		if(m!=0){
			char* str=writeMedication(m);
			fprintf(f,"%s",str);
			free(str);
			free(m);
		}

	}
}

void loadMedications(MedicationRepository *repo)
{
	FILE* f=fopen(repo->fileName,"r");
	if(f!=NULL){
		char name[20];
		int code,concentration,quantity;

		while(fscanf(f,"%4d | %20s | %4d | %4d", &code,name,&concentration,&quantity)!=EOF){
			Medication* m=malloc(sizeof(Medication));
			initMedication(m,code,name,concentration,quantity);
			add(repo->medList,m);
		}
		fclose(f);
	}
}

Medication** getAll(MedicationRepository* repo,int* count)
{
	*count=repo->medList->len;
	return repo->medList->elems;
}

void addMedication(MedicationRepository* repo, Medication* m)
{
	int lenght,i;
	int found=0;
	lenght=repo->medList->len;
	for(i=1;i<lenght;i++)
		if(repo->medList->elems[i]!=0)
			if(getCode(repo->medList->elems[i])==getCode(m))
				found=1;
	if(found==0){
		add(repo->medList,m);
		saveMedications(repo);
	}
}

void removeMedication(MedicationRepository* repo, int code)
{
	int l,i=0,found=0;
	l=repo->medList->len;
	while(found==0 && i<l){
		if(cmpMedicationCode(repo->medList->elems[i],code)==1){
			repo->medList->del(repo->medList->elems[i]);
			repo->medList->elems[i]=0;
			found=1;
		}
		i++;
	}
	saveMedications(repo);
}

void updateMedication(MedicationRepository* repo,Medication* m){
	int i=0,found=0,l;
	l=repo->medList->len;
	while (found==0 && i<l){
		if (cmpMedicationCode(repo->medList->elems[i],m->code)==1){
			repo->medList->elems[i]=repo->medList->cpy(m);
			found=1;
		}
		i++;
	}
	saveMedications(repo);
}

Medication** findByQuantity(MedicationRepository* repo,int* quantity, int* length){
	int l;
	l=repo->medList->len;
	Medication** found=malloc(l*sizeof(Medication*));
	int i,j=0;
	for(i=0;i<l;i++){
		if (repo->medList->elems[i]!=0){
			if (cmpMedicationsQuantity(repo->medList->elems[i], quantity)==0){
				found[j]=repo->medList->cpy(repo->medList->elems[i]);
				j++;
			}
		}
	}
	Medication** ret=malloc(j*sizeof(Medication*));
	memcpy(ret, found, j*sizeof(Medication*));
	*length=j;
	free(found);
	return ret;
}

Medication** sortByNameA(MedicationRepository* repo,int* l){
	int j,i,len;
	Medication* aux;
	len=repo->medList->len;
	Medication** ret=malloc(len*sizeof(Medication*));
	memcpy(ret,repo->medList->elems,len*sizeof(Medication*));
	for(i=0;i<len;i++){
		for(j=i+1;j<len;j++){
			if(ret[j]!=0 && ret[i]!=0){
				if (cmpMedicationName(ret[i],ret[j])>0){
					aux=repo->medList->cpy(ret[i]);
					ret[i]=repo->medList->cpy(ret[j]);
					ret[j]=repo->medList->cpy(aux);
				}
			}
		}
	}
	*l=len;
	return ret;
}


Medication** sortByNameD(MedicationRepository* repo,int* l){
	int j,i,len;
	Medication* aux;
	len=repo->medList->len;
	Medication** ret=malloc(len*sizeof(Medication*));
	memcpy(ret,repo->medList->elems,len*sizeof(Medication*));
	for(i=0;i<len;i++){
		for(j=i+1;j<len;j++){
			if(ret[j]!=0 && ret[i]!=0){
				if (cmpMedicationName(ret[i],ret[j])<0){
					aux=repo->medList->cpy(ret[i]);
					ret[i]=repo->medList->cpy(ret[j]);
					ret[j]=repo->medList->cpy(aux);
				}
			}
		}
	}
	*l=len;
	return ret;
}

Medication** sortByQuantityA(MedicationRepository* repo,int* l){
	int j,i,len;
	Medication* aux;
	len=repo->medList->len;
	Medication** ret=malloc(len*sizeof(Medication*));
	memcpy(ret,repo->medList->elems,len*sizeof(Medication*));
	for(i=0;i<len;i++){
		for(j=i+1;j<len;j++){
			if(ret[j]!=0 && ret[i]!=0){
				if (cmpMedicationsQuantity(ret[i],ret[j])>0){
					aux=repo->medList->cpy(ret[i]);
					ret[i]=repo->medList->cpy(ret[j]);
					ret[j]=repo->medList->cpy(aux);
				}
			}
		}
	}
	*l=len;
	return ret;
}

Medication** sortByQuantityD(MedicationRepository* repo,int* l){
	int j,i,len;
	Medication* aux;
	len=repo->medList->len;
	Medication** ret=malloc(len*sizeof(Medication*));
	memcpy(ret,repo->medList->elems,len*sizeof(Medication*));
	for(i=0;i<len;i++){
		for(j=i+1;j<len;j++){
			if(ret[j]!=0 && ret[i]!=0){
				if (cmpMedicationsQuantity(ret[i],ret[j])<0){
					aux=repo->medList->cpy(ret[i]);
					ret[i]=repo->medList->cpy(ret[j]);
					ret[j]=repo->medList->cpy(aux);
				}
			}
		}
	}
	*l=len;
	return ret;
}
