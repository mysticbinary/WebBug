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

/**
 * Session 设计缺陷： 以下是流程图
 *
 * login - 1.jsp
 * 	发送
 * 	username 参数
 * 	password 参数
 *
 * 	|
 * 	|
 * 	↓
 *
 * errorsession.java
 * 	3.不判断前就设定seeion
 * 		将username 参数 ，设定到seeion
 * 	4.判断
 * 	如果登录成功 将名字设置在seeion
 * 	如果失败就重定向到login.jsp
 *
 * 	|
 * 	| （302跳转）
 * 	↓
 *
 *
 * login - 2.jsp
 * 	1.首先移除会话里面all对象
 * 	2.发送
 * 	username 参数
 * 	password 参数
 *
 */
public class ErrorSessionLoginServlet extends HttpServlet {

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

        PrintWriter writer = resp.getWriter();

        // 3. 不判断前就先设定seeion
        User user = new User();
        user.setUsername(username);
        req.getSession().setAttribute("user", user);

        // 4. 判断
        // 校验数据库
        if (new LoginDao().Logincheck(username, password)) {
            //实体类 填充数据
            user.setUsername(username);
            user.setPassword(password);
            req.getSession().setAttribute("user", user);

            writer.write(
//                    "恭喜！您登录成功 2秒后跳转。"
                    "恭喜！登录成功 "
            );
            // 等待2秒 刷新
//            resp.setHeader("refresh", "2;url=/index_top.jsp");
//            resp.sendRedirect("/index_top.jsp");
        } else {
            // 重定向
            resp.sendRedirect("/jsp/join/errorsessionlogin2.jsp");
            // 转发
//            req.getRequestDispatcher("/jsp/join/errorsessionlogin.jsp").forward(req, resp);
        }
    }
}
