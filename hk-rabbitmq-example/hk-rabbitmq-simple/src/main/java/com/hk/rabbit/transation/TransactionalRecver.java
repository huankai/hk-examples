package com.hk.rabbit.transation;


import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 消费者事物
 */
public class TransactionalRecver {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(TransactionalProducer.TRANSACTIONAL_QUEUE_NAME, false, false, false, null);

        channel.basicConsume(TransactionalProducer.TRANSACTIONAL_QUEUE_NAME, true,
                (consumerTag, delivery) -> System.out.println("收到消息：" + new String(delivery.getBody(), StandardCharsets.UTF_8)),
                consumerTag -> {
                });
    }
}
