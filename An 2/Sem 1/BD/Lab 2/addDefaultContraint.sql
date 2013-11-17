Create PROCEDURE addDefaultConstraint
AS
BEGIN
DECLARE @Sql VARCHAR(1000)
SELECT @Sql = 'ALTER TABLE newTable ADD CONSTRAINT no_name_firstName DEFAULT ''no_name'' FOR firstName'
EXEC (@Sql)
END