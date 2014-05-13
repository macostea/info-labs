begin tran

update Articles set subject='newsubj' where attachment=3
waitfor delay '00:00:05.000'
update Authors set name='Zoltan' where name='Andrei'
rollback tran