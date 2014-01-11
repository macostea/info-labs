create procedure populateTestTables

as
begin
declare @testId int, @testName nvarchar(50)
declare testcursor CURSOR

for select Tests.TestID, Tests.Name from Tests
open testcursor

fetch next from testcursor into @testId, @testName

while @@FETCH_STATUS = 0
begin
	declare @_index int, @_nextIndex int
	select @_index = 1

	while @_index != 0
	begin
		declare @operation varchar(20)
		declare @position int
		set @position = 0
		select @_nextIndex = CHARINDEX('_', @testName, @_index + 1)

		if @_nextIndex != 0
			begin
			select @operation = SUBSTRING(@testName, @_index + 1, @_nextIndex - @_index - 1)
			end
		else
			begin
			select @operation = SUBSTRING(@testName, @_index + 1, LEN(@testName) - @_index)
			end
		select @_index = @_nextIndex

		declare @starIndex int, @nextStarIndex int
		declare @tableId int, @numberOfRows int, @lastTableId int
		declare @op varchar(3)

		select @starIndex = CHARINDEX('*', @operation)
		select @op = SUBSTRING(@operation, 1, @starIndex - 1)
		select @position = @position + 1
		select @nextStarIndex = CHARINDEX('*', @operation, @starIndex + 1)

		select @tableId = CONVERT(int, SUBSTRING(@operation, @starIndex + 1, @nextStarIndex - @starIndex - 1))

		select @starIndex = CHARINDEX('*', @operation, @nextStarIndex + 1)
		select @nextStarIndex = CHARINDEX('*', @operation, @starIndex + 1)

		select @numberOfRows = CONVERT(int, SUBSTRING(@operation, @starIndex + 1, @nextStarIndex - @starIndex - 1))

		if (@op = 'sel')
		begin
			insert into TestViews values(@testId, @tableId)
			select @lastTableId = @tableId
		end
		else
		begin
			if (@tableId = @lastTableId)
			begin
				update TestTables set NoOfRows = NoOfRows + @numberOfRows where TableID = @tableId
			end
			else
			begin
			insert into TestTables values (@testId, @tableId, @numberOfRows, @position)
			end
			select @lastTableId = @tableId
		end

	end



	fetch next from testcursor into @testId, @testName
end

close testcursor
deallocate testcursor

end