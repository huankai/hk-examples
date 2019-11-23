package com.hk.rocketmq.springcloud;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 延迟队列
 *
 * @author kevin
 * @date 2019-11-23 14:20
 */
public interface DelayOutput {

    String OUTPUT = "delay-stream-output";

    @Output(OUTPUT)
    MessageChannel output();
}
