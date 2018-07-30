<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/7/10
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>query user</title>
</head>
<body>
<h2>Query user</h2>

<form name='left' action='/ToQueryServlet' target='MyRight' method='get'>
    <input type='text' id='query' name="querystr"/>
    <input type='submit' value='查询用户名' id='bt1'>
</form>

</body>
</html>
