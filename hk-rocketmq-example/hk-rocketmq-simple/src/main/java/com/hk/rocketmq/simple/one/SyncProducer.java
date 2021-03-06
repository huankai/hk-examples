package com.hk.rocketmq.simple.one;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 一、生产者发送同步消息
 *
 * @author kevin
 * @date 2019-10-16 15:02
 */
public class SyncProducer {

    public static void main(String[] args) throws MQClientException,
            UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        /*
         * 实例化消息生产者Producer:
         * 参数一: 指定生产的组名
         */
        DefaultMQProducer producer = new DefaultMQProducer("simple-producer");
        /*
         * 设置生产者的 nameSrv 地址，多个使用 ; 分隔
         */
        producer.setNamesrvAddr(Constants.NAME_SERVER);
//        producer.setCompressMsgBodyOverHowmuch(1024 * 1024 * 5); // 消息到达多大时进行压缩:5M
//        producer.setRetryTimesWhenSendFailed(3); //设置消息发送失败重试次数
//        producer.setHeartbeatBrokerInterval(1000 * 15);//配置心跳时间 15 秒
//        producer.setMaxMessageSize(1024 * 1024 * 20); // 设置消息最大大小， 20M
//        producer.createTopic();//创建主题
        /*
         * 启动Producer实例
         */
        producer.start();

        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("topicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        }
        /*
         *   如果不再发送消息，关闭Producer实例。
         */
        producer.shutdown();


    }
}
