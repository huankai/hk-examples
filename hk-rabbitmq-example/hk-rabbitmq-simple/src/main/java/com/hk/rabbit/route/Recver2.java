package com.hk.rabbit.route;

import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 消息接收者01
 *
 * @author huangkai
 * @date 2019-01-17 10:33
 */
public class Recver2 {

    public static void main(String[] args) throws IOException, TimeoutException {
        String QUEUE_NAME = "test_route_2";
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列到交换机，接收 routeKey 为 key1 和 key3 的消息
//        channel.queueBind(QUEUE_NAME, Sender.EXCHANGE_NAME, "key1");
//        channel.queueBind(QUEUE_NAME, Sender.EXCHANGE_NAME, "key3");
//        channel.basicQos(1);

        /*
         * 第二个参数 表示是否自动确定，这里设置为 false
         */
        channel.basicConsume(QUEUE_NAME, false, (consumerTag, delivery) -> {
            System.out.println("收到消息：" + new String(delivery.getBody(), StandardCharsets.UTF_8));
            // 手动确认 ，参数2,false 为确认收到消息, true 为拒接收到消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }, consumerTag -> {
        });
    }
}
