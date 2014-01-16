create procedure [_ins*1*20*_sel*1*0*]
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

insert into TestRuns values('_ins*1*20*_sel*1*0*', NULL, NULL)
select @testRunId = max(TestRuns.TestRunID) from TestRuns

set @globalBefore = SYSDATETIME()

select @tableId = Tables.TableID from Tables where Tables.Name = 'Articles'

set @before = SYSDATETIME()

while @it<20
begin
insert into Articles values (@it+100, cast(@it as Varchar(200)), cast(@it as Varchar(200)), 0, '2013-12-12', 3)
Set @it = @it + 1
end

set @after = SYSDATETIME()

insert into TestRunTables values(@testRunId, @tableId, @before, @after)

set @before = SYSDATETIME()

select * from Articles_View

set @after = SYSDATETIME()

declare @viewId int
select @viewId = Views.ViewID from Views where Views.Name = 'Articles_View'
insert into TestRunViews values(@testRunId, @viewId, @before, @after)


set @globalAfter = SYSDATETIME()

update TestRuns set
	StartAt = @globalBefore,
	EndAt = @globalAfter
	where TestRunID = @testRunId

end