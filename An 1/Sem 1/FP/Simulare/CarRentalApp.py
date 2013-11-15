'''
Created on Jan 16, 2013

@author: mihai
'''
"""
from Repository.CarRepository import CarRepository
from UI.CarRentalUI import CarRentalUI
from UI.RentController import RentController

class CarRentalApp:
    def __init__(self):
        self.__carRepository = CarRepository("repo.dat")
        self.__rentController = RentController(self.__carRepository)
        self.__carRentalUI = CarRentalUI(self.__rentController)
        

carRentalApp = CarRentalApp()

"""

l = [10,20,4,2,5,6,12,2,9,590,15,20,430,5135]

def qsort(list):
    """
    Quicksort using list comprehensions
    
    """
    if list == []: 
        return []
    else:
        pivot = list[0]
        lesser = qsort([x for x in list[1:] if x < pivot])
        greater = qsort([x for x in list[1:] if x >= pivot])
        return lesser + [pivot] + greater
    

print qsort(l)
