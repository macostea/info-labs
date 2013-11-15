'''
Created on Oct 26, 2012

@author: Mihai Costea

The module transactionManipulation is in charge of all the operations which have a direct
effect on the transaction list. This includes adding a new transaction, removing transactions,
replacing transactions and printing transactions.
'''
from utils.check import check_parameter
from utils.constants import ONLY_VALUE, VALUE_AND_DAY

def print_transaction(transaction):
    """
    Prints a transaction neatly formatted
    transaction is a dictionary of the form {'day':x (int), 'value':x (int), 'type':x (str), 'description':x (str)}
    """
    print "Day:", transaction['day'], "| Value:", transaction['value'], "| Type:", transaction['type'], "| Description:", transaction['description']
    print "-----------------------------------------------------------"

def add_transaction(transaction_list, transaction_value, transaction_day, transaction_type, transaction_description):
    """
    Adds a transaction to the transaction list
    transaction_list is a list, transaction_value and transaction_day are of type int,
    transaction_type and transaction_description are of type str
    Returns 0 if completed successfully
    Returns 1 if arguments are not of the right type
    """
    if not check_parameter(transaction_list, "list") or not check_parameter(transaction_value, "int") or not check_parameter(transaction_day, "int") or not check_parameter(transaction_type, "in/out") or not check_parameter(transaction_description, "str"):
        return 1
    transaction_list += [{'day': transaction_day, 'value': transaction_value, 'type': transaction_type, 'description': transaction_description}]
    
    return 0

def remove_transaction(transaction_list, transaction_query):
    """
    Removes transactions from the transaction list
    transaction_list is a list, transaction_query is one of: int, str, list of 2 ints
    Returns 0 if completed successfully
    Returns 1 if arguments are not of the right type
    Returns 2 if transaction was not found
    """
    if check_parameter(transaction_query, "int"):  #type int means the user entered a transaction day
        transaction_exists = False
        transaction_day = transaction_query
        for transaction in reversed(transaction_list):
            if transaction['day'] == transaction_day:
                transaction_exists = True
                transaction_list.pop(transaction_list.index(transaction))
    elif check_parameter(transaction_query, "in/out"): #type str means the user entered a transaction type (in/out)
        transaction_exists = False
        transaction_type = transaction_query
        for transaction in reversed(transaction_list):
            if transaction['type'] == transaction_type:
                transaction_exists = True
                transaction_list.pop(transaction_list.index(transaction))
    elif check_parameter(transaction_query, "list"): #type list means the user entered a range of days
        if len(transaction_query) != 2:
            return 1
        start_day = transaction_query[0]
        end_day = transaction_query[1]
        for transaction in reversed(transaction_list):
            if start_day <= transaction['day'] <= end_day:
                transaction_list.pop(transaction_list.index(transaction))
        return 0
    else: 
        return 1
    
    if not transaction_exists:
        return 2
    
    return 0

def replace_transaction(transaction_list, transaction_day, transaction_type, transaction_description, new_transaction_value):
    """
    Replaces the value form a transaction in the transaction list with new_transaction_value
    transaction_list is a list, new_transaction_value and transaction_day are of type int,
    transaction_type and transaction_description are of type str
    Returns 0 if completed successfully
    Returns 1 if arguments are not of the right type
    Returns 2 if the transaction was not found
    """
    if not check_parameter(transaction_list, "list") or not check_parameter(transaction_day, "int") or not check_parameter(transaction_type, "in/out") or not check_parameter(transaction_description, "str") or not check_parameter(new_transaction_value, "int"):
        return 1
    transaction_exists = False
    for transaction in transaction_list:
        if transaction['day'] == transaction_day:
            if transaction['type'] == transaction_type:
                if transaction['description'] == transaction_description:
                    transaction_exists = True
                    transaction['value'] = new_transaction_value
    if not transaction_exists:
        return 2
    return 0

def return_transactions(transaction_list, query, query_type = 0):
    """
    Returns transactions based on a query
    query can be a str or a list
    If query is a str it can be: 'all', 'in' or 'out'
    If query is a list it must have 3 or 5 elements where
    query[0] is 'greater' or 'less'
    query[1] is 'than' and does not matter to the app
    query[2] is a value of which transactions must be less or greater
    query[3] is 'before' or 'after'
    query[4] is a value of which the transaction day must be less or greater
    query_type is a constant: 0 if the query is a single str, ONLY_VALUE if the query only includes value for comparison
    VALUE_AND_DAY if the query includes both value and day for comparison
    """
    if check_parameter(query, "str"):
        if query == "all":
            transaction_number = 1
            for transaction in transaction_list:
                print "Transaction number:", transaction_number
                print_transaction(transaction)
                transaction_number += 1
        if query in ('in','out'):
            transaction_number = 1
            for transaction in transaction_list:
                if transaction['type'] == query:
                    print "Transaction number:", transaction_number
                    print_transaction(transaction)
                    transaction_number += 1
        else:
            return -1
    elif check_parameter(query, "list"):
        transaction_number = 1
        if query_type == ONLY_VALUE:
            if query[0] == "greater":
                for transaction in transaction_list:
                    if transaction['value'] >= query[2]:
                        print "Transaction number:", transaction_number
                        print_transaction(transaction)
                        transaction_number += 1
            elif query[0] == "less":
                for transaction in transaction_list:
                    if transaction['value'] <= query[2]:
                        print "Transaction number:", transaction_number
                        print_transaction(transaction)
                        transaction_number += 1
            else:
                return -1
        elif query_type == VALUE_AND_DAY:
            if query[0] == "greater":
                if query[3] == "before":
                    for transaction in transaction_list:
                        if transaction['value'] >= query[2] and transaction['day'] <= query[4]:
                            print "Transaction number:", transaction_number
                            print_transaction(transaction)
                            transaction_number += 1
                elif query[3] == "after":
                    for transaction in transaction_list:
                        if transaction['value'] >= query[2] and transaction['day'] >= query[4]:
                            print "Transaction number:", transaction_number
                            print_transaction(transaction)
                            transaction_number += 1
                else:
                    return -1
            elif query[0] == "less":
                if query[3] == "before":
                    for transaction in transaction_list:
                        if transaction['value'] <= query[2] and transaction['day'] <= query[4]:
                            print "Transaction number:", transaction_number
                            print_transaction(transaction)
                            transaction_number += 1
                elif query[3] == "after":
                    for transaction in transaction_list:
                        if transaction['value'] <= query[2] and transaction['day'] >= query[4]:
                            print "Transaction number:", transaction_number
                            print_transaction(transaction)
                            transaction_number += 1
                else:
                    return -1
            else:
                return -1
    return 0


