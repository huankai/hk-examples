package com.hk.rocketmq.simple.two;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 2、 异步消费者
 *
 * @author kevin
 * @date 2019-11-18 17:08
 */
public class AsyncConsumer {

    public static void main(String[] args) throws Exception {

        // Instantiate with specified consumer group name.在 push 模式下，不建议做批处理，使用 DefaultMQPullConsumer 做批处理
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("async-consumer");

        // Specify name server addresses.
        consumer.setNamesrvAddr(Constants.NAME_SERVER);
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);//从哪里开始消费
//        consumer.setConsumeThreadMax(20); // 消费者最大线程数
//        consumer.setConsumeThreadMin(5); //消费者最小线程数
//        consumer.setPullBatchSize(32); //指定批量获取消息大小，默认为 32
//        consumer.setPullInterval(0);//消息拉取线程每多长时间 拉取一次，默认为 0
//        consumer.setAllocateMessageQueueStrategy();
        // Subscribe one more more topics to consume.
//
//        设置消息消费模式，广播与集群，在集群模式下自动负载均衡，广播模式下相同给的消费者都能收到消息，默认为集群模式
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.subscribe("asyncTopicTest", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                System.out.println("Receive New Messages" + msg);
            }
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 标记该消息已经被成功消费
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }

}
