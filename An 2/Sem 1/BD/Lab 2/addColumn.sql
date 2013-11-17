Create PROCEDURE addColumn

AS
BEGIN
DECLARE @Sql VARCHAR(1000)

SELECT @Sql = 'ALTER TABLE newTable ADD newColumn int'

EXEC(@Sql)

END