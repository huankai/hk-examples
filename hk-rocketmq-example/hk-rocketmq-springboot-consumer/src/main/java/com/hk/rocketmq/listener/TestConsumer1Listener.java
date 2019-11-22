package com.hk.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 测试一：
 * 发送 String 类型的消息 ，消费者 组为 test-group01
 *
 * @author kevin
 * @date 2019-11-22 15:21
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "test-group01")
public class TestConsumer1Listener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.debug("group01 Receive Message:{}", message);
    }
}
