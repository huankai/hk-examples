package com.hk.rocketmq.springcloud;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author kevin
 * @date 2019-11-23 14:26
 */
public interface DelayInput {

    String INPUT = "delay-stream-input";

    @Input(INPUT)
    SubscribableChannel input();
}
