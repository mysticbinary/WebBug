<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/6/22
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        ol{
            counter-reset: li; /* 创建一个计数器 */
            list-style: none; /* 清除列表默认的编码*/
            *list-style: decimal; /* 让IE6/7具有默认的编码 */
            font: 15px 'trebuchet MS', 'lucida sans';
            padding: 0;
            margin-bottom: 4em;
            text-shadow: 0 1px 0 rgba(255,255,255,.5);
        }
        ol ol{
            margin: 0 0 0 2em; /* 给二级列表增加一定的左边距*/
        }
    </style>
</head>
<body>

<ol>
    <li><a href="/PersonalcenterServlet" target="MyRight">个人中心</a></li>
    <li><a href="/UserManageServlet" target="MyRight">用户管理(后台功能)1</a></li>
    <li><a href="/UserManageServlet2" target="MyRight">用户管理(后台功能)2</a></li>
    <li><a href="/InQueryServlet" target="MyRight">查询用户1</a></li>
    <li><a href="/InQueryServlet2" target="MyRight">查询用户2</a></li>
    <li><a href="/WriterMessagesServlet" target="MyRight">写留言</a></li>
    <li><a href="/ShowMessagesServlet" target="MyRight">留言展示1</a></li>
    <li><a href="/ShowMessagesServlet2" target="MyRight">留言展示2</a></li>
    <li><a href="/jsp/upload/uploadimage.jsp" target="MyRight">文件上传(到目录)</a></li>
    <li><a href="/index_right.jsp" target="MyRight">关于该网站</a></li>
</ol>

</body>
</html>
