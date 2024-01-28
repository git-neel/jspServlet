<%--
  Created by IntelliJ IDEA.
  User: luv
  Date: 28/01/24
  Time: 9:19 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather App</title>
</head>
<body>
<h2>Enter city name to get Weather details</h2>
<form action="second-servlet" method="get">
    <input name="cityName"/>
    <button>Submit</button>
    <input type="hidden" name="respBody" value="${respBody}">
    <input type="hidden" name="respStatus" value="${respStatus}">
</form>
<%
    String respBody = (String) request.getAttribute("respBody");
    String respBodyBySession = (String) session.getAttribute("respBodyBySession");
%>
<div>
    <h3>Response Body from Request Attribute:</h3>
    <textarea><%= respBody != null ? respBody : "No response body available" %></textarea>
</div>
<div>
    <h3>Response Body from Session Attribute:</h3>
    <textarea><%= respBodyBySession != null ? respBodyBySession : "No response body available" %></textarea>
</div>
<div>
    <h3>Response Body from Request Parameter:</h3>
    <textarea>${param.respBody != null ? param.respBody : "No response body available"}</textarea>
</div>
<div>
    <h3>Response status from Request Parameter:</h3>
    <textarea>${param.respStatus != null ? param.respStatus : "No status body available"}</textarea>
</div>
</body>
</html>
