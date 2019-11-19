package com.hk.rocketmq.simple.three;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;


/**
 * 顺序消息消费者
 *
 * @author huangkai
 * @date 2019-11-19 23:16
 */
public class OrderConsumer {


    public static void main(String[] args) throws MQClientException, RemotingException,
            InterruptedException, MQBrokerException {
// Instantiate with specified consumer group name.在 push 模式下，不建议做批处理，使用 DefaultMQPullConsumer 做批处理
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("async-simple-consumer");

        // Specify name server addresses.
        consumer.setNamesrvAddr(Constants.NAME_SERVER);
        consumer.subscribe("orderTopicTest", "*");
        /*
           使用 MessageListenerOrderly 时，会使用一个线程处理一个队列的消息，严格保证消息消费的顺序
         */
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(Thread.currentThread().getName() + " Receive Message: " + new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
