<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<xsl:variable name="key">*</xsl:variable>

<html>
	<body>
		<h2>Bibliography</h2>
		<table border="1">
			<xsl:for-each select="bibliography/entry">
				<xsl:sort select="author" />
				<xsl:choose>
					<xsl:when test="$key='*' or author=$key or year=$key or editor=$key or contains(title, $key)">
						<tr>
					      <td><xsl:value-of select="title"/></td>
			              <td><xsl:value-of select="author"/></td>
		  	              <td><xsl:value-of select="year"/></td>
		                  <td><xsl:value-of select="editor"/></td>
						</tr>
					</xsl:when>
				</xsl:choose>
			</xsl:for-each>
		</table>
	</body>
</html>

</xsl:template>
</xsl:stylesheet>
