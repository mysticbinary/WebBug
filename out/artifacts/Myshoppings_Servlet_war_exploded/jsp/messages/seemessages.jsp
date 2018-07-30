<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/6/27
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.Message" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
    <title>showmessages</title>
</head>
<body>
<h2>Show Messages</h2>
<table border=1 cellspacing="0">
    <tr>
        <th>留言人姓名</th>
        <th>留言时间</th>
        <th>留言标题</th>
        <th>留言内容</th>
    </tr>
    <%
        ArrayList<Message> all = new ArrayList();
        all = (ArrayList) session.getAttribute("all_messages");
        if (all != null) {
            Iterator it = all.iterator();
            while (it.hasNext()) {
                Message ms = (Message) it.next();
    %>
    <tr>
        <td><%= ms.getUsername() %>
        </td>
        <td><%= ms.getTime().toString() %>
        </td>
        <td><%= ms.getTitle() %>
        </td>
        <td><%= ms.getMessage() %>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
