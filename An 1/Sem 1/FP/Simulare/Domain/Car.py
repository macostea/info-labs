'''
Created on Jan 16, 2013

@author: mihai
'''

class Car:
    """
    Car class
    """
    def __init__(self, nr, modelName, type, pricePerDay):
        """
        Contructor
        nr, modelName, type are str
        pricePerDay is an int
        """
        self.setNr(nr)
        self.setModelName(modelName)
        self.setType(type)
        self.setPricePerDay(pricePerDay)
        
    def setNr(self, nr):
        """
        Sets the car number
        """
        self.__nr = nr
        
    def getNr(self):
        """
        Returns the car number
        """
        return self.__nr
        
    def setModelName(self, modelName):
        """
        Sets the model name
        """
        self.__modelName = modelName
        
    def getModelName(self):
        """
        Returns the model name
        """
        return self.__modelName
        
    def setType(self, type):
        """
        Sets the car type
        """
        self.__type = type
        
    def getType(self):
        """
        Returns the car type
        """
        return self.__type
        
    def setPricePerDay(self, pricePerDay):
        """
        Sets the price per day
        """
        self.__pricePerDay = pricePerDay
        
    def getPricePerDay(self):
        """
        Returns the price per day
        """
        return self.__pricePerDay
    
    def __str__(self):
        return self.getNr() + ", " + self.getModelName() + ", " + self.getType() + ", " + str(self.getPricePerDay())