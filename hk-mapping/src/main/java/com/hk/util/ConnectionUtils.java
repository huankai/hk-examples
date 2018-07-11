package com.hk.util;

import com.hk.entity.ConnectionModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 *
 * @author: kevin
 * @date 2018-05-29 11:07
 */
public class ConnectionUtils {

    /**
     * 获取连接
     *
     * @param connectionModel
     * @return
     */
    public static Connection getConnection(ConnectionModel connectionModel) {
        try {
            Class.forName(connectionModel.getDriverName());
            return DriverManager.getConnection(connectionModel.getJdbcUrl(),
                    connectionModel.getUsername(), connectionModel.getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
