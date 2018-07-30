package servlet.login;

import entity.User;
import util.GlobalVariables;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码
        resp.setContentType("text/html;charset=utf-8");

        // 创建会话Session
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user"); //获得session 会话中的 user
        if (user == null) {
            resp.getWriter().print(
                    "<img src='/image/lie_logo.png' width='30' height='30'/><b>" + GlobalVariables.title + "</b>"
                            + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
                            + " 您还没登录，请 <a href='/jsp/join/login.jsp' target='MyRight'>登录1</a>"
                            + " &nbsp <a href='/jsp/join/MySQLCodeclogin.jsp' target='MyRight'>登录2</a>"
                            + " &nbsp <a href='/jsp/join/sqllogin.jsp' target='MyRight'>登录3</a>"

                            + " &nbsp <a href='/jsp/join/loginhadcheck.jsp' target='MyRight'>登录4(有验证码)</a>"
                            + " &nbsp <a href='/jsp/join/bypassloginhadcheck.jsp' target='MyRight'>登录5(有验证码)</a>"

                            + " &nbsp <a href='/jsp/register/register.jsp' target='MyRight'>用户注册</a>"
            );
        } else {
            resp.getWriter().print(
                    "<img src='/image/lie_logo.png' width='30' height='30'/><b>" + GlobalVariables.title + "</b>"
                            + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
                            + " 您已登录,欢迎您&nbsp <b>" + user.getUsername() + "</b> &nbsp!"
                            + "&nbsp&nbsp <a href='/LogoutServlet' target='MyTop'>退出</a>"
            );
            //创建Cookie存放Session的标识号 , 一下步骤是否可以省略？系统会默认保存session_id ?
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(60 * 30); //默认是什么时间单位？
            cookie.setPath("/cookietext"); //外部是否能访问？ 不能
            resp.addCookie(cookie);
        }
    }
}
