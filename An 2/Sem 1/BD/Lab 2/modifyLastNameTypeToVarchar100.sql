Create PROCEDURE modifyLastNameTypeToVarchar100
AS
BEGIN
DECLARE @Sql VARCHAR(1000)

SELECT @Sql = 'ALTER TABLE newTable ALTER COLUMN lastName VARCHAR(100)'

EXEC (@Sql)

END