package servlet.user;

import dao.UersDao;
import entity.Part;
import entity.User;
import entity.UserCombination;
import servlet.part.RemainderMethod;
import util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "UserManageServlet")
public class UserManageServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决乱码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        //先判断是否登录，然后在判断是否是管理员权限
        PrintWriter writer = response.getWriter();

        // 创建会话Session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); //获得session 会话中的 user

        if (user != null) {
            //获取User 校验 里面的管理员权限
            UersDao userDao = new UersDao();
            User currentuser_data = userDao.getcurrentUers(user.getUsername()); //pass 修改获取数据 Dao

            // 这里校验不严谨 就会有垂直越权访问
            if (currentuser_data.getUser_rights() < 1 ) {
                writer.write(
                        "<h2>Without this authority!</h2>"
                                + "抱歉，该页面需要管理员 或 超级管理员权限才能进入！"
                );
            } else {
                //new part
                Part part = new Part();
                part.setDefault_count(3);
                part.setAll_number(userDao.gettableCount());

                RemainderMethod remainderMethod = new RemainderMethod();
                int remainder = remainderMethod.getRemainder(part.getDefault_count(), part.getAll_number());
                part.setAll_pape(remainder);
                part.setCurrent_pape(1);

                // 封装出对应条数的User 发送到jsp
                /**
                 * 123       1    1*3-2
                 * 456       2    2*3-2
                 * 789       3    3*3-2
                 * 10 11 12  4    ...
                 */
                if (part.getCurrent_pape() != 1) {
                    ArrayList<UserCombination> userCombinations = userDao.getpartUers(part.getCurrent_pape() * 3 - 3);
                    session.setAttribute("usercombination", userCombinations); //展示的用户数据
                } else {
                    ArrayList<UserCombination> userCombinations = userDao.getpartUers(0);
                    session.setAttribute("usercombination", userCombinations); //展示的用户数据
                }
                session.setAttribute("part", part); // 分页数据

                // 获取当前用户
                String imagepath = userDao.getUersimage(currentuser_data.getId());
                Tool tool = new Tool();
                UserCombination current_user = tool.mergeTwoUser(currentuser_data,imagepath);
                session.setAttribute("current_user", current_user); // 分页数据

                // get path
                String servletPath = request.getServletPath();
                System.out.println("current usrmanage path:"+servletPath); // /UserManageServlet

                if(servletPath.equals("/UserManageServlet")){
                    request.getRequestDispatcher("/jsp/user/user_manage.jsp").forward(request, response);
                }else if(servletPath.equals("/UserManageServlet2")){
                    request.getRequestDispatcher("/jsp/user/user_manage2.jsp").forward(request, response);
                }

            }
        } else {
            writer.write(
                    "<h2>Error</h2>"
                            + "抱歉，该页面需要登陆之后才能进行操作！ 2秒后跳转登录页！"
            );
            //等待2秒 跳转登录页
            response.setHeader("refresh", "2;url=/jsp/join/login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
