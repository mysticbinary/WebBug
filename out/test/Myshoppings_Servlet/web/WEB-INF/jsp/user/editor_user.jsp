<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/7/9
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.UserCombination" %>
<html>
<head>
    <title>editor user</title>

    <script type="text/javascript">
        // 解决获取不到真实路径问题
        function getObjectURL(file) {
            var url = null;
            if (window.createObjcectURL != undefined) {
                url = window.createOjcectURL(file);
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file);
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file);
            }
            return url;
        }

        //使用js实现文件上传图片的预览
        function showPreview(obj) {
            var str = getObjectURL(obj.files[0]);
            //alert(str);
            document.getElementById("previewImg").innerHTML =
                "<img width='50' height='50' border='1' src = '" + str + "'/>";
        }
    </script>
</head>
<body>
<h2>Editor user</h2>


<form name="reg" action="/Editor2UserServlet" target="MyRight" enctype="multipart/form-data" method="post">
    <table border="1" cellspacing="0">
        <%
            UserCombination user = new UserCombination();
            user = (UserCombination) session.getAttribute("uersinform");
        %>

        <tr>
            <td>
                <center><img src=<%=user.getImagepath()%> width="50" height="50" border="2"/></center>
                user image
            </td>
            <td>
                <table border="1" cellspacing="0">
                    <tr>
                        <td><input id="myfile" name="myfile" type="file" onchange="showPreview(this)"/></td>
                    </tr>
                    <tr>
                        <td>
                            <div id="previewImg"></div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>

        <input name="id" type="hidden" value=<%=user.getId()%> />

        <tr>
            <td>user name:</td>
            <td><input name="username" value=<%=user.getUsername()%> type="text"/></td>
        </tr>

        <tr>
            <td>mobilephone:</td>
            <td><input name="mobilephone" value=<%=user.getMobilephone()%> type="text"/></td>
        </tr>

        <tr>
            <td>eamil:</td>
            <td><input name="email" value=<%=user.getEmail()%> type="text"/></td>
        </tr>

        <tr>
            <td>select city:</td>
            <td>
                <select id="city">
                    <option value="-1">--请选择城市--</option>
                    <option value="0">北京</option>
                    <option value="1">天津</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>You shipping address:</td>
            <td><input name="delivery_address" value=<%=user.getDelivery_address()%> type="text"/></td>
        </tr>

        <tr>
            <td><input type="reset" value="reset" /></td>
            <td><input type="submit" value="提交修改" id="bt"></td>
        </tr>

    </table>
</form>

</body>
</html>
