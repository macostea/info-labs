set transaction isolation level read committed

begin transaction
update Authors set name='Andrei' where name='Zoltan'
commit transaction