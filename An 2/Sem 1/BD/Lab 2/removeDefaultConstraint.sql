Create PROCEDURE removeDefaultConstraint
AS
BEGIN
DECLARE @Sql VARCHAR(1000)

SELECT @Sql = 'ALTER TABLE newTable DROP CONSTRAINT no_name_firstName'

EXEC (@Sql)
END