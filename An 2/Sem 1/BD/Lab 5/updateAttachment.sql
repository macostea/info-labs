create procedure updateAttachment(@url varchar(200), @newURL varchar(200), @newUploader varchar(200))

as
begin

declare @uploaderId int
set @uploaderId = -1

select @uploaderId = Authors.id from Authors where Authors.name like @newUploader
if @uploaderId = -1
	print 'Invalid uploader'
else
	if dbo.validateURL(@newURL) = 0
		print 'Invalid url'
	else
		update Attachment set
			url = @newURL,
			uploaderId = @uploaderId
		where Attachment.url like @url


end