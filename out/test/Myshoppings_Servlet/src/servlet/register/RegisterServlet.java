package servlet.register;

import dao.RegisterDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String mobilephone = req.getParameter("mobilephone");
        String email = req.getParameter("email");
        String city = req.getParameter("city");
        String delivery_address = req.getParameter("delivery_address");

        PrintWriter writer = resp.getWriter();

        // 先判断是否用户名重复
        //pass

        //插入数据库
        if (new RegisterDao().insertUser(username, password, mobilephone, email, city, delivery_address)) {

            //resp.sendRedirect("/jsp/join/login.jsp");
            writer.write(
                    "<h2>Register</h2>"
                            + "恭喜，注册成功！ 2秒后跳转登录页！"
            );
            //等待2秒 跳转登录页
            resp.setHeader("refresh", "2;url=/jsp/join/login.jsp");
        } else {
            writer.write(
                    "<h2>Register</h2>"
                    + "抱歉，注册失败！请重新尝试。"
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
