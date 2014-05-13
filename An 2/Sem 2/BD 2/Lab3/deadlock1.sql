begin tran

update Authors set name='Zoltan' where name='Andrei'
waitfor delay '00:00:05.000'
update Articles set subject='newsubj' where attachment=3
rollback tran