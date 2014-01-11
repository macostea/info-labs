create procedure [test2]

as
begin


declare @it int
Set @it = 0

declare @globalBefore datetime2
declare @globalAfter datetime2

declare @before datetime2
declare @after datetime2

declare @testRunId int
declare @tableId int

insert into TestRuns values('_ins*9*r*20*_ins*10*r*3*_sel*2*r*0*_sel*3*r*0', NULL, NULL)
select @testRunId = max(TestRuns.TestRunID) from TestRuns

set @globalBefore = SYSDATETIME()

select @tableId = Tables.TableID from Tables where Tables.Name = 'newTable'

set @before = SYSDATETIME()

while @it<20
begin
insert into newTable values (@it+100, 'newnew')
Set @it = @it + 1
end

set @after = SYSDATETIME()

insert into TestRunTables values(@testRunId, @tableId, @before, @after)

select @tableId = Tables.TableID from Tables where Tables.Name = 'TagsArticles'

set @before = SYSDATETIME()

insert into TagsArticles values('aaa',0)
insert into TagsArticles values('bbb',1)
insert into TagsArticles values('ccc',2)

set @after = SYSDATETIME()

insert into TestRunTables values(@testRunId, @tableId, @before, @after)

set @before = SYSDATETIME()

select * from moreTablesView

set @after = SYSDATETIME()

declare @viewId int
select @viewId = Views.ViewID from Views where Views.Name = 'moreTablesView'
insert into TestRunViews values(@testRunId, @viewId, @before, @after)

set @before = SYSDATETIME()

select * from groupByView

set @after = SYSDATETIME()

select @viewId = Views.ViewID from Views where Views.Name = 'groupByView'
insert into TestRunViews values(@testRunId, @viewId, @before, @after)


set @globalAfter = SYSDATETIME()

update TestRuns set
	StartAt = @globalBefore,
	EndAt = @globalAfter
	where TestRunID = @testRunId

end