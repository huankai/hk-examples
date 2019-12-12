package com.hk.rabbit.cluster;

import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 消息接收者
 *
 * @author huangkai
 * @date 2019-01-17 10:33
 */
public class Recver {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getClusterConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Sender.QUEUE_NAME, false, false, false, null);

        channel.basicConsume(Sender.QUEUE_NAME, true, (consumerTag, delivery) ->
                        System.out.println("收到消息：" + new String(delivery.getBody(), StandardCharsets.UTF_8)),
                consumerTag -> {
                });

//        channel.basicConsume(Sender.QUEUE_NAME, true, new DefaultConsumer(channel) {
//
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println("收到消息：" + new String(body));
//            }
//        });
    }
}
