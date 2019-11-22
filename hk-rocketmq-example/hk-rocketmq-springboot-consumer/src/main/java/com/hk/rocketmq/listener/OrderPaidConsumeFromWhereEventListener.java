package com.hk.rocketmq.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 接受自定义类型的对象消息，
 * 并设置从指定位置开始消费
 * <p>
 * 注意：如果需要重新消费消息，必须重新指定 消费者组名 consumerGroup , 这个组名不能是已消费过的组名
 * 如果这个组名已消费过，不是会重新消费的
 *
 * @author kevin
 * @date 2019-11-22 15:35
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "custom-obj-topic-1", consumerGroup = "custom-obj-consumer-lifecycle1")
public class OrderPaidConsumeFromWhereEventListener implements
        RocketMQListener<OrderPaidConsumeFromWhereEventListener.OrderPaidEvent>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(OrderPaidEvent message) {
        log.debug("Lifecycle Receive Message: {}", message);
    }

    /**
     * 配置消费者从指定位置开始消费
     *
     * @param consumer
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);// 设置从开始消费

//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET); // 设置从最后位置消费
//
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP); // 设置从指定时间开始消费
//        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));//并指定消费开始时间
    }

    @Data
    public static class OrderPaidEvent {

        private String orderId;

        private BigDecimal paidMoney;
    }
}
