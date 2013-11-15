'''
Created on Jan 25, 2013

@author: mihai
'''
from Domain.Contact import Contact
from Domain.ContactValidator import ContactValidator, AgendaError

def testContact():
    contact = Contact(2, "new", "12351251", "Family")
    assert contact.getId() == 2
    assert contact.getName() == "new"
    assert contact.getPhoneNr() == "12351251"
    assert contact.getGroup() == "Family"
    
def testValidator():
    validator = ContactValidator()
    contact = Contact(2, "new", "12351251", "Family")
    try:
        validator.validate(contact)
        assert True
    except AgendaError as error:
        assert False

    contact = Contact(2, "", "123aa51251", "OtherGroup")
    try:
        validator.validate(contact)
        assert False
    except AgendaError as error:
        errorList = error.getErrors()
        assert "Name is empty" in errorList
        assert "Phone number must only contain digits" in errorList
        assert "Group is not correct" in errorList