'''
Created on Jan 25, 2013

@author: mihai
'''

class AgendaError(Exception):
    """
    Custom exception class
    """
    def __init__(self, errorList):
        """
        Constructor method
        errorList is a list of strings
        """
        self.__errorList = errorList
        
    def getErrors(self):
        """
        Error list getter
        Returns the error list
        """
        return self.__errorList

class ContactValidator:
    """
    Contact validator class
    """
    
    def __init__(self):
        """
        Constructor method
        """
        self.__errorList = []
    
    def validate(self, contact, nameIsInList=False):
        """
        This method validates a contact
        contact is an instance of the Contact class
        raises AgendaError if the contact is not valid
        """
        if contact.getName() == "":
            self.__errorList.append("Name is empty")
            
        if nameIsInList:
            self.__errorList.append("Can not add more than one phone number for the same name")
        
        if contact.getGroup() not in {"Friends", "Family", "Others"}:
            self.__errorList.append("Group is not correct")
            
        if contact.getPhoneNr() == "":
            self.__errorList.append("Phone number is empty")
            
        try:
            int(contact.getPhoneNr()) #check if the phone number contains letters
            
        except:
            self.__errorList.append("Phone number must only contain digits")
            
        if len(self.__errorList) != 0:
            raise AgendaError(self.__errorList)