create procedure insertTagArticleWithRecovery(@tagName varchar(200), @tagOptions varchar(200), @articleSubject varchar(200), @articleText varchar(200), @articleAuthorName varchar(200))

as
begin

begin tran

DECLARE @AUTHORID int
SET @AUTHORID = -1

SELECT @AUTHORID = Authors.id from Authors where Authors.name = @articleAuthorName
PRINT 'AUTHORID: '
PRINT @AUTHORID
if @AUTHORID != -1
	begin
		DECLARE @ARTICLEID int
		SET @ARTICLEID = -1

		save transaction beforeInsertArticles

		begin try
			insert into Articles(subject, text, author, date) values(@articleSubject, @articleText, @AUTHORID, GETDATE())
			SELECT @ARTICLEID = Articles.id from Articles where Articles.subject = @articleSubject and Articles.text = @articleText and Articles.author = @AUTHORID
			PRINT 'ARTICLEID: '
			PRINT @ARTICLEID
			save transaction beforeInsertTag
			begin try
				insert into Tags values(@tagName, @tagOptions)
				PRINT 'INSERTED TAG'
				save transaction beforInsertTagsArticles
				begin try
					PRINT 'INSERTED TAGARTICLE'
					insert into TagsArticles values(@tagName, @ARTICLEID)
					COMMIT TRAN
				end try
				begin catch
					rollback transaction beforeInsertTagsArticles
					commit tran
				end catch
			end try
			begin catch
				rollback transaction beforeInsertTag
				commit tran
			end catch
		end try
		begin catch
			rollback transaction beforeInsertArticles
			commit tran
		end catch
	end
end