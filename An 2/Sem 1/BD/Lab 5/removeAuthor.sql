create procedure removeAuthor(@authorName varchar(200))

as
begin

delete from Authors where Authors.name like @authorName

end