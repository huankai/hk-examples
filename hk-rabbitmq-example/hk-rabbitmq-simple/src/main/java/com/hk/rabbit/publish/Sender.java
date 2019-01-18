package com.hk.rabbit.publish;

import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author huangkai
 * @date 2019-01-17 11:10
 */
public class Sender {

    final static String EXCHANGE_NAME = "test_exchange";//定义交换机的名字

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        /*
         * 声明交换机
         * 参数二固定为 fanout
         */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        /*
         * 发布订阅模式,因为消息是先发到交换机中,而交换机是没有保存功能的,所以如果没有消费者,消息会丢失
         */
        channel.basicPublish(EXCHANGE_NAME, "", null, "发布订阅模式的消息".getBytes());
        channel.close();
        ConnectionUtils.close(connection);
    }
}
