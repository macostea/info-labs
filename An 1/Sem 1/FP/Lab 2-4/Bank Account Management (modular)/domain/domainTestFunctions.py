'''
Created on Oct 28, 2012

@author: Mihai Costea

This module contains all the test functions for the domain package
They are called in the __init__.py
'''

#------Test functions
from domain.calculations import sum_of_transactions, sold
from domain.transactionManipulation import add_transaction, replace_transaction, \
    remove_transaction

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

