create procedure removeTag(@name varchar(200))

as
begin

delete from Tags where Tags.name like @name

end