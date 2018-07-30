package dao;

import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {

    Connection conn = DBHelper.getConn();
    PreparedStatement pstm = null;

    //添加注册用户
    public boolean insertUser(String username, String password, String mobilephone, String email, String city ,String delivery_address) {
        String sql = "insert into user(username,password,mobilephone,email,city,delivery_address,user_rights,register_time) values(?,?,?,?,?,?,?,now())";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, mobilephone);
            pstm.setString(4, email);
            pstm.setString(5, city);
            pstm.setString(6, delivery_address);
            pstm.setInt(7, 0); //默认注册都是 普通用户，需要 管理员权限需要后台修改
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 查看是否有重复的用户名-该函数重复使用

}
