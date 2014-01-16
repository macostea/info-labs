use [Blogging Platform]
declare @articleSubject varchar(200), @articleId int

declare testcursor CURSOR
for select Articles.subject, Articles.id from Articles

open testcursor

fetch next from testcursor into @articleSubject, @articleId

while @@FETCH_STATUS = 0
begin
	print @articleSubject + ' ' + cast(@articleId as varchar(2))
	fetch next from testcursor into @articleSubject, @articleId
end

close testcursor
deallocate testcursor

