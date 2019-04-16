package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 手动确认模式
 *
 * @author kevin
 * @date 2019-4-16 13:57
 */
public interface AcknowledgeModeInput {

    String INPUT = "acknowledge_mode_input";

    @Input(INPUT)
    SubscribableChannel acknowledgeModeInput();
}
