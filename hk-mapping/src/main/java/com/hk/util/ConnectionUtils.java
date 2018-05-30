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

    /**
     * 获取连接
     *
     * @param url
     * @param username
     * @param password
     * @param driver
     * @return
     */
    public static Connection getConnection(String url, String username, String password, String driver) {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
