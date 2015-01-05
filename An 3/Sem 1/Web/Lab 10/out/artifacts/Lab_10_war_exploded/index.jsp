<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  Model.User: mihaicostea
  Date: 02/01/15
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <h1>Welcome</h1>
  <h2>Login</h2>
  <form method="POST" action="/Login.do">
    Name: <input type="text" name="name"/> <br/>
    <input type="Submit" value="Login">
  </form>

  <h2>Register</h2>
  <form method="POST" action="/Register.do" enctype="multipart/form-data">
      Name: <input type="text" name="name" value=""> <br>
      Email: <input type="text" name="email" value=""> <br>
      PictureURL: <input type="file" name="picture" value=""> <br>
      Age: <input type="text" name="age" value=""> <br>
      Town: <input type="text" name="town" value=""> <br>
      <input type="submit" value="Register">
  </form>
  </body>
</html>
