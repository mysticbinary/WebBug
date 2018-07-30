<%--
  Created by IntelliJ IDEA.
  User: Jonas
  Date: 2018/6/26
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <script type="text/javascript">
        var xmlHttp;

        // 触发的条件，可以是任意事件
        function init() {
            //要给服务器发送用户输入的内容，因为我们采用的是ajax异步发送数据
            //所以我们要使用一个对象，叫做XmlHttp对象
            xmlHttp = createXMLHttp();
            var url = "/CityAjaxServlet"; //请求的Servlet
            //true 表示js脚本会在send()方法之后继续执行，而不会等待来自服务器的响应
            xmlHttp.open("GET", url, true);
            //xmlHttp的状态0-4，我们只关心4(complete)这个状态，因为当数据传输的过程完成之后，在调用回调方法才有意义
            xmlHttp.onreadystatechange = callback;//无()传递的是函数对象
            xmlHttp.send(null);
        }

        //回调函数
        function callback() {
            //4代表完成
            if (xmlHttp.readyState == 4) {
                //200代表服务器响应成功，404代表资源未找到，500代表服务器内部错误
                if (xmlHttp.status == 200) {
                    //交互成功，获得相应的数据，是文本格式
                    var result = xmlHttp.responseText;
                    //解析获得数据
                    var json = eval("(" + result + ")");
                    //获得数据之后，就可以动态的显示这些数据了，把这些数据展示到输入框下面
                    //alert(result);
                    //setContent(json);
                    //将json数据填充到 下拉选择框里
                    var _city = document.getElementById("city");
                    for (var i in json) {
                        var opt_i = new Option(json[i].name, json[i].value);
                        _city.add(opt_i);
                    }
                }
            }
        }

        //获得XmlHttp对象
        function createXMLHttp() {
            //对于大多数的浏览器都适用
            var xmlHttp;
            if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            }
            //要考虑浏览器的兼容性
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                if (!xmlHttp) {
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                }
            }
            return xmlHttp;
        }

        // 校验两次密码
        function checktwopassword() {
            var password = document.getElementById("password").value;
            var repassword = document.getElementById("repassword").value;
            if (password != repassword) {
                window.alert("您输入的'密码'与'重输密码'不一致!");
                signupForm.repassword.focus();
                return false;
            }
            return true;
        }


    </script>
</head>
<body onload="init();">
<h2>Register</h2>
<form name="reg" action="/RegisterServlet" target="MyRight" method="post">
    <table border="1" cellspacing="0">
        <tr>
            <td>用户名:</td>
            <td><input name="username" type="text"/></td>
        </tr>
        <tr>
            <td>密&nbsp &nbsp码:</td>
            <td><input name="password" id="password" type="password"/></td>
        </tr>
        <tr>
            <td>重输密码:</td>
            <td><input name="repassword" id="repassword" type="password"/></td>
        </tr>
        <tr>
            <td>手机:</td>
            <td><input name="mobilephone" type="text"/></td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><input name="email" type="text"/></td>
        </tr>
        <tr>
            <td>选择城市:</td>
            <td>
                <select id="city" onchange="toProvince();">
                    <option value="-1">--请选择城市--</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>填写收货地址:</td>
            <td><input name="delivery_address" type="text"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="提交注册" id="bt" onclick="return checktwopassword()"></td>
        </tr>
    </table>
</form>
</body>
</html>
