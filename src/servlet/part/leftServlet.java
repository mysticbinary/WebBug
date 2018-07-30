package servlet.part;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

import dao.UersDao;
import entity.Part;
import entity.User;
import entity.UserCombination;
import util.Tool;

@WebServlet(name = "leftServlet")
public class leftServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Part part = (Part) session.getAttribute("part");
        String left1_right1 = request.getParameter("left1_right1");

        // 判断是左边 还是 右边
        if (left1_right1.equals("left1")) {
            int temp = part.getCurrent_pape();
            if (temp > 1) {
                part.setCurrent_pape(temp - 1);
            }
        } else if (left1_right1.equals("right1")) {
            int temp = part.getCurrent_pape();
            if (temp < part.getAll_pape()) {
                part.setCurrent_pape(temp + 1);
            }
        }

        UersDao userDao = new UersDao();
        System.out.println(part.getCurrent_pape() + "--- getcurrent pape");
        if (part.getCurrent_pape() != 1) {
            ArrayList<UserCombination> userCombinations = userDao.getpartUers(part.getCurrent_pape() * 3 - 3);
            session.setAttribute("usercombination", userCombinations); //展示的用户数据
        } else {
            ArrayList<UserCombination> userCombinations = userDao.getpartUers(0);
            session.setAttribute("usercombination", userCombinations); //展示的用户数据
        }
        session.setAttribute("part", part); // 分页数据

        // 获取当前用户
        User user = (User) session.getAttribute("user"); //获得session 会话中的 user
        User currentuser_data = userDao.getcurrentUers(user.getUsername()); //pass 修改获取数据 Dao
        String imagepath = userDao.getUersimage(currentuser_data.getId());
        Tool tool = new Tool();
        UserCombination current_user = tool.mergeTwoUser(currentuser_data,imagepath);
        session.setAttribute("current_user", current_user);

        request.getRequestDispatcher("/jsp/user/user_manage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
