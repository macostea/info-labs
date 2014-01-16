create procedure addAuthor(@authorName as varchar(200))

as
begin

if dbo.validateName(@authorName) = 0
	print('Invalid name')
else
	insert into Authors values(@authorName)

end