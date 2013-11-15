'''
Created on Oct 26, 2012

@author: Mihai Costea

Module ui represents the user interface which the app will use.
It also calls the necessary methods from the domain.

'''

from datetime import datetime
from domain.calculations import max_of_transactions, sum_of_transactions, sold
from domain.transactionManipulation import add_transaction, remove_transaction, \
    replace_transaction, return_transactions
from utils.check import check_day, check_parameter
from utils.constants import ONLY_VALUE, VALUE_AND_DAY


menu = "(1) Add a new transaction\n(2) Modify transactions\n(3) View transactions\n(4) Calculate\n(5) Filter\n(6) Undo last operation"
submenu1 = "(1) Add a transaction to the current day\n(2) Insert a transaction in another day"
submenu2 = "(1) Remove transactions\n(2) Replace transactions"
submenu3 = "(1) Show all the transactions\n(2) Show all transactions of a given type (in/out)\n(3) Show transactions with other properties\n(4) Show the account sold in a given day"
submenu4 = "(1) Sum of all the transactions of the same type (in/out)\n(2) Return the day with the maximum value of transactions of a given type (in/out)"
submenu5 = "(1) Keep only transactions which are of a given type (in/out)"

separator = "\n--------------------------\n"
back = "(x) Back"
quit_app = "(x) Exit the application"
invalid = "INVALID CHOICE"

menu_back = "back"
exit_app = "exit"

transaction_list = [{'type': 'in', 'day': 12, 'value': 100, 'description': 'desc'}, {'type': 'out', 'day': 12, 'value': 20, 'description': 'newdesc'}, {'type': 'in', 'day': 20, 'value': 140, 'description': 'newerdesc'}]
undo_list = list()

now = datetime.now()

def subMenu(subMenuNumber):
    if subMenuNumber == 1: #-------Start of submenu 1
        print submenu1, separator, back
        user_input = raw_input ("Please enter your choice:")
        user_input = user_input.lower()
        if user_input == "1":
            current_day = now.day
            try:
                transaction_value = input("Please insert the value:")
                transaction_type = raw_input("Please insert the transaction type (in/out):")
                transaction_type = transaction_type.lower()
                transaction_description = raw_input("Please enter a description:")
                transaction_description = transaction_description.lower()
                undo_list.append(list(transaction_list))
                if add_transaction(transaction_list, transaction_value, current_day, transaction_type, transaction_description) == 0:
                    raw_input ("*Transaction added successfully.*\n")
                else:
                    undo_list.pop()
                    raw_input ("*Transaction details are not of valid types. Please try again.*")
            except:
                raw_input ("*VALUE MUST BE A NUMBER*")
                
        elif user_input == '2':
            try:
                transaction_day = input("Please insert the day")
                if type(transaction_day) == int and check_day(int(transaction_day)):
                    try:
                        transaction_value = input("Please insert the value:")
                        transaction_type = raw_input("Please insert the transaction type (in/out):")
                        transaction_type = transaction_type.lower()
                        transaction_description = raw_input("Please enter a description:")
                        transaction_description = transaction_description.lower()
                        undo_list.append(list(transaction_list))
                        if add_transaction(transaction_list, transaction_value, transaction_day, transaction_type, transaction_description) == 0:
                            raw_input ("*Transaction added successfully.*\n")
                        else:
                            undo_list.pop()
                            raw_input ("*Transaction details are not of valid types. Please try again.*\n")
                    except:
                        raw_input ("*VALUE MUST BE A NUMBER!*")
                else:
                    print '*', transaction_day, "is not a valid day*"
                    raw_input()
            except:
                raw_input ("*DAY MUST BE A NUMBER*")

        elif user_input == "x":
            return menu_back
        else:
            raw_input (invalid)   
    #-------Start of submenu 2
    elif subMenuNumber == 2:
        print submenu2, separator, back
        user_input = raw_input ("Please enter your choice:")
        user_input = user_input.lower()
        if user_input == '1':
            print "Please enter the transactions to be removed"
            print "If you want to remove transactions in a single day, enter the day"
            print "If you want to remove transactions that have a specific type (in/out) enter the type"
            print "Use the keywords: 'from' and 'to' to remove all the transactions in that interval"
            print "Example: from 12 to 25 -> removes all the transactions from day 12 to day 25"
            user_input = raw_input(">")
            user_input = user_input.lower()
            result = -1
            try: #we try to see if the user entered an int (a single day)
                transaction_day = int(user_input)
                if not check_day(transaction_day):
                    print '*', transaction_day, "is not a valid day*"
                    raw_input()
                else:
                    undo_list.append(list(transaction_list))
                    result = remove_transaction(transaction_list, transaction_day)
            except: #the user did not enter an int so we move on
                if user_input in ["in","out"]: #the user entered a str (transaction type: 'in' or 'out')
                    transaction_type = user_input
                    undo_list.append(list(transaction_list))
                    result = remove_transaction(transaction_list, transaction_type)
                else: #the user entered a str (range of days: 'from 2 to 10')
                    words = user_input.split()
                    if words[0] == "from" and words[2] == "to":
                        if not check_day(int(words[1])) or not check_day(int(words[3])):
                            raw_input ("*Invalid range*")
                        else:
                            transaction_range = [int(words[1]), int(words[3])]
                            undo_list.append(list(transaction_list))
                            result = remove_transaction(transaction_list, transaction_range)
                    else:
                        raw_input ("*Unrecognized query*")
            if result == 0:
                raw_input ("*Transactions removed successfully.*\n")
            elif result == 1:
                undo_list.pop()
                raw_input ("*Transaction details are not of valid types. Please try again.*\n")
            elif result == 2:
                undo_list.pop()
                raw_input ("*Requested transaction could not be found. Please try again.*\n")                        
        elif user_input == '2':
            try:                
                transaction_day = input("Please enter the day of the transaction to be replaced:")
                if type(transaction_day) == int and check_day(transaction_day):
                    transaction_type = raw_input("Please enter the type (in/out) of the transaction to be replaced:")
                    transaction_type = transaction_type.lower()
                    transaction_description = raw_input("Please enter the description of the transaction to be replaced:")
                    transaction_description = transaction_description.lower()
                    try:
                        new_transaction_value = input("Please enter the new value for the transaction:")
                        undo_list.append(list(transaction_list))
                        result = replace_transaction(transaction_list, transaction_day, transaction_type, transaction_description, new_transaction_value)
                        if result == 0:
                            raw_input ("*Transaction replaced successfully.*\n")
                        elif result == 1:
                            undo_list.pop()
                            raw_input ("*Transaction details are not of valid types. Please try again.*\n")
                        else:
                            undo_list.pop()
                            raw_input ("*Requested transaction could not be found. Please try again.*\n")
                    except:
                        raw_input ("*TRANSACTION VALUE MUST BE A NUMBER*")
                else:
                    print '*', transaction_day, "is not a valid day*"
                    raw_input()
            except:
                raw_input ("*TRANSACTION DAY MUST BE A NUMBER*")
        elif user_input == 'x':
            return menu_back
        else:
            raw_input (invalid)
    #End of submenu 2
    #-------Start of submenu 3
    elif subMenuNumber == 3:
        print submenu3, separator, back
        user_input = raw_input("Please enter your choice:")
        user_input = user_input.lower()
        if user_input == "1":
            return_transactions(transaction_list, "all")
        elif user_input == "2":
            user_input = raw_input("Please enter the type (in/out):")
            user_input = user_input.lower()
            if check_parameter(user_input, "in/out"):
                return_transactions(transaction_list, user_input)
            else:
                raw_input ("*Transaction type is in or out*")
        elif user_input == "3":  #Because this feature would require a long sub-submenu, we implement it by syntax
            print ("Please enter a query with the following syntax")
            print ("greater than 100 > returns all the transactions with a value greater than 100")
            print ("less than 100 before 15 > returns all the transactions with a value less than 100 that were made before day 15")
            print ("Keywords: greater, less, before, after")
            user_input = raw_input("Please enter your query:")
            user_input = user_input.lower()
            words = user_input.split()
            if len(words) == 3: #if the query has 3 words then it is of the form: 'greater than 100' or 'less than 100'
                try:
                    words[2] = int(words[2])
                except:
                    raw_input ("*Value must be a number*")
                if words[0] in ('greater', 'less') and words[1] == "than" and type(words[2]) == int:
                    return_transactions(transaction_list, words, ONLY_VALUE) #the query contains the value in it so we pass ONLY_VALUE to the function
                else:
                    raw_input ("*INVALID QUERY*")
            if len(words) == 5: #if the query has 5 words then it is of the form: 'greater than 100 before 15'
                try:
                    words[2] = int(words[2])
                    words[4] = int(words[4])
                except:
                    raw_input ("*Value and Day must be numbers*")
                if words[0] in ('greater', 'less') and words[1] == "than" and type(words[2]) == int and words[3] in ('before', 'after') and type(words[4]) == int:
                    return_transactions(transaction_list, words, VALUE_AND_DAY) #the query contains both the value and the day so we pass VALUE_AND_DAY to the function
                else:
                    raw_input ("*INVALID QUERY*")
            if len(words) not in (3, 5):
                raw_input ("*INVALID QUERY*")
        elif user_input == '4':
            result = False
            try:
                transaction_day = input("Please enter the day:")
                if check_day(transaction_day):
                    result = sold(transaction_list, transaction_day)
                else:
                    raw_input ("*Invalid day!*")
            except:
                raw_input ("*The day must be a number*")
            if result != False:
                print "The account sold in day", transaction_day, "is:", result
                raw_input()
        elif user_input == 'x':
            return menu_back
        else:
            raw_input (invalid)
    #End of submenu 3
    #-------Start of submenu 4
    elif subMenuNumber == 4:
        print submenu4, separator, back
        user_input = raw_input("Please enter your choice:")
        user_input = user_input.lower()
        if user_input == '1':
            transaction_type = raw_input("Please enter the type of the transaction (in/out):")
            transaction_type = transaction_type.lower()
            result = sum_of_transactions(transaction_list, transaction_type)
            if result == -1:
                raw_input ("*INVALID TYPE*")
            else:
                print "The result is:", result
                raw_input()
        elif user_input == '2':
            transaction_type = raw_input("Please enter the type of the transaction (in/out):")
            transaction_type = transaction_type.lower()
            result = max_of_transactions(transaction_list, transaction_type)
            if result == -1:
                raw_input ("*INVALID TYPE*")
            else:
                print "The result is:", result
                raw_input()
        elif user_input == 'x':
            return menu_back
        else:
            raw_input (invalid)
    #End of submenu 4
    #-------Start of submenu 5
    elif subMenuNumber == 5:
        print submenu5, separator, back
        user_input = raw_input ("Please enter your choice:")
        if user_input == "1":
            user_input = raw_input("Please enter the type of transactions you want to keep (in/out):")
            transaction_type = user_input.lower()
            if check_parameter(transaction_type, "in/out"):
                if transaction_type == "in":
                    transaction_type = "out"
                else:
                    transaction_type = "in"
                undo_list.append(list(transaction_list))
                remove_transaction(transaction_list, transaction_type)
                raw_input ("*Transactions filtered successfully!*")
            else:
                raw_input ("*Transaction type must be in or out!*")
        elif user_input == 'x':
            return menu_back
        else:
            raw_input (invalid)
    #End if submenu 5
def mainMenu():
    print menu, separator, quit_app
    user_input = raw_input ("Please enter your choice:")
    user_input = user_input.lower()
    if user_input == '1':
        while subMenu(1) != menu_back:
            pass
    elif user_input == '2':
        while subMenu(2) != menu_back:
            pass
    elif user_input == '3':
        while subMenu(3) != menu_back:
            pass
    elif user_input == '4':
        while subMenu(4) != menu_back:
            pass
    elif user_input == '5':
        while subMenu(5) != menu_back:
            pass
    elif user_input == '6':
        if undo_list != []:
            global transaction_list
            transaction_list = undo_list.pop()
            raw_input ("*Operation undone successfully!*")
        else:
            raw_input ("*Nothing to undo!*")
    elif user_input == 'x':
        return exit_app
    else:
        raw_input (invalid)

def run():
    while mainMenu() != exit_app:
        pass