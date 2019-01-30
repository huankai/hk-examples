package com.hk.rabbit.transation;


import com.hk.commons.util.StringUtils;
import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者 Confirm
 */
public class ConfirmProducer {

    final static String CONFIRM_QUEUE_NAME = "confirm_queue_name";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(CONFIRM_QUEUE_NAME, false, false, false, null);
        channel.confirmSelect();
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(StringUtils.EMPTY, CONFIRM_QUEUE_NAME, null, "confirm  Message".getBytes());
        }

        if (channel.waitForConfirms()) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败.");
        }

        channel.close();
        ConnectionUtils.close(connection);


    }
}
