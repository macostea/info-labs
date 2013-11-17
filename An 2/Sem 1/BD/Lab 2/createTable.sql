Create PROCEDURE createTable
AS
BEGIN
DECLARE @Sql VARCHAR(1000)
SELECT @Sql = 'CREATE TABLE newTable(id int, firstName VARCHAR(100), lastName VARCHAR(100))'

EXEC (@Sql)
END