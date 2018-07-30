package dao;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.MySQLCodec;
import util.DBHelper;

import java.rmi.MarshalledObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    Connection conn = DBHelper.getConn();
    PreparedStatement pstm = null;
    ResultSet rs = null;

    //登录校验
    public boolean Logincheck(String username, String password) {
        String sql = "select * from user where username=? and password=?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            if (rs.next()) {
                //登陆成功
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //登录校验 - ESAPI.MySQLCodec 防护
    public boolean mySQLCodecLogin(String username, String password) {
        /**
         * MySQL数据库本身有两种运行模式
         * MySQLCodec.Mode.STANDARD  = 标准模式 (默认)
         * MySQLCodec.Mode.ANSI = ANSI模式
         *
         * MySQLCodec编码器原理：
         * 通过在字符前面加反斜杠字符来进行转义；
         * MySQLCodec转义后的 SQL 语句:select * from user where username='1\' \#\-\-' and password='1'
         * MySQLCodec转义后的 SQL 语句:select * from user where username='admin\' \-\-' and password='1'
         */
        MySQLCodec codec = new MySQLCodec(MySQLCodec.Mode.STANDARD );
        String username_1 = ESAPI.encoder().encodeForSQL(codec, username);
        String password_1 = ESAPI.encoder().encodeForSQL(codec, password);

        String sql = "select * from user where username=\'" + username_1 + "\' and password=\'" + password_1 + "\'";
        System.out.println("MySQLCodec转义后的 SQL 语句:" + sql);
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                //登陆成功
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //登录校验 - 可SQL 注入
    public boolean sqlInjectLog(String username, String password) {
        String sql = "select * from user where username=\'" + username + "\' and password=\'" + password + "\'";
        System.out.println("正在被尝试注入的 SQL 语句:" + sql);
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                //登陆成功
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
