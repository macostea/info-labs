select * from sys.indexes

select * from sys.dm_db_index_usage_stats
	join sys.indexes on dm_db_index_usage_stats.index_id = sys.indexes.index_id and
						dm_db_index_usage_stats.object_id = sys.indexes.object_id


exec dbo.addTagsArticles
	@tagName = 'bbb',
	@articleSubject = 's57'

declare @it int
set @it = 0

while @it < 50000
	begin
	insert into Articles values('s'+CAST(@it+53 as varchar(200)), 'aaaa', 1, SYSDATETIME(), NULL)
	set @it = @it + 1
	end
