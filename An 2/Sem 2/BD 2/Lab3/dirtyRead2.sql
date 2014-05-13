begin tran
update Authors set name='Ion' where name='Andrei'
waitfor delay '00:00:05.00'
rollback transaction
select * from Authors where name='Andrei'