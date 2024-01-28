<%--
  Created by IntelliJ IDEA.
  User: luv
  Date: 28/01/24
  Time: 10:27 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action ="third-servlet" method="get">
   <input name="city"/>
    <button>Submit</button>
</form>
<div>
    <h3>Response Body from Request Attribute:</h3>
    <textarea><%= request.getAttribute("responseBody") != null ? request.getAttribute("responseBody") .toString() : "No response body available" %></textarea>
</div>
</body>
</html>
