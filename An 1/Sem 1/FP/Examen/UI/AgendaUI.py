'''
Created on Jan 25, 2013

@author: mihai
'''


class AgendaUI:
    """
    UI class
    """
    
    def __init__(self, agendaController):
        """
        Constructor method
        agendaController is an instance of AgendaController
        """
        self.__agendaController = agendaController
        self.__menu()
        
    def __menu(self):
        """
        Private method that displays the app menu
        """
        userInput = ""
        while userInput != "x":
            print "(1)Add a new contact\n(2)Lookup a phone number\n(3)Show all contacts of a group\n(4)Export contacts to CSV\n(x)Exit"
            userInput = raw_input("Please enter your choice:")
            
            if userInput == "1": #add new contact
                contactId = raw_input("Id:")
                contactName = raw_input("Name:")
                phoneNumber = raw_input("Phone Number:")
                contactGroup = raw_input("Group:")
                result = self.__agendaController.addContact(contactId, contactName, phoneNumber, contactGroup)
                if result == None:
                    print "Contact added successfully"
                else:
                    for error in result:
                        print error
                
            elif userInput == "2": #lookup phone number
                contactName = raw_input("Name:")
                result = self.__agendaController.lookup(contactName)
                if result == False:
                    print "Contact was not found"
                else:
                    print result
            
            elif userInput == "3": #show all contacts of a group
                contactGroup = raw_input("Group:")
                result = self.__agendaController.lookupAll(contactGroup)
                if result == False:
                    print "Group is not valid"
                else:
                    if len(result) != 0:
                        for contact in result:
                            print contact
                    else:
                        print "No contacts were found for group,", contactGroup
                
            elif userInput == "4": #export CSV
                contactGroup = raw_input("Group:")
                filename = raw_input("Filename:")
                result = self.__agendaController.exportCSV(contactGroup, filename)
                if result == False:
                    print "Group is not valid"
                elif result == "file error":
                    print "An error has occoured. Your file was not created."
                else:
                    print "List exported successfully"
            
            else:
                if userInput != "x":
                    print "INVALID CHOICE"