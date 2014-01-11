create trigger myTrigger
	on Articles
	for INSERT

as
begin
	declare @ArticleSubject varchar(200)

	select @ArticleSubject = subject from inserted

	print 'triggered insert for article subject ' + @ArticleSubject


end


insert into Articles values(9, 'asfa', 'asgasga', 2, '2013-12-12', 2)