package com.hk.rabbit.transation;


import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 asyn
 */
public class AsynRecver {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(AsynProducer.ASYN_QUEUE_NAME, false, false, false, null);

        channel.basicConsume(AsynProducer.ASYN_QUEUE_NAME, true,
                (consumerTag, delivery) -> System.out.println("收到消息：" + new String(delivery.getBody(), StandardCharsets.UTF_8)),
                consumerTag -> {
                });
    }
}
