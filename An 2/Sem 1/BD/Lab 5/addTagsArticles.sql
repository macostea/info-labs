create procedure addTagsArticles(@tagName varchar(200), @articleSubject varchar(200))

as
begin

declare @articleId int
set @articleId = -1

select @articleId = Articles.id from Articles where Articles.subject like @articleSubject

if (@articleId = -1)
	print('Article not found')
else
begin
	select * from Tags where Tags.name like @tagName
	if (@@ROWCOUNT = 0)
		print('Tag not found')
	else
		insert into TagsArticles values(@tagName, @articleId)
end

end	