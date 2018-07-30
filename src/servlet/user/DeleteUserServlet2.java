package servlet.user;

import dao.UersDao;
import util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "DeleteUserServlet")
public class DeleteUserServlet2 extends HttpServlet {
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

        //校验等级
        Tool tool = new Tool();
        String md5_2 = null;
        try {
            md5_2 = tool.EncoderByMd5("2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(!grade.equals(md5_2)){
            writer.write(
                    "<h2>Error</h2>"
                            + "没有执行该操作的权限！ 2秒后跳转用户管理页！"
            );
            //等待2秒 跳转
            response.setHeader("refresh", "2;url=/UserManageServlet");
            return;
        }

        UersDao userDao = new UersDao();
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
