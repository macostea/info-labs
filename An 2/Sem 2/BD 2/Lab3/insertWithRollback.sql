create procedure insertTagArticle(@tagName varchar(200), @tagOptions varchar(200), @articleSubject varchar(200), @articleText varchar(200), @articleAuthorName varchar(200))

as
begin

begin tran
SET XACT_ABORT ON

DECLARE @AUTHORID int
SET @AUTHORID = -1

SELECT @AUTHORID = Authors.id from Authors where Authors.name = @articleAuthorName

if @AUTHORID = -1
	ROLLBACK TRAN
	else
	begin
		DECLARE @ARTICLEID int
		SET @ARTICLEID = -1
		insert into Articles(subject, text, author, date) values(@articleSubject, @articleText, @AUTHORID, GETDATE())
		SELECT @ARTICLEID = Articles.id from Articles where Articles.subject = @articleSubject and Articles.text = @articleText and Articles.author = @AUTHORID
		
		if @ARTICLEID = -1
			ROLLBACK TRAN
			ELSE
			BEGIN
				insert into Tags values(@tagName, @tagOptions)
				insert into TagsArticles values(@tagName, @ARTICLEID)

				COMMIT TRAN
			END
	end

end