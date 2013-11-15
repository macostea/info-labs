'''
Created on Oct 26, 2012

@author: Mihai Costea

The check module groups together all the validation methods in the app
'''
from types import ListType, StringType, IntType

def check_parameter(parameter, expected_type):
    """
    Checks if the parameters are of the expected types
    parameter is the parameter to be checked
    expected_type is a str "list", "str", "int", "in/out"
    Returns True if the parameter is of the expected type, False otherwise
    """
    if expected_type == "list":
        if type(parameter) is ListType:
            return True
    elif expected_type == "str":
        if type(parameter) is StringType:
            return True
    elif expected_type == "int":
        if type(parameter) is IntType:
            return True
    elif expected_type == "in/out":
        if type(parameter) is StringType and parameter.lower() in ('in', 'out'):
            return True
    return False

def check_day(day):
    """
    Checks if the passed day is valid
    day is int
    Returns True if valid, False otherwise
    """
    if day in range(1,32):
        return True
    return False
