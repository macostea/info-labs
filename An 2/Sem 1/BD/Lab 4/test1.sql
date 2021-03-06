USE [Blogging Platform]
GO
/****** Object:  StoredProcedure [dbo].[_ins*1*r*20*_ins*2*r*15*_del*2*r*10*]    Script Date: 06/01/2014 12:06:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[_ins*1*r*20*_ins*2*r*15*_del*2*r*10*]

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

insert into TestRuns values('_ins*1*r*20*_ins*2*r*15*_del*2*r*10*', NULL, NULL)
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

Set @it = 0
select @tableId = Tables.TableID from Tables where Tables.Name = 'Authors'

set @before = SYSDATETIME()

while @it<15
begin
insert into Authors values (@it+100, cast(@it as Varchar(200)))
Set @it = @it + 1
end

Set @it = 0

while @it<10
begin
delete from Authors where Authors.id = @it+100
Set @it = @it + 1
end

set @after = SYSDATETIME()

insert into TestRunTables values(@testRunId, @tableId, @before, @after)

set @globalAfter = SYSDATETIME()

update TestRuns set
	StartAt = @globalBefore,
	EndAt = @globalAfter
	where TestRunID = @testRunId

end