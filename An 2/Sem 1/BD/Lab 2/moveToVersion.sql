Create PROCEDURE moveToVersion(
	@version int
)
AS
BEGIN
DECLARE @Sql NCHAR(1000)
DECLARE @currentVersion INT
DECLARE @reverseProcedure varchar(1000)
DECLARE @forwardProcedure varchar(1000)
DECLARE @itVersion INT

SELECT @itVersion = -1

SELECT @Sql = 'SELECT @currVer = versionNumber FROM version WHERE currentVersion = 1'

EXECUTE sp_executesql @Sql, N'@currVer int output', @currVer = @currentVersion OUTPUT

if @currentVersion > @version
BEGIN
	WHILE @currentVersion != @version
	BEGIN
	SELECT @Sql = 'UPDATE version SET currentVersion = 0 WHERE currentVersion = 1'
	EXEC (@Sql)
	SELECT @Sql = 'SELECT @revProcName = reverseStoredProcedureName, @itVer = versionNumber FROM version WHERE versionNumber <= ' + CAST(@currentVersion as VARCHAR) + 'ORDER BY versionNumber'
	EXECUTE sp_executesql @Sql, N'@revProcName varchar(1000) output, @itVer int output', @revProcName = @reverseProcedure OUTPUT, @itVer = @itVersion OUTPUT
	SELECT @currentVersion = @itVersion - 1
	EXECUTE @reverseProcedure
	SELECT @Sql = 'UPDATE version SET currentVersion = 1 WHERE versionNumber = ' + CAST(@currentVersion as VARCHAR)
	EXEC (@Sql)
	END
END
else
BEGIN
	WHILE @itVersion != @version
	BEGIN
	SELECT @Sql = 'UPDATE version SET currentVersion = 0 WHERE currentVersion = 1'
	EXEC (@Sql)
	SELECT @Sql = 'SELECT @procName = storedProcedureName, @itVer = versionNumber FROM version WHERE versionNumber > ' + CAST(@currentVersion as VARCHAR) + 'ORDER BY versionNumber DESC'
	EXECUTE sp_executesql @Sql, N'@procName varchar(1000) output, @itVer int output', @procName = @forwardProcedure OUTPUT, @itVer = @itVersion OUTPUT
	SELECT @currentVersion = @itVersion
	EXECUTE @forwardProcedure
	
	SELECT @Sql = 'UPDATE version SET currentVersion = 1 WHERE versionNumber = ' + CAST(@currentVersion as VARCHAR)
	EXEC (@Sql)
	END
END


END
