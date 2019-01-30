package com.hk.rabbit.util;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author huangkai
 * @date 2019-01-17 10:24
 */
public class ConnectionUtils {

    /**
     * 获取连接
     *
     * @return {@link Connection}
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("182.61.40.18");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest#root");
        connectionFactory.setVirtualHost("/");
        return connectionFactory.newConnection();
    }

    /**
     * 关闭连接
     *
     * @param connection
     */
    public static void close(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
