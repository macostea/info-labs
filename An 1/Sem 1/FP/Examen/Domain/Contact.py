'''
Created on Jan 25, 2013

@author: mihai
'''

class Contact:
    """
    Contact class
    """
    def __init__(self, id, name, phoneNr, group):
        """
        Constructor method
        id is an int
        name, phoneNr and group are nonempty strings
        phoneNr is a string containing only numbers
        group is a string and can be one of "Friends", "Family", "Others"
        """
        self.__id = id
        self.__name = name
        self.__phoneNr = phoneNr
        self.__group = group
        
    def getId(self):
        """
        Id getter method
        returns the id of the contact (int)
        """
        return self.__id
    
    def getName(self):
        """
        Name getter method
        returns the name of the contact (str)
        """
        return self.__name
    
    def getPhoneNr(self):
        """
        Phone number getter method
        returns the phone number of the contact (str)
        """
        return self.__phoneNr
    
    def getGroup(self):
        """
        Group getter method
        returns the group of the contact (str)
        """
        return self.__group
    
    def __str__(self):
        return str(self.getId()) + ", " + self.getName() + ", " + self.getPhoneNr() + ", " + self.getGroup() + "\n"