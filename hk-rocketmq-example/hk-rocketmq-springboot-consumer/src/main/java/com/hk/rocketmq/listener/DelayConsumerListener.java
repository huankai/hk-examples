package com.hk.rocketmq.listener;

import com.hk.commons.util.date.DatePattern;
import com.hk.commons.util.date.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 消费者接受延迟消息
 *
 * @author kevin
 * @date 2019-11-22 16:27
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "delay-topic", consumerGroup = "delay-group")
public class DelayConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.debug("receive delay Message:{},currentDate:{}",
                message, DateTimeUtils.localDateTimeToString(LocalDateTime.now(), DatePattern.YYYY_MM_DD_HH_MM_SS));
    }
}
