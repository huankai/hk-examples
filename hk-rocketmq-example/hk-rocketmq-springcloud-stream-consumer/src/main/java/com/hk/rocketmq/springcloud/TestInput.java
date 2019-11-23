package com.hk.rocketmq.springcloud;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author kevin
 * @date 2019-11-23 10:53
 */
public interface TestInput {

    String INPUT = "test-stream-input";

    @Input(INPUT)
    SubscribableChannel input();
}
