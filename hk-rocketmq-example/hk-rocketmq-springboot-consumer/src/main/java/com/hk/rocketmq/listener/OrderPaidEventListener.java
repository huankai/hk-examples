package com.hk.rocketmq.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author kevin
 * @date 2019-11-22 15:35
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "custom-obj-topic-1", consumerGroup = "custom-obj-consumer")
public class OrderPaidEventListener implements RocketMQListener<OrderPaidEventListener.OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent message) {
        log.debug("Receive Message: {}", message);
    }

    @Data
    public static class OrderPaidEvent {

        private String orderId;

        private BigDecimal paidMoney;
    }
}
