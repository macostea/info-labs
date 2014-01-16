create function validateName(@name varchar(200))
returns bit

as
begin

declare @returnValue as bit

if PATINDEX('%[0-9]%', @name) > 0
	set @returnValue = 0
else
	set @returnValue = 1


	return @returnValue
end