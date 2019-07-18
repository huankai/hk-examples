package com.hk.rocketmq.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 01、生产者
 *
 * @author huangkai
 * @date 2019-07-14 12:14
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new
                DefaultMQProducer("simple-producer");
        // Specify name server addresses.
        producer.setNamesrvAddr("182.61.40.18:9876");//指定 namesrv 地址，多个使用 ; 分隔
//        producer.setCompressMsgBodyOverHowmuch(1024 * 1024 * 5); // 消息到达多大时进行压缩:5M
//        producer.setRetryTimesWhenSendFailed(3); //设置消息发送失败重试次数
//        producer.setHeartbeatBrokerInterval(1000 * 15);//配置心跳时间 15 秒
//        producer.setMaxMessageSize(1024 * 1024 * 20); // 设置消息最大大小， 20M
//        producer.createTopic();//创建主题
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 10; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();

    }
}
