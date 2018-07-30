<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/7/20
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Iterator" %>
<%@ page import="entity.UserCombination" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.owasp.esapi.ESAPI" %>

<html>
<head>
    <title>Show Query User</title>
</head>

<body>
<%
    String query;
    query = (String) request.getAttribute("querystr");
    /*HTML编码使用的是ESAPI HTMLEntityCodec编码器
    编码原理：
    如果是空格、字母或者数字，就不进行编码；
    如果有特殊字符在HTML中匹配的替代品，就用替代方式，如 引号"  替换成  &quot;
    其他字符则使用 &#x+十六进制数值+;
     */
    String safety_query = ESAPI.encoder().encodeForHTML(query);
%>
<h2>您查询的用户名:<%=safety_query%>
</h2>
<table border=1 cellspacing="0">

    <tr>
        <th>user_id</th>
        <th>name</th>
        <th>user_image</th>
        <th>rights</th>
    </tr>
    <%--// 获取储存在模型中的行信息--%>
    <%--//判断是否有数据--%>
    <%
        ArrayList<UserCombination> alluser = new ArrayList();
        alluser = (ArrayList) session.getAttribute("usercombination");
        if (alluser != null) {
            Iterator it = alluser.iterator();
            while (it.hasNext()) {
                UserCombination uc = (UserCombination) it.next();
    %>
    <tr>
        <td><%=uc.getId()%>
        </td>
        <td><%=uc.getUsername()%>
        </td>
        <td>
            <center><img src="<%=uc.getImagepath()%>" ate="用户头像" width="50" height="50" border="2"/></center>
        </td>
        <td><%=uc.getUser_rights()%>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
