package com.hk.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 *
 * @author: huangkai
 * @date 2018-05-29 11:07
 */
public class ConnectionUtils {

    private static String url;

    private static String username;

    private static String password;

    static {
        init();
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        url = PropertyUtils.get("jdbc.url");
        username = PropertyUtils.get("jdbc.username");
        password = PropertyUtils.get("jdbc.password");
        String driver = PropertyUtils.get("jdbc.driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
