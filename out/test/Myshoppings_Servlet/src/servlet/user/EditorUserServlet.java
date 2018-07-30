package servlet.user;

import dao.UersDao;
import entity.Tempid;
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

@WebServlet(name = "EditorUserServlet")
public class EditorUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决乱码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        //如何收到客户端点击的id ? hidden
        String userid = request.getParameter("userid");
        //System.out.println("id:" + userid);

        // 将刚点击的userid 存储到 session
        Tempid tempid = new Tempid();
        tempid.setId(userid);
        HttpSession session = request.getSession();
        session.setAttribute("tempid",tempid);

        int userid2 = Integer.parseInt(userid);

        //通过id将该用户的全部信息查询出来
        User user = new User();
        UersDao userDao = new UersDao();
        User uersinform = userDao.getUersinform(userid2);

        String imagepath = userDao.getUersimage(userid2);
        //new
        Tool tool = new Tool();
        UserCombination usercombination = tool.mergeTwoUser(uersinform,imagepath);

        //获取数据库中 该登录的用户数据 ，并放到Session中
        session.setAttribute("uersinform", usercombination);
        request.getRequestDispatcher("/jsp/user/editor_user.jsp").forward(request, response);
    }
}
