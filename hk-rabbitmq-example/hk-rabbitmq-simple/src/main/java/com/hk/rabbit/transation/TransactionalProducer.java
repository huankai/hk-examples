package com.hk.rabbit.transation;


import com.hk.commons.util.StringUtils;
import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者事物
 */
public class TransactionalProducer {

    final static String TRANSACTIONAL_QUEUE_NAME = "transactional_queue_name";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(TRANSACTIONAL_QUEUE_NAME, false, false, false, null);
        try {
            channel.txSelect();
            channel.basicPublish(StringUtils.EMPTY, TRANSACTIONAL_QUEUE_NAME, null, "Tran Message".getBytes());
//            System.out.println(1 / 0);//模拟出现异常
            channel.txCommit();
        } catch (Exception e) {
            e.printStackTrace();
            channel.txRollback(); // 出现异常回滚，消费不能到达 rabbitmq 服务器
        } finally {
            channel.close();
            ConnectionUtils.close(connection);

        }
    }
}
