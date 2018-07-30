package dao;

import entity.User;
import entity.UserCombination;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UersDao {
    Connection conn = DBHelper.getConn();
    PreparedStatement pstm = null;

    //获取用户头像路径
    public String getUersimage(int userid) {
        String sql = "select * from user_image where user_id=?;";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, userid);
            ResultSet rst = pstm.executeQuery();

            while (rst.next()) {
                System.out.println("当前 dao imag:" + rst.getString(3));
                return rst.getString(3);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //插入用户头像
    public Boolean uploadimage(int userid, String imagepath) {
        String sql = "INSERT INTO user_image(user_id,image_path,upload_time) VALUES(?,?,now());";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, userid);
            pstm.setString(2, imagepath);
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //根据用户id获取 用户信息
    public User getUersinform(int userid) {

        String sql = "select * from user where id=?;";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, userid);
            ResultSet rst = pstm.executeQuery();

            User user = new User();
            while (rst.next()) {
                user.setId(rst.getInt(1));
                user.setUsername(rst.getString(2));
                user.setPassword(rst.getString(3));
                user.setMobilephone(rst.getString(4));
                user.setEmail(rst.getString(5));
                user.setCity(rst.getInt(6));
                user.setDelivery_address(rst.getString(7));
                user.setUser_rights(rst.getInt(8));
                user.setRegister_time(rst.getTimestamp(9));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据用户名获取 用户信息
    public User getcurrentUers(String username) {

        String sql = "select * from user where username=?;";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            ResultSet rst = pstm.executeQuery();

            User user = new User();
            while (rst.next()) {
                user.setId(rst.getInt(1));
                user.setUsername(rst.getString(2));
                user.setPassword(rst.getString(3));
                user.setMobilephone(rst.getString(4));
                user.setEmail(rst.getString(5));
                user.setCity(rst.getInt(6));
                user.setDelivery_address(rst.getString(7));
                user.setUser_rights(rst.getInt(8));
                user.setRegister_time(rst.getTimestamp(9));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取表 条数
    public int gettableCount() {
        String sql = "select count(*) from user;";
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rst = pstm.executeQuery();
            while (rst.next()) {
                return rst.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    //获取全部用户信息
    public ArrayList<User> getallUers(String username) {
        ArrayList<User> allUser = new ArrayList<>();

        String sql = "select * from user;";
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rst = pstm.executeQuery();

            User user = new User();
            while (rst.next()) {
                User one_User = new User();
                user.setId(rst.getInt(1));
                user.setUsername(rst.getString(2));
                user.setPassword(rst.getString(3));
                user.setMobilephone(rst.getString(4));
                user.setEmail(rst.getString(5));
                user.setCity(rst.getInt(6));
                user.setDelivery_address(rst.getString(7));
                user.setUser_rights(rst.getInt(8));
                user.setRegister_time(rst.getTimestamp(9));
                allUser.add(one_User);
            }
            return allUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 获取指定部分用户数据
    public ArrayList<UserCombination> getpartUers(int start) {
        ArrayList<UserCombination> allUser = new ArrayList<>();

        String sql = "select * from user limit 3 offset ?;";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, start);
            ResultSet rst = pstm.executeQuery();

            while (rst.next()) {
                UserCombination user2 = new UserCombination();
                user2.setId(rst.getInt(1));
                user2.setUsername(rst.getString(2));
                user2.setPassword(rst.getString(3));
                user2.setMobilephone(rst.getString(4));
                user2.setEmail(rst.getString(5));
                user2.setCity(rst.getInt(6));
                user2.setDelivery_address(rst.getString(7));
                user2.setUser_rights(rst.getInt(8));
                user2.setRegister_time(rst.getTimestamp(9));
                //image path
                String uersimage = getUersimage(rst.getInt(1));
                user2.setImagepath(uersimage);
                allUser.add(user2);
            }
            return allUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 获取查询部分用户数据
    public ArrayList<UserCombination> getQueryUers(String key) {
        ArrayList<UserCombination> allUser = new ArrayList<>();

        String sql = "select * from `user` WHERE username like ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, '%' + key + '%');
            ResultSet rst = pstm.executeQuery();

            while (rst.next()) {
                UserCombination user2 = new UserCombination();
                user2.setId(rst.getInt(1));
                user2.setUsername(rst.getString(2));
                user2.setPassword(rst.getString(3));
                user2.setMobilephone(rst.getString(4));
                user2.setEmail(rst.getString(5));
                user2.setCity(rst.getInt(6));
                user2.setDelivery_address(rst.getString(7));
                user2.setUser_rights(rst.getInt(8));
                user2.setRegister_time(rst.getTimestamp(9));
                //image path
                String uersimage = getUersimage(rst.getInt(1));
                user2.setImagepath(uersimage);
                allUser.add(user2);
            }
            return allUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //delete user
    public Boolean deleteUser(int userid) {
        String sql = "DELETE FROM user WHERE id = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, userid);
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
