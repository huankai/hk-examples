package com.hk.rocketmq.simple.four;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;


/**
 * 过滤消息消费者
 *
 * @author huangkai
 * @date 2019-11-19 23:16
 */
public class FilterConsumer {


    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("filter-simple-consumer");
        consumer.setNamesrvAddr(Constants.NAME_SERVER);
//        consumer.subscribe("filterTopicTest", "TagA || TagB"); //订阅 指定 topic 的TagA 或 TabB，如果为 * 则消费该 topic 所有消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("filter Receive Message:" + new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
