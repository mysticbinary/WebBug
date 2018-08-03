<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/6/27
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Token" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>writermessages2</title>
</head>
<body>
<h2>Writer Messages</h2>

<%
    Token token = (Token) session.getAttribute("token1");
%>

<form action="<%= path %>/Submit_Messages_tokenServlet" method="post">
    <table border=1 cellspacing="0">
        <tr>
            <td>message title</td>
            <td><input type="text" name="title"/></td>
        </tr>
        <tr>
            <td>message content</td>
            <td><textarea name="message" rows="5" cols="35"></textarea></td>
        </tr>

        <input type="hidden" name="csrf_token" value="<%= token.getToken() %>"/>

        <tr>
            <td><input type="reset" value="reset"/></td>
            <td><input type="submit" value="submit"/></td>
        </tr>
    </table>

</form>
</body>
</html>
