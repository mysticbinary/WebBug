package servlet.login;


import dao.LoginDao;
import entity.User;
import util.GlobalVariables;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BypassLoginCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String clientCode = req.getParameter("picturecode");

        String picturecode = (String) req.getSession().getAttribute("check_code");

        PrintWriter writer = resp.getWriter();

        // 校验数据库
        // 验证码问题，新手比较容易犯的逻辑问题，就是不刷新
        // 即使前台刷新了验证码，但是后台没有强制刷新验证码，会出现 验证码不过期，可以暴力爆破的情况。
        // 只要跳转之后，验证码就会刷新
        if (new LoginDao().Logincheck(username, password) && clientCode.equals(picturecode)) {
            //实体类 填充数据
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            req.getSession().setAttribute("user", user);

            writer.write(
                    "恭喜！您登录成功 2秒后跳转。"
            );
            //等待2秒 刷新
            resp.setHeader("refresh", "2;url=/index_top.jsp");

        } else if (clientCode.equals(picturecode)) {
            // 只是向前端回显了错误，没有重定向,也是不刷新后台生成图片
            // 可以去掉前端js代码，实现绕过验证码
            writer.write(
                    "<img src='/image/lie_logo.png' width='30' height='30'/><b>" + GlobalVariables.title + "</b>"
                            + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
                            + " 您还没登录，请 <a href='/jsp/join/login.jsp' target='MyRight'>登录1</a>"
                            + " &nbsp <a href='/jsp/join/sqllogin.jsp' target='MyRight'>登录2</a>"

                            + " &nbsp <a href='/jsp/join/loginhadcheck.jsp' target='MyRight'>登录3(有验证码)</a>"
                            + " &nbsp <a href='/jsp/join/bypassloginhadcheck.jsp' target='MyRight'>登录4(有验证码)</a>"
                            + "&nbsp&nbsp<font color='red'>[提示]登录失败，账号或密码错误</font>"

                            + " &nbsp <a href='/jsp/register/register.jsp' target='MyRight'>用户注册</a>"
            );
        } else {
            writer.write(
                    "<img src='/image/lie_logo.png' width='30' height='30'/><b>" + GlobalVariables.title + "</b>"
                            + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
                            + " 您还没登录，请 <a href='/jsp/join/login.jsp' target='MyRight'>登录1</a>"
                            + " &nbsp <a href='/jsp/join/sqllogin.jsp' target='MyRight'>登录2</a>"

                            + " &nbsp <a href='/jsp/join/loginhadcheck.jsp' target='MyRight'>登录3(有验证码)</a>"
                            + " &nbsp <a href='/jsp/join/bypassloginhadcheck.jsp' target='MyRight'>登录4(有验证码)</a>"
                            + "&nbsp&nbsp<font color='red'>[提示]登录失败，验证码错误</font>"

                            + " &nbsp <a href='/jsp/register/register.jsp' target='MyRight'>用户注册</a>"
            );
        }
    }
}
