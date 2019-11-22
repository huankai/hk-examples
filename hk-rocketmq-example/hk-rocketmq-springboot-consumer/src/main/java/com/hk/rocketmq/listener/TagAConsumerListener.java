package com.hk.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费者接受 Tag 消息
 *
 * @author kevin
 * @date 2019-11-22 16:27
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "tag-topic-1", consumerGroup = "tag-group-A", selectorExpression = "TagA")
public class TagAConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.debug("receive sync Message:{}", message);
    }
}
