create procedure updateArticle(@subject varchar(200), @newSubject varchar(200), @newText varchar(200), @newAuthor varchar(200), @newDate datetime2, @newAttachment varchar(200))

as
begin

declare @articleId int
set @articleId = -1
select @articleId = Articles.id from Articles where Articles.subject like @subject

declare @authorId int
set @authorId = -1
select @authorId = Authors.id from Authors where Authors.name like @newAuthor

declare @attachmentId int
set @attachmentId = -1
select @attachmentId = Attachment.id from Attachment where Attachment.url like @newAttachment

if @articleId = -1
	print('Article not found')
else
	update Articles set
		subject = @newSubject,
		text = @newText,
		author = @authorId,
		date = @newDate,
		attachment = @newAttachment
	where Articles.id = @articleId

end