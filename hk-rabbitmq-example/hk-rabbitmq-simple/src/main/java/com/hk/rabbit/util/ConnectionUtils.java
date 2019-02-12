package com.hk.rabbit.util;


import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("test");
        connectionFactory.setVirtualHost("/test");
        return connectionFactory.newConnection();
    }

    /**
     * 获取集群连接
     *
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getClusterConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("192.168.64.128", 5672));
        addresses.add(new Address("192.168.64.129", 5672));
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        return connectionFactory.newConnection(addresses);
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
