package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 延时队列
 *
 * @author huangkai
 * @date 2019-02-11 14:15
 */
public interface DelayedInput {

    String INPUT = "delayed_input";

    @Input(INPUT)
    SubscribableChannel input();

}
