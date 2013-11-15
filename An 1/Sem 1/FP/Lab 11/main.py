'''
Created on Dec 16, 2012

@author: mihai
'''
from bingoSort import bingoSort
from compareFunctions import compareInts, compareNames
from lists import intList, nameList
from mergeSort import mergeSort

def mergeSortInts():
    print mergeSort(intList, compareInts)
    
def mergeSortNames():
    print mergeSort(nameList, compareNames)
    
def bingoSortInts():
    newIntList = intList[:]
    bingoSort(newIntList, compareInts)
    print newIntList

if __name__ == '__main__':
    mergeSortInts()
    mergeSortNames()
    bingoSortInts()