create procedure addAttachment(@url varchar(200), @uploader varchar(200))

as
begin

declare @uploaderId int
set @uploaderId = -1

select @uploaderId = Authors.id from Authors where Authors.name like @uploader
if @uploaderId = -1
	print 'Invalid uploader'
else
	if dbo.validateURL(@url) = 0
		print 'Invalid url'
	else
		insert into Attachment values (@url, @uploaderId)

end