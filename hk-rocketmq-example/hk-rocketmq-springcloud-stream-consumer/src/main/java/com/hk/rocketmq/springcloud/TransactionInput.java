package com.hk.rocketmq.springcloud;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author kevin
 * @date 2019-11-23 11:05
 */
public interface TransactionInput {

    String INPUT = "transaction-stream-input";

    @Input(INPUT)
    SubscribableChannel input();
}
