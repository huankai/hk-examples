package com.hk.rocketmq.simple.four;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量消息生产者
 *
 * @author huangkai
 * @date 2019-11-21 10:23
 */
public class BatchProducer {

    public static void main(String[] args) throws MQClientException, RemotingException,
            InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("batch-producer");
        producer.setNamesrvAddr(Constants.NAME_SERVER);//指定 namesrv 地址，多个使用 ; 分隔
        producer.start();
        List<Message> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new Message("batchTopicTest",
                    "TagA",
                    "",
                    ("Batch Message " + i).getBytes()));

        }
        producer.send(list);// 不用循环，直接发送多条消息
        producer.shutdown();
    }
}
