create procedure addTag(@name varchar(200), @options varchar(200))

as
begin

insert into Tags values(@name, @options)

end