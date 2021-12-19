package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    public static Connection conn;
    //private static String url = "jdbc:mysql://localhost:3306/myshoppings?useUnicode=true&characterEncoding=UTF-8";
    private static String url = "jdbc:mysql://mysql-db:3306/myshoppings?useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "root";

    // get connnection
    public static Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void CloseConn() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}