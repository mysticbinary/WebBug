<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/6/23
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script type="text/javascript">
        function changeImage() {
            //单击触发图片重载事件，完成图片验证码的更换
            document.getElementById("imgRandom").src="/CheckPictureCodeServlet?t=" + Math.random();
        }
        function submit_bt() {
            // 每次提交之后就刷新当前页面。 如果前端修改JS代码不加载这个JS 会绕过吗？
            document.location.reload();//当前页面
        }
    </script>
</head>
<body>
<h2>Login prevent violence</h2>
<form name="reg" action="/BypassLoginCheckServlet" target="MyTop" method="post">
    <table border="1" cellspacing="0">
        <tr>
            <td>用户名:</td>
            <td><input name="username" type="text"/></td>
        </tr>
        <tr>
            <td>密&nbsp &nbsp码:</td>
            <td><input name="password" type="password"/></td>
        </tr>
        <tr>
            <td><img src="/CheckPictureCodeServlet" id="imgRandom" onclick="changeImage()"></td>
            <td><input name="picturecode" type="text"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="登录" id="bt" onclick="submit_bt()"></td>
        </tr>
    </table>
</form>
</body>
</html>
