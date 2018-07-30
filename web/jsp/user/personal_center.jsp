<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/7/6
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.User" %>
<%@ page import="entity.UserCombination" %>
<html>
<head>
    <title>personal center</title>
</head>

<body>
<h2>Personal Center</h2>
<table border=1 cellspacing="0">

    <%
        UserCombination user = new UserCombination();
        user = (UserCombination) session.getAttribute("currentuser_data");
    %>
    <tr>
        <th>头像</th>
        <th><center><img src=<%=user.getImagepath()%> width="50" height="50" border="2"/></center></th>
    </tr>
    <tr>
        <td>name</td>
        <td><%= user.getUsername() %></td>
    </tr>
    <tr>
        <td>mobilephone</td>
        <td><%= user.getMobilephone() %></td>
    </tr>
    <tr>
        <td>email</td>
        <td><%= user.getEmail() %></td>
    </tr>
    <tr>
        <td>city <br/> (0-北京，1-天津)</td>
        <td><%= user.getCity() %></td>
    </tr>
    <tr>
        <td>delivery adderss</td>
        <td><%= user.getDelivery_address() %></td>
    </tr>
    <tr>
        <td>user rights <br/>(0-普通用户，1-管理员，2-超级管理员)</td>
        <td><%= user.getUser_rights() %></td>
    </tr>
    <tr>
        <td>register time</td>
        <td><%= user.getRegister_time() %></td>
    </tr>
</table>
</body>
</html>
