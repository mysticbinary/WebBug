<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/6/26
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload imape</title>
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
                "<img src = '" + str + "'/>";
        }
    </script>
</head>
<body>
<h2>Upload Image</h2>

<form action="/UploadImageServlet" method="post" enctype="multipart/form-data">
    <table border="1" cellspacing="0">
        <tr>
            <td>请选择图片:</td>
            <td><input id="myfile" name="myfile" type="file" onchange="showPreview(this)"/></td>
        </tr>
        <tr>
            <td>上传预览图片:</td>

            <td><div id="previewImg"></div></td>
        </tr>
            <td><input type="submit" value="开始上传"/></td>
            <td>${result}</td>
        </tr>
    </table>
</form>

</body>
</html>
