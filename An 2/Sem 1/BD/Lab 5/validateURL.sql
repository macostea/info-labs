create function validateURL(@url varchar(200))
returns bit

as
begin

declare @returnValue bit

if PATINDEX('%http://%', @url) = 1
	set @returnValue = 1
else
	set @returnValue = 0

return @returnValue

end