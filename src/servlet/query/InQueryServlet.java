package servlet.query;

import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InQueryServlet")
public class InQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = response.getWriter();

        // 创建会话Session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); //获得session 会话中的 user
        if (user == null) {
            writer.write(
                    "<h2>Error</h2>"
                            + "抱歉，该页面需要登陆之后才能进行操作！ 2秒后跳转登录页！"
            );
            //等待2秒 跳转登录页
            response.setHeader("refresh", "2;url=/jsp/join/login.jsp");
        } else {
//            response.sendRedirect("/WEB-INF/jsp/user/queryuser.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/user/queryuser.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
