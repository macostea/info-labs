'''
Created on Jan 16, 2013

@author: mihai
'''

class Rent:
    """
    Rent class
    """
    def __init__(self, car, nrDays):
        """
        Constructor
        car is an instance of a car
        nrDays is an int
        """
        self.setCar(car)
        self.setNrDays(nrDays)
    
    def setCar(self, car):
        """
        Sets the car to be rented
        """
        self.__car = car
        
    def getCar(self):
        """
        Returns the car instance that is rented
        """
        return self.__car
        
    def setNrDays(self, nrDays):
        """
        Sets the number of days to be rented
        """
        self.__nrDays = nrDays
        
    def getNrDays(self):
        """
        Returns the number of days
        """
        return self.__nrDays
    
    def getTotalPrice(self):
        """
        Returns the total price of the rented period
        Returns an int
        """
        return self.getNrDays() * self.__car.getPricePerDay()