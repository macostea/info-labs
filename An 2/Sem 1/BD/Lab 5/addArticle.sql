create procedure addArticle(@subject varchar(200), @text varchar(MAX), @author varchar(200), @attachmentURL varchar(200))

as
begin

declare @authorId int
declare @attachmentId int
set @attachmentId = -1
set @authorId = -1
select @authorId = Authors.id from Authors where Authors.name like @author

if @authorId = -1
	begin
	exec dbo.addAuthor
		@authorName = @author
	select @authorId = Authors.id from Authors where Authors.name like @author
	end

if @attachmentURL != NULL
	exec dbo.addAttachment
		@url = @attachmentURL,
		@uploader = @author

select @attachmentId = Attachment.id from Attachment where Attachment.url like @attachmentURL

if (@authorId != -1) and (@attachmentId != -1)
	insert into Articles values(@subject, @text, @authorId, SYSDATETIME(), @attachmentId)
else
	print('Author or attachment invalid and cannot be added')

end