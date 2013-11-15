'''
Created on Jan 16, 2013

@author: mihai
'''
from Domain.Car import Car
from Domain.Rent import Rent

def testCar():
    car = Car("08CJRER","FORD","Van",90)
    assert car.getNr() == "08CJRER"
    assert car.getModelName() == "FORD"
    assert car.getType() == "Van"
    assert car.getPricePerDay() == 90

def testRent():
    car = Car("08CJRER","FORD","Van",90)
    rent = Rent(car, 3)
    assert rent.getCar() == car
    assert rent.getNrDays() == 3
    assert rent.getTotalPrice() == 270