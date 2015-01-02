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
    <form method="GET" action="/GetUsers.do">
        <input type="submit" value="View users">
    </form>

    <form method="GET" action="/EditProfile.do">
        <input type="hidden" value="<%out.print(request.getAttribute("id"));%>" name="id">
        <input type="submit" value="Edit profile">
    </form>
</body>
</html>
