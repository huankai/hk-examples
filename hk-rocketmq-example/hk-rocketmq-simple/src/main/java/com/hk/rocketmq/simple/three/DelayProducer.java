package com.hk.rocketmq.simple.three;

import com.hk.rocketmq.simple.constants.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 延迟消息生产者
 *
 * @author huangkai
 * @date 2019-11-21 10:23
 */
public class DelayProducer {

    public static void main(String[] args) throws MQClientException, RemotingException,
            InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("delay-producer");
        producer.setNamesrvAddr(Constants.NAME_SERVER);//指定 namesrv 地址，多个使用 ; 分隔
        producer.start();
        Message msg = new Message("delayTopicTest",
                "TagA",
                "",
                "Delay Message".getBytes());
        /*
         设置消息延迟等级，
         rocketmq 消息的延迟时间不支持自定义，
         只能设置等级 ，预设值的延迟时间间隔为：
            1s、 5s、 10s、 30s、 1m、 2m、 3m、 4m、 5m、 6m、 7m、 8m、 9m、 10m、 20m、 30m、 1h、 2h
         */
        msg.setDelayTimeLevel(3);
        producer.send(msg);
        producer.shutdown();
    }
}
