'''
Created on Jan 16, 2013

@author: mihai
'''

class RentController():
    def __init__(self, carRepository):
        self.__carRepository = carRepository
    
    def lookup(self, type):
        """
        Controller method for getting all cars of a specific type
        type is a str
        Returns a list of found cars
        """
        return self.__carRepository.findCarByType(type)
    
    def rent(self, nr, nrDays):
        """
        Controller method for renting a car
        nr is a str
        nrDays is an int
        Returns False if the car was not found
        Returns a list containing the car instance and the total price for rent
        """
        return self.__carRepository.rent(nr, nrDays)