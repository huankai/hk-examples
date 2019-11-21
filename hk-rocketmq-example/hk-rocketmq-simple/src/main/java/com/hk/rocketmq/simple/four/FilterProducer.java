package com.hk.rocketmq.simple.four;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 过滤消息生产者
 * 以 Tag 过滤
 *
 * @author huangkai
 * @date 2019-11-21 10:23
 */
public class FilterProducer {

    public static void main(String[] args) throws MQClientException, RemotingException,
            InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("filter-producer");
        producer.setNamesrvAddr(Constants.NAME_SERVER);//指定 namesrv 地址，多个使用 ; 分隔
        producer.start();
        Message message = new Message("filterTopicTest", // 发送到指定 topic 的 TagA
                "TagC",
                "",
                "filter Message ".getBytes());
        producer.send(message);
        producer.shutdown();
    }
}
