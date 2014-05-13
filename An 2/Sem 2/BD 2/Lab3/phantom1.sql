set transaction isolation level repeatable read
begin tran

select * from Authors
waitfor delay '00:00:05.000'
select * from Authors
rollback