'''
Created on Oct 26, 2012

@author: Mihai Costea

The module calculations groups together all the methods responsible for
calculating transaction parameters like sum and max

'''
from utils.check import check_parameter

def max_of_transactions(transaction_list, transaction_type):
    """
    Returns the day with the maximum transaction value of a given type (in/out)
    transaction_list is a list
    transaction_type is a str - the given transaction type (in/out)
    Returns the day if everything is ok,
    -1 if the transaction_type is not 'in' or 'out' or if transaction_list is not a list
    """
    max_value = 0
    day = 0
    if not check_parameter(transaction_type, "in/out") or not check_parameter(transaction_list, "list"):
        return -1
    else:
        for transaction in transaction_list:
            if transaction['type'] == transaction_type:
                if transaction['value'] > max_value:
                    max_value = transaction['value']
                    day = transaction['day']
    return day
    
def sum_of_transactions(transaction_list, transaction_type):
    """
    Returns the sum of values of transactions of a given type (in/out)
    transaction_list is a list
    transaction_type is the given transaction type (in/out)
    Returns the sum if everything is ok, 
    -1 if the transaction_type is not 'in' or 'out' or if transaction_list is not a list
    """
    result = 0
    if not check_parameter(transaction_type, "in/out") or not check_parameter(transaction_list, "list"):
        return (-1)
    else:
        for transaction in transaction_list:
            if transaction['type'] == transaction_type:
                result += transaction['value']
    return result 

def sold(transaction_list, transaction_day):
    """
    Computes the account sold in the given day
    transaction_list is a list, transaction_day is an int
    Returns the result if everything is ok, False otherwise
    """
    
    result = 0
    if not check_parameter(transaction_day, "int"):
        return False
    else:
        for transaction in transaction_list:
                if transaction["day"] <= transaction_day:
                    if transaction["type"] == "in":
                        result += transaction["value"]
                    else:
                        result -= transaction["value"]
    return result