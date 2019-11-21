package com.hk.rocketmq.simple.four;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;


/**
 * SQL 过滤消息消费者
 *
 * @author huangkai
 * @date 2019-11-19 23:16
 */
public class SqlFilterConsumer {


    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("sql-filter-simple-consumer");
        consumer.setNamesrvAddr(Constants.NAME_SERVER);
        consumer.subscribe("sqlFilterTopicTest", MessageSelector.bySql("i > 3"));// 根据 sql 过滤 i 大于  3 的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("sql filter Receive Message:" + new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
