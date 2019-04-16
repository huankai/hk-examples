package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 死信队列
 *
 * @author kevin
 * @date 2019-4-16 14:24
 */
public interface AutoBindDlqOutput {

    String OUTPUT = "auto_bind_dlq_output";

    @Output(OUTPUT)
    MessageChannel output();
}
