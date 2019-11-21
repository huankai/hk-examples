package com.hk.rocketmq.simple.three;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;


/**
 * 延迟消息消费者
 *
 * @author huangkai
 * @date 2019-11-19 23:16
 */
public class DelayConsumer {


    public static void main(String[] args) throws MQClientException, RemotingException,
            InterruptedException, MQBrokerException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("delay-simple-consumer");
        consumer.setNamesrvAddr(Constants.NAME_SERVER);
        consumer.subscribe("DelayTopicTest", "*");
        /*
           使用 MessageListenerOrderly 时，会使用一个线程处理一个队列的消息，严格保证消息消费的顺序
         */
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(" Receive Message:" + new String(msg.getBody()) +
                            ", 延迟时间:" + (System.currentTimeMillis() - msg.getStoreTimestamp()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
