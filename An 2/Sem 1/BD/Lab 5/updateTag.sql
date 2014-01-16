create procedure updateTag(@name varchar(200), @newName varchar(200), @newOption varchar(200))

as
begin

update Tags set
	name = @newName,
	options = @newOption
where Tags.name like @name

end