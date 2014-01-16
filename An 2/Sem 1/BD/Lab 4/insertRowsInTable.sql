create procedure insertRowsInTable(@tableName varchar(200), @numberOfRows int)

as
begin
DECLARE @Sql NCHAR(1000)

declare @it int
Set @it = 0


while @it<@numberOfRows
begin
set @Sql = 'INSERT into ' + @tableName + ' values(' + cast(@it+100 as varchar) + ', ' + cast(@it as varchar) + ', ' + cast(@it as varchar) + ', 0, ''2013-12-12'', 3)'

print @sql

exec(@Sql)

Set @it = @it + 1
end

end