'''
Created on Jan 16, 2013

@author: mihai
'''
from Repository.CarRepository import CarRepository

def testRepository():
    repository = CarRepository("repo.dat")
    assert repository.findByNr("100BNOT").getType() == "Coupe"
    assert repository.findCarByType("Coupe")[0].getNr() == "04CJCAR"
    assert repository.getAll()[0].getNr() == "01CJWAT"
    assert repository.rent("01CJWAT", 10)[1] == 580