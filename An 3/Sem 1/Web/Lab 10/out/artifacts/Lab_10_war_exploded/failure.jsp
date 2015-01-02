<%--
  Created by IntelliJ IDEA.
  User: mihaicostea
  Date: 02/01/15
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>The operation failed</h1>
    <strong>Reason:</strong>
    <p><% out.println(request.getAttribute("message")); %></p>
</body>
</html>
