package servlet.login;

import dao.LoginDao;
import entity.User;
import util.GlobalVariables;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SQLinjectLoginServlet")
public class MySQLCodecLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决乱码
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();

        // 校验数据库
        if (new LoginDao().mySQLCodecLogin(username, password)) {
            //实体类 填充数据
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            request.getSession().setAttribute("user", user);

            writer.write(
                    "恭喜！您登录成功 2秒后跳转。"
            );

            //等待2秒 刷新
            response.setHeader("refresh", "2;url=/index_top.jsp");
        } else {
            writer.write(
                    "<img src='/image/lie_logo.png' width='30' height='30'/><b>" + GlobalVariables.title + "</b>"
                            + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
                            + " 您还没登录，请 <a href='/jsp/join/login.jsp' target='MyRight'>登录1</a>"
                            + " &nbsp <a href='/jsp/join/MySQLCodeclogin.jsp' target='MyRight'>登录2</a>"
                            + "&nbsp&nbsp<font color='red'>[提示]登录失败，用户名或密码错误</font>"
                            + " &nbsp <a href='/jsp/join/sqllogin.jsp' target='MyRight'>登录3</a>"

                            + " &nbsp <a href='/jsp/join/loginhadcheck.jsp' target='MyRight'>登录4(有验证码)</a>"
                            + " &nbsp <a href='/jsp/join/bypassloginhadcheck.jsp' target='MyRight'>登录5(有验证码)</a>"

                            + " &nbsp <a href='/jsp/register/register.jsp' target='MyRight'>用户注册</a>"
            );
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
