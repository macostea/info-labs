'''
Created on Jan 16, 2013

@author: mihai
'''

class CarRentalUI:
    """
    Main UI class
    """
    def __init__(self, rentController):
        """
        Constructor
        rentController is an instance of the controller class
        """
        self.__menu = "(1)Find car based on type\n(2)Rent a car\n(x)Exit"
        self.__controller = rentController
        self.showMenu()
    
    def showMenu(self):
        """
        Prints out the menu and handles the user input
        """
        user_input = ""
        while user_input != "x":
            print self.__menu
            user_input = raw_input("Please enter your choice:")
            if user_input == "1":
                user_input = raw_input("Enter the type:")
                list = self.__controller.lookup(user_input)
                for car in list:
                    print car
                user_input = ""
            elif user_input == "2":
                carNumber = raw_input("Car number:")
                numberOfDays = input("Number of days:")
                result = self.__controller.rent(carNumber, numberOfDays)
                if result == False:
                    print "The car was not found"
                else:
                    for item in result:
                        print item
                user_input = ""