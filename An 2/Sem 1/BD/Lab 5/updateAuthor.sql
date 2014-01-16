create procedure updateAuthor(@authorName varchar(200), @newAuthorName varchar(200))

as
begin

if dbo.validateName(@authorName) = 0 AND dbo.validateName(@newAuthorName) = 0
	update Authors set
		Authors.name = @newAuthorName 
	where Authors.name = @authorName
else
	print 'Invalid name'


end
