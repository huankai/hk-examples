package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 手动确认模式
 *
 * @author kevin
 * @date 2019-4-16 13:57
 */
public interface AcknowledgeModeOutput {

    String OUTPUT = "acknowledge_mode_output";

    @Output(OUTPUT)
    MessageChannel output();
}
