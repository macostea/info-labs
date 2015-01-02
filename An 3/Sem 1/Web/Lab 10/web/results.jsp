<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: mihaicostea
  Date: 02/01/15
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>Search</h1>
    <form method="POST" action="/GetUsers.do">
        <input type="text" name="term">
        <input type="submit" value="Search">
    </form>
    <h1>Matched users</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Picture</th>
            <th>Age</th>
            <th>Town</th>
        </tr>
    <%
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
        for (User user : users) {
            out.println("<tr>");
            out.println("<td>" + user.getName() + "</td>");
            out.println("<td>" + user.getEmail() + "</td>");
            out.println("<td><img src='" + user.getPictureURL() + "'></td>");
            out.println("<td>" + user.getAge() + "</td>");
            out.println("<td>" + user.getTown() + "</td>");
        }
    %>
    </table>

</body>
</html>
