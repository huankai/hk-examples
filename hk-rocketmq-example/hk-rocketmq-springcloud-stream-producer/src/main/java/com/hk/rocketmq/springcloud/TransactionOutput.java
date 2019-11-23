package com.hk.rocketmq.springcloud;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 发送事物消息
 * @author kevin
 * @date 2019-11-23 10:49
 */
public interface TransactionOutput {

    String OUTPUT = "transaction-stream-output";

    @Output(OUTPUT)
    MessageChannel output();

}
