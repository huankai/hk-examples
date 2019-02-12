package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * rabbitmq 延迟队列
 *
 * @author huangkai
 * @date 2019-02-11 14:10
 */
public interface DelayedOutput {

    String OUTPUT = "delayed_output";

    @Output(OUTPUT)
    MessageChannel output();
}
