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
    <script language="javascript">
        function getData() {
            //window.location.href = "/CheckPictureCodeServlet";
            window.location.href = "/IndexServlet";
        }
    </script>
</head>

<body onload="getData()">
<%-- Q:怎么自动去访问 IndexServlet? --%>
<%-- A:onload="getData()"--%>
</body>
</html>
