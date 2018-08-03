package servlet.user;

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


@WebServlet(name = "PersonalcenterServlet")
public class PersonalcenterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决乱码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

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
        }else{
            // 创建session Dao模型
            UersDao userDao = new UersDao();

            //获取数据库中 该登录的用户数据 ，并放到Session中
            User currentuser_data = userDao.getcurrentUers(user.getUsername()); //pass 修改获取数据 Dao
            String imagepath = userDao.getUersimage(currentuser_data.getId());
            Tool tool = new Tool();
            UserCombination usercombination = tool.mergeTwoUser(currentuser_data,imagepath);

            session.setAttribute("currentuser_data", usercombination);
            request.getRequestDispatcher("/WEB-INF/jsp/user/personal_center.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
