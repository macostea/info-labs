'''
Created on Jan 25, 2013

@author: mihai
'''
from Domain.Contact import Contact


class ContactRepository:
    """
    Contact repository class
    """
    def __init__(self, filename):
        """
        Constructor method
        filename is a string containing the name of the repository file
        """
        self.__filename = filename
        self.__contactList = []
        self.__loadContacts()
        
    def __loadContacts(self):
        """
        Private method that loads the contacts from the repository file into memory
        """
        try:
            contactsFile = open(self.__filename, "r")
            line = "nonempty"
            while line != "":
                line = contactsFile.readline()
                if line != "":
                    elements = line.split(",")
                    newContact = Contact(int(elements[0].strip()), elements[1].strip(), elements[2].strip(), elements[3].strip())
                    self.__contactList.append(newContact)
        except IOError: #file does not exist, create it
            contactsFile = open(self.__filename, "w")
            
        contactsFile.close()
        
    def __writeList(self):
        """
        Private method that writes the contact list from memory to the repository file
        """
        contactsFile = open(self.__filename, "w")
        for contact in self.__contactList:
            contactsFile.write(str(contact.getId()) + "," + contact.getName() + "," + contact.getPhoneNr() + "," + contact.getGroup() + "\n")
        contactsFile.close()
        
    def add(self, contact):
        """
        This method adds a new contact to the list and writes it to the repository file
        contact is an instance of the Contact class
        """
        self.__contactList.append(contact)
        self.__writeList()
        
    def find(self, name):
        """
        This method checks if a name is in the contact list
        name is a string
        returns the contact phone number if found
        returns False otherwise
        """
        for contact in self.__contactList:
            if contact.getName() == name:
                return contact.getPhoneNr()
        return False
    
    def getAllFor(self, group):
        """
        This method gets all the contacts that belong to a given group
        group is a string that can be "Friends", "Family", "Others"
        returns False if group is not valid
        returns a list of contact instances if everything is ok
        returns an empty list if no contacts were found
        """
        if group not in {"Friends", "Family", "Others"}:
            return False
        returnList = []
        for contact in self.__contactList:
            if contact.getGroup() == group:
                returnList.append(contact)
        return returnList
    
    def getAll(self):
        """
        This method returns the entire list of contact instances
        """
        return self.__contactList
        