package servlet.login;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码
        resp.setContentType("text/html;charset=utf-8");
        // 将 Session对象中的User对象移除
        req.getSession().removeAttribute("user");
        resp.sendRedirect("/IndexServlet");
    }
}
