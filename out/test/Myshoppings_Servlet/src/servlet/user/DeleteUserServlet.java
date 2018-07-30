package servlet.user;

import dao.LoginDao;
import dao.UersDao;
import entity.User;
import entity.UserCombination;
import util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决乱码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        String userid = request.getParameter("userid");
        String grade = request.getParameter("grade");

        PrintWriter writer = response.getWriter();
        // 获取当前用户
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); //获得session 会话中的 user
        UersDao userDao = new UersDao();
        User user_allinfo = userDao.getcurrentUers(user.getUsername());

        // 逻辑 与 符号，必须两者都 True  ,先判断 前端的值，再次校验数据库的值
        if (!(grade.equals("2") && Integer.toString(user_allinfo.getUser_rights()).equals("2"))) {
            writer.write(
                    "<h2>Error</h2>"
                            + "没有执行该操作的权限！ 2秒后跳转用户管理页！"
            );
            //等待2秒 跳转
            response.setHeader("refresh", "2;url=/UserManageServlet");
            return;
        }

        Boolean aBoolean = userDao.deleteUser(Integer.parseInt(userid));

        if (aBoolean == true) {
            writer.write(
                    "<h2>Success</h2>"
                            + "恭喜,删除成功！ 2秒后跳转用户管理页！"
            );
            //等待2秒 跳转
            response.setHeader("refresh", "2;url=/UserManageServlet");
        } else {
            writer.write(
                    "<h2>Error</h2>"
                            + "抱歉，删除失败！ 2秒后跳转用户管理页！"
            );
            //等待2秒 跳转
            response.setHeader("refresh", "2;url=/UserManageServlet");
        }
    }
}
