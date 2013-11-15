'''
Created on Jan 25, 2013

@author: mihai
'''
from Domain.Contact import Contact
from Domain.ContactValidator import AgendaError

class AgendaController:
    """
    Controller class
    """
    def __init__(self, contactRepository, contactValidator):
        """
        Constructor method
        contactRepository is an instance of ContactRepository
        contactValidator is an instance of ContactValidator
        """
        self.__validator = contactValidator
        self.__contactRepository = contactRepository
        
    def addContact(self, id, name, phoneNr, group):
        """
        Controller method that adds a new contact
        id, name, phoneNr, group are strings
        returns a list of error strings if parameters are not valid
        """
        newContact = Contact(id, name, phoneNr, group)
        try:
            nameIsInList = False
            contactList = self.__contactRepository.getAll()
            for contact in contactList:
                if contact.getName() == name:
                    nameIsInList = True
            self.__validator.validate(newContact, nameIsInList)
            self.__contactRepository.add(newContact)
            
        except AgendaError as error:
            return error.getErrors()
        
    def lookup(self, name):
        """
        Controller method that finds contacts by name
        name is a string
        returns the result of the find(name) repository method
        """
        return self.__contactRepository.find(name)
    
    def lookupAll(self, group):
        """
        Controller method that finds all the contacts that belong to a given group
        group is a string
        returns the result of the getAllFor(group) repository method
        """
        return self.__contactRepository.getAllFor(group)
    
    def exportCSV(self, group, outFile):
        """
        Controller method that exports a CSV (Comma Separated Values) file
        group and outFile are strings
        returns False is the group is not valid
        returns "file error" if the file could not be created (not enough space, etc.)
        """
        contacts = self.__contactRepository.getAllFor(group)
        if contacts == False:
            return False
        try:
            file = open(outFile, "w")
            for contact in contacts:
                file.write(contact.getName() + "," + contact.getPhoneNr()+ "\n")
            file.close()
        except IOError:
            return "file error"
            
            