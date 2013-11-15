from datetime import datetime
from types import ListType, StringType, IntType

ONLY_VALUE = 1
VALUE_AND_DAY = 2

undo_list = list()

#----------Functions			

def check_day(day):
	"""
	Checks if the passed day is valid
	day is int
	Returns True if valid, False otherwise
	"""
	if day in range(1,32):
		return True
	return False

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

def print_transaction(transaction):
	"""
	Prints a transaction neatly formatted
	transaction is a dictionary of the form {'day':x (int), 'value':x (int), 'type':x (str), 'description':x (str)}
	"""
	print "Day:", transaction['day'], "| Value:", transaction['value'], "| Type:", transaction['type'], "| Description:", transaction['description']
	print "-----------------------------------------------------------"

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
	
#----------Test Functions				

def test_add_transaction():
	transaction_list = []
	assert add_transaction(transaction_list, 25, 25, 'in', 'desc') == 0
	assert add_transaction(0, 25, 25, 'in', 'desc') == 1
	assert add_transaction(transaction_list, '25', 25, 'in', 'desc') == 1
	assert add_transaction(transaction_list, 25, '25', 'in', 'desc') == 1
	assert add_transaction(transaction_list, 25, 25, 0, 'desc') == 1
	assert add_transaction(transaction_list, 25, 25, 'in', 0) == 1
	
def test_replace_transaction():
	transaction_list = [{'type': 'in', 'day': 12, 'value': 100, 'description': 'desc'}, {'type': 'out', 'day': 12, 'value': 20, 'description': 'newdesc'}, {'type': 'in', 'day': 20, 'value': 40, 'description': 'newerdesc'}]
	assert replace_transaction(transaction_list, 12, 'in', 'desc', 40) == 0
	assert replace_transaction(0,12,'in', 'desc', 40) == 1
	assert replace_transaction(transaction_list, 40, 'in', 'desc', 20) == 2
	
def test_remove_transaction():
	transaction_list = [{'type': 'in', 'day': 12, 'value': 100, 'description': 'desc'}, {'type': 'out', 'day': 12, 'value': 20, 'description': 'newdesc'}, {'type': 'in', 'day': 20, 'value': 140, 'description': 'newerdesc'}]
	remove_transaction(transaction_list, 12)
	assert transaction_list == [{'type': 'in', 'day': 20, 'value': 140, 'description': 'newerdesc'}]
	transaction_list = [{'type': 'in', 'day': 12, 'value': 100, 'description': 'desc'}, {'type': 'out', 'day': 12, 'value': 20, 'description': 'newdesc'}, {'type': 'in', 'day': 20, 'value': 140, 'description': 'newerdesc'}]
	remove_transaction(transaction_list, 'out')
	assert transaction_list == [{'type': 'in', 'day': 12, 'value': 100, 'description': 'desc'}, {'type': 'in', 'day': 20, 'value': 140, 'description': 'newerdesc'}]
	transaction_list = [{'type': 'in', 'day': 12, 'value': 100, 'description': 'desc'}, {'type': 'out', 'day': 12, 'value': 20, 'description': 'newdesc'}, {'type': 'in', 'day': 20, 'value': 140, 'description': 'newerdesc'}]
	remove_transaction(transaction_list, [12, 20])
	assert transaction_list == []

def test_sum_of_transactions():
	transaction_list = [{'type': 'in', 'day': 12, 'value': 100, 'description': 'desc'}, {'type': 'out', 'day': 12, 'value': 20, 'description': 'newdesc'}, {'type': 'in', 'day': 20, 'value': 140, 'description': 'newerdesc'}]
	assert sum_of_transactions(transaction_list, "in") == 240
	assert sum_of_transactions(transaction_list, "out") == 20
	assert sum_of_transactions(transaction_list, 1) == -1
	
def test_sold():
	transaction_list = [{'type': 'in', 'day': 12, 'value': 100, 'description': 'desc'}, {'type': 'out', 'day': 12, 'value': 20, 'description': 'newdesc'}, {'type': 'in', 'day': 20, 'value': 140, 'description': 'newerdesc'}]
	assert sold(transaction_list, 12) == 80
	assert sold(transaction_list, 28) == 220
	assert sold(transaction_list, "day 12") == False


	
	
#----------Main Function

def main(): 
	test_add_transaction()
	test_replace_transaction()
	test_remove_transaction()
	test_sum_of_transactions()
	test_sold()
	#transaction_list = [] # declaration of the transaction list
	undo_list = []
	transaction_list = [{'type': 'in', 'day': 12, 'value': 100, 'description': 'desc'}, {'type': 'out', 'day': 12, 'value': 20, 'description': 'newdesc'}, {'type': 'in', 'day': 20, 'value': 140, 'description': 'newerdesc'}]

	now = datetime.now() # get current date and time
	user_input = ""
	
	print "Bank Account Management"
	choices = "(1) Add a new transaction\n(2) Modify transactions\n(3) View transactions\n(4) Calculate\n(5) Filter\n(6) Undo last operation"
	quit_app = "\n--------------------------\n(x) Exit the application"
	
	submenu1 = "(1) Add a transaction to the current day\n(2) Insert a transaction in another day"
	submenu2 = "(1) Remove transactions\n(2) Replace transactions"
	submenu3 = "(1) Show all the transactions\n(2) Show all transactions of a given type (in/out)\n(3) Show transactions with other properties\n(4) Show the account sold in a given day"
	submenu4 = "(1) Sum of all the transactions of the same type (in/out)\n(2) Return the day with the maximum value of transactions of a given type (in/out)"
	submenu5 = "(1) Keep only transactions which are of a given type (in/out)"
	
	back = "\n--------------------------\n(x) Back"
	invalid = "INVALID CHOICE"
	
	while user_input != 'x': # main application loop
		print choices, quit_app
		user_input = raw_input ("Please enter your choice:")
		user_input = user_input.lower()
		
		# Start of submenu 1
		if user_input == '1':
			user_input = ''
			while user_input != 'x':
				print submenu1,back
				user_input = raw_input ("Please enter your choice:")
				user_input = user_input.lower()
				
				if user_input == '1':
					current_day = now.day
					try:
						transaction_value = input("Please insert the value:")
						transaction_type = raw_input("Please insert the transaction type (in/out):")
						transaction_type = transaction_type.lower()
						transaction_description = raw_input("Please enter a description:")
						transaction_description = transaction_description.lower()
						undo_list.append(list(transaction_list))
						if add_transaction(transaction_list, transaction_value, current_day, transaction_type, transaction_description) == 0:
							print "*Transaction added successfully.*\n"
						else:
							undo_list.pop()
							print "*Transaction details are not of valid types. Please try again.*"
					except:
						print "*VALUE MUST BE A NUMBER*"
					
					
				elif user_input == '2':
					transaction_day = input("Please insert the day")
					if type(transaction_day) == int and check_day(int(transaction_day)):
						transaction_value = input("Please insert the value:")
						transaction_type = raw_input("Please insert the transaction type (in/out):")
						transaction_type = transaction_type.lower()
						transaction_description = raw_input("Please enter a description:")
						transaction_description = transaction_description.lower()
						undo_list.append(list(transaction_list))
						if add_transaction(transaction_list, transaction_value, transaction_day, transaction_type, transaction_description) == 0:
							print "*Transaction added successfully.*\n"
						else:
							undo_list.pop()
							print "*Transaction details are not of valid types. Please try again.*\n"
					else:
						print '*', transaction_day, "is not a valid day*"
				else:
					if user_input != 'x':
						print invalid
			else:
				user_input = ''
		# End of submenu 1
		
		# Start of submenu 2
		elif user_input == '2':
			user_input = ''
			while user_input != 'x':
					print submenu2, back
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
								print '*', transaction_day, 'is not a valid day*'
							else:
								undo_list.append(list(transaction_list))
								result = remove_transaction(transaction_list, transaction_day)
						except: #the user did not enter an int so we move on
							if user_input in ['in','out']: #the user entered a str (transaction type: 'in' or 'out')
								transaction_type = user_input
								undo_list.append(list(transaction_list))
								result = remove_transaction(transaction_list, transaction_type)
							else: #the user entered a str (range of days: 'from 2 to 10')
								words = user_input.split()
								if words[0] == 'from' and words[2] == 'to':
									if not check_day(int(words[1])) or not check_day(int(words[3])):
										print '*Invalid range*'
									else:
										transaction_range = [int(words[1]), int(words[3])]
										undo_list.append(list(transaction_list))
										result = remove_transaction(transaction_list, transaction_range)
								else:
									print '*Unrecognized query*'
						if result == 0:
							print '*Transactions removed successfully.*\n'
						elif result == 1:
							undo_list.pop()
							print '*Transaction details are not of valid types. Please try again.*\n'
						elif result == 2:
							undo_list.pop()
							print '*Requested transaction could not be found. Please try again.*\n'
						
					elif user_input == "2":
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
										print '*Transaction replaced successfully.*\n'
									elif result == 1:
										undo_list.pop()
										print '*Transaction details are not of valid types. Please try again.*\n'
									else:
										undo_list.pop()
										print '*Requested transaction could not be found. Please try again.*\n'
								except:
									print "*TRANSACTION VALUE MUST BE A NUMBER*"
							else:
								print '*', transaction_day, 'is not a valid day*'
						except:
							print "*TRANSACTION DAY MUST BE A NUMBER*"
					else:
						if user_input != "x":
							print invalid
			else:
				user_input = ""
		#End of submenu 2
		elif user_input == "3": #Submenu 3
			user_input = ""
			while user_input != "x":
				print submenu3, back
				user_input = raw_input("Please enter your choice:")
				user_input = user_input.lower()
				if user_input == "1":
					return_transactions(transaction_list, "all")
				elif user_input == "2":
					user_input = raw_input("Please enter the type (in/out):")
					user_input = user_input.lower()
					return_transactions(transaction_list, user_input)
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
							print "*Value must be a number*"
						if words[0] in ('greater', 'less') and words[1] == "than" and type(words[2]) == int:
							return_transactions(transaction_list, words, ONLY_VALUE) #the query contains the value in it so we pass ONLY_VALUE to the function
						else:
							print "*INVALID QUERY*"
					if len(words) == 5: #if the query has 5 words then it is of the form: 'greater than 100 before 15'
						try:
							words[2] = int(words[2])
							words[4] = int(words[4])
						except:
							print "*Value and Day must be numbers*"
						if words[0] in ('greater', 'less') and words[1] == "than" and type(words[2]) == int and words[3] in ('before', 'after') and type(words[4]) == int:
							return_transactions(transaction_list, words, VALUE_AND_DAY) #the query contains both the value and the day so we pass VALUE_AND_DAY to the function
						else:
							print "*INVALID QUERY*"
					if len(words) not in (3, 5):
						print "*INVALID QUERY*"
				elif user_input == '4':
					result = False
					try:
						transaction_day = input("Please enter the day:")
						if check_day(transaction_day):
							result = sold(transaction_list, transaction_day)
						else:
							print "*Invalid day!*"
					except:
						print "*The day must be a number*"
					if result != False:
						print "The account sold in day", transaction_day, "is:", result
				else:
					if user_input != "x":
						print invalid
			else:
				user_input = ""
		#End of submenu 3
		elif user_input == "4": #Start of submenu 4
			user_input = ""
			while user_input != "x":
				print submenu4, back
				user_input = raw_input("Please enter your choice:")
				user_input = user_input.lower()
				if user_input == "1":
					transaction_type = raw_input("Please enter the type of the transaction (in/out):")
					transaction_type = transaction_type.lower()
					result = sum_of_transactions(transaction_list, transaction_type)
					if result == -1:
						print "*INVALID TYPE*"
					else:
						print "The result is:", result
				elif user_input == "2":
					transaction_type = raw_input("Please enter the type of the transaction (in/out):")
					transaction_type = transaction_type.lower()
					result = max_of_transactions(transaction_list, transaction_type)
					if result == -1:
						print "*INVALID TYPE*"
					else:
						print "The result is:", result
				else:
					if user_input != "x":
						print invalid
					
			else:
				user_input = ""
		#End of submenu 4
		
		elif user_input == "5": #Start of submenu 5
			while user_input != "x":
				print submenu5, back
				user_input = raw_input ("Please enter your choice:")
				if user_input == "1":
					user_input = raw_input("Please enter the type of transactions you want to keep (in/out):")
					transaction_type = user_input.lower()
					if check_parameter(transaction_type, "in/out"):
						if transaction_type == "in":
							transaction_type = "out"
						else:
							transaction_type = "in"
						remove_transaction(transaction_list, transaction_type)
						print "*Transactions filtered successfully!*"
					else:
						print "*Transaction type must be in or out!*"
				else:
					if user_input != "x":
						print invalid
			else:
				user_input = ""
		#End of submenu 5
		elif user_input == '6': #Menu entry 6
			if undo_list != []:
				transaction_list = undo_list.pop()
				print "*Last operation undone successfully!*"
			else:
				print "*Nothing to undo!*"
		#End if menu entry 6
		else:
			if user_input != "x":
				print invalid
	#End of main loop
	
	
main()
