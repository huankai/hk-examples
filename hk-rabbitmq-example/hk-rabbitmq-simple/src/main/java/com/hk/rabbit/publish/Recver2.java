package com.hk.rabbit.publish;

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
        String QUEUE_NAME = "pub_queue_2";
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME, Sender.EXCHANGE_NAME, "");
        /*
         *告诉服务器,在我们没有确认当前消息完成之前,不要给我发新的消息，
         * 如果每台服务器配置不一样，可能会出现某些服务器分配不均匀的情况，配置此参数后，会根据消费者的能力消费。
         *
         */
        channel.basicQos(1);

        /*
         * 第二个参数 表示是否自动确定，这里设置为 false
         */
        channel.basicConsume(QUEUE_NAME, false, (consumerTag, delivery) -> {
            System.out.println("收到消息：" + new String(delivery.getBody(), StandardCharsets.UTF_8));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 手动确认 ，参数2,false 为确认收到消息, true 为拒接收到消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }, consumerTag -> {
        });
    }
}
