package servlet.messages;

import dao.MessagesDao;
import entity.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShowMessagesServlet")
public class ShowMessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 创建session Dao模型
        HttpSession session = request.getSession();
        MessagesDao messagesDao = new MessagesDao();

        //获取留言板表中全部数据 装在list里面，并放到Session中
        ArrayList<Message> all_messages = messagesDao.getAllMessages();
        session.setAttribute("all_messages", all_messages);


        //get path
        String servletPath = request.getServletPath();

        if(servletPath.equals("/ShowMessagesServlet")){
            request.getRequestDispatcher("/jsp/messages/seemessages.jsp").forward(request, response);
        }else if(servletPath.equals("/ShowMessagesServlet2")){
            request.getRequestDispatcher("/jsp/messages/seemessages2.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
