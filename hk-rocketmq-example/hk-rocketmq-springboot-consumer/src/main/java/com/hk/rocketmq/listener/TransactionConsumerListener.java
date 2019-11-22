package com.hk.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费者接受事物消息
 *
 * @author kevin
 * @date 2019-11-22 16:27
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "transaction-topic", consumerGroup = "transaction-group")
public class TransactionConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.debug("receive batch Message:{}", message);
    }
}
