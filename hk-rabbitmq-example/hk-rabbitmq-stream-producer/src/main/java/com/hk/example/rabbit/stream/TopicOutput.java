package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author huangkai
 * @date 2019-01-30 15:31
 */
public interface TopicOutput {

    String OUTPUT = "topic_output";

    @Output(OUTPUT)
    MessageChannel output();
}
