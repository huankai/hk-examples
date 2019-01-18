package com.hk.rabbit.helloworld;

import com.hk.commons.util.StringUtils;
import com.hk.rabbit.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息发送者
 *
 * @author huangkai
 * @date 2019-01-17 10:28
 */
public class Sender {

    static final String QUEUE_NAME = "simplest";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        /*
         * 声明队列,如果队列存在则什么都不做,如果不存在才创建
         * 参数1 : 队列的名字
         * 参数2 : 是否持久化队列,我们的队列模式是在内存中的,如果 rabbitmq 重启会丢失,如果我们设置为 true, 则会保存到 erlang 自带的数据库中,重启后会重新读取
         * 参数3 : 是否排外,有两个作用,第一个当我们的连接关闭后是否会自动删除队列,作用二 是否私有当天前队列,如果私有了,其他通道不可以访问当前队列,如果为 true, 一般是一个队列只适用于一个消费者的时候
         * 参数4 : 是否自动删除
         * 参数5 ：一些其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        /*
         * 参数1： 交换机名称
         * 参数2： 队列名称
         * 参数3：一些其他参数
         * 参数4：消息内容
         */
        channel.basicPublish(StringUtils.EMPTY, QUEUE_NAME, null, "Message".getBytes());
        channel.close();
        ConnectionUtils.close(connection);
    }
}
