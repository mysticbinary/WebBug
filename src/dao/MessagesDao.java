package dao;

import entity.Message;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessagesDao {

    Connection conn = DBHelper.getConn();
    PreparedStatement pstm = null;

    //获取全部留言板信息
    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> messagess = new ArrayList<>();
        String sql = "select * from messages";
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rst = pstm.executeQuery();

            while (rst.next()) {
                Message message = new Message();
                message.setId(rst.getInt(1));
                message.setUsername(rst.getString(2));
                message.setTime(rst.getDate(3));
                message.setTitle(rst.getString(4));
                message.setMessage(rst.getString(5));
                messagess.add(message);
            }
            return messagess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //添加留言板信息
    public boolean addInfo(String username,String title, String message) {

        String sql = "insert into messages(name,time,title,message) values(?,now(),?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, title);
            pstm.setString(3, message);
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
