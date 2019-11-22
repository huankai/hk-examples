package com.hk.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费者接受异步消息
 *
 * @author kevin
 * @date 2019-11-22 16:27
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "async-topic", consumerGroup = "async-group")
public class AsyncConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.debug("receive Async Message:{}", message);
    }
}
