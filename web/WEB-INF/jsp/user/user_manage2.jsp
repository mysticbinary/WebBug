<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/7/6
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Iterator" %>
<%@ page import="entity.UserCombination" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Part" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="sun.misc.BASE64Encoder" %>
<html>
<head>
    <title>user manage</title>
</head>
<body>
<h2>User Manage</h2>

<table border=1 cellspacing="0">

    <tr>
        <th>user_id</th>
        <th>name</th>
        <th>user_image</th>
        <th>rights</th>
        <th>operation</th>
    </tr>
    <%--// 获取储存在模型中的行信息--%>
    <%--//判断是否有数据--%>
    <%
        ArrayList<UserCombination> alluser = new ArrayList();
        alluser = (ArrayList) session.getAttribute("usercombination");

        UserCombination current_user = new UserCombination();
        current_user = (UserCombination) session.getAttribute("current_user");

        String grade_md5 = new BASE64Encoder().encode(MessageDigest.getInstance("MD5").digest(Integer.toString(current_user.getUser_rights()).getBytes("utf-8")));

        if (alluser != null) {
            Iterator it = alluser.iterator();
            while (it.hasNext()) {
                UserCombination uc = (UserCombination) it.next();
    %>
                <tr>
                    <td><%=uc.getId()%></td>
                    <td><%=uc.getUsername()%></td>
                    <td>
                        <center><img src="<%=uc.getImagepath()%>" ate="用户头像" width="50" height="50" border="2"/></center>
                    </td>
                    <td><%=uc.getUser_rights()%></td>
                    <td>
                        <%
                            int userid = uc.getId();

                            String editor_user_url = "<form action='/EditorUserServlet'  target='MyRight' method='get'>" +
                                    "<input type='hidden' name='userid' value="+userid+">" +
                                    "<input type='submit' value='修改用户信息'></form>";

                            String delete_user_url = "<form action='/DeleteUserServlet2'  target='MyRight' method='get'>" +
                                    "<input type='hidden' name='userid' value="+userid+">" +
                                    "<input type='hidden' name='grade' value=" + grade_md5 +">" +
                                    "<input type='submit' value='删除该用户'></form>";
                        %>
                        <%=editor_user_url%>
                        <%=delete_user_url%>
                    </td>
                </tr>
    <%
            }
        }
    %>
</table>

<%-- paper --%>
<br/>
<%
    String left1 = "<form name='left' action='/leftServlet2' target='MyRight' method='get'> "
            + "<input type='hidden' value='left1' id='left1_right1' name='left1_right1'>"
            + "<input type='submit' value='上一页' id='bt1'></form>";
    String right1 = "<form name='right' action='/leftServlet2' target='MyRight' method='get'> "
            + "<input type='hidden' value='right1' id='left1_right1' name='left1_right1'>"
            + "<input type='submit' value='下一页' id='bt1'></form>";
%>

<%
    Part part = new Part();
    part = (Part) session.getAttribute("part");
%>
<div style="display:inline-block">
    <%=left1%>
</div>

<div style="display:inline-block">
    <%=part.getCurrent_pape()%>
</div>
/
<div style="display:inline-block">
    <%=part.getAll_pape()%>
</div>

<div style="display:inline-block">
    <%=right1%>
</div>

<div style="display:inline-block">
    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 总共条数: <%=part.getAll_number()%>
</div>

</body>
</html>
