set transaction isolation level read committed
begin transaction
select top 5 * from Authors order by name
waitfor delay '00:00:05.000'
select top 5 * from Authors order by name
commit transaction
