<%@ page import="Model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: mihaicostea
  Date: 02/01/15
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
%>
    <form action="/EditProfile.do" method="POST">
        Id: <input type="text" value="<%out.print(user.getId());%>" disabled> <br>
        <input type="hidden" name="id" value="<%out.print(user.getId());%>">
        Name: <input type="text" name="name" value="<%out.print(user.getName());%>"> <br>
        Email: <input type="text" name="email" value="<%out.print(user.getEmail());%>"> <br>
        PictureURL: <input type="text" name="pictureURL" value="<%out.print(user.getPictureURL());%>"> <br>
        Age: <input type="text" name="age" value="<%out.print(user.getAge());%>"> <br>
        Town: <input type="text" name="town" value="<%out.print(user.getTown());%>"> <br>
        <input type="submit" value="Update">
    </form>
</body>
</html>
