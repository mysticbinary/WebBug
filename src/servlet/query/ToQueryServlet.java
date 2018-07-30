package servlet.query;

import dao.UersDao;
import entity.UserCombination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ToQueryServlet")
public class ToQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String querystr = request.getParameter("querystr");

        //获取User 校验 里面的管理员权限
        UersDao userDao = new UersDao();
        ArrayList<UserCombination> queryUersall = userDao.getQueryUers(querystr);

        HttpSession session = request.getSession();
        session.setAttribute("usercombination", queryUersall); //展示的用户数据
        request.setAttribute("querystr", querystr); //展示的用户数据  request.setAttribute()
        request.getRequestDispatcher("/jsp/user/showqueryuser.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
