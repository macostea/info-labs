'''
Created on Jan 16, 2013

@author: mihai
'''
from Domain.Car import Car
from Domain.Rent import Rent

class CarRepository:
    """
    Car Repository class
    """
    def __init__(self, filename):
        """
        Constructor
        filename is a str containing the name of the repository file
        """
        self.__file = open(filename, "r")
        self.__carList = []
        self.__rentList = []
        self.__readFromFrile()
        
    def __readFromFrile(self):
        """
        Populates the car list from the file
        """
        line = "Nonempty string"
        while line != "":
            line = self.__file.readline()
            if line != "":
                attributes = line.split(",")
                car = Car(attributes[0], attributes[1], attributes[2], int(attributes[3]))
                self.__carList.append(car)
        
    def findCarByType(self, type):
        """
        Returns a car instance from the list based on the type
        Returns an array of car instances
        Returns and empty list if no cars match the type
        """
        returnList = []
        for car in self.__carList:
            if car.getType() == type:
                returnList.append(car)
        return returnList
    
    def findByNr(self, nr):
        """
        Returns a car instance from the list based on its number
        Returns False if no care has been found
        """
        for car in self.__carList:
            if car.getNr() == nr:
                return car
        return False
    
    def getAll(self):
        """
        Returns the entire list of cars
        """
        return self.__carList
    
    def rent(self, nr, nrDays):
        """
        Rents a car
        nr is the car number
        nrDays is the number of days to be rented
        Returns False if the car was not found
        Returns a list containing a car instance and the total price for rent otherwise
        """
        found = False
        for car in self.__carList:
            if car.getNr() == nr:
                found = True
                foundCar = car
                break
        if not found:
            return False
        rent = Rent(foundCar, nrDays)
        self.__rentList.append(rent)
        return [foundCar, rent.getTotalPrice()]