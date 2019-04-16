package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 死信队列
 *
 * @author kevin
 * @date 2019-4-16 14:24
 */
public interface AutoBindDlqInput {

    String INPUT = "auto_bind_dlq_input";

    @Input(INPUT)
    SubscribableChannel autoBindDlq();
}
