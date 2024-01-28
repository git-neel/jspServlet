<%--
  Created by IntelliJ IDEA.
  User: luv
  Date: 28/01/24
  Time: 11:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Information</title>
</head>
<body>
<h2>Enter Employee Information</h2>
<form action="employee" method="post">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName"><br><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName"><br><br>

    <label for="age">Age:</label>
    <input type="number" id="age" name="age"><br><br>

    <label for="salary">Salary:</label>
    <input type="number" id="salary" name="salary"><br><br>

    <label for="designation">Designation:</label>
    <input type="text" id="designation" name="designation"><br><br>

    <label for="address">Address:</label>
    <textarea id="address" name="address"></textarea><br><br>

    <button type="submit">Submit</button>
</form>
</body>
</html>
