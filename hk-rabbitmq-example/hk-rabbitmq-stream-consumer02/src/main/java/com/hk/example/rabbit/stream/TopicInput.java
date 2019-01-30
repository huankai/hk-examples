package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Topic消息生产者
 *
 * @author huangkai
 * @date 2019-01-30 15:26
 */
public interface TopicInput {

    String INPUT = "topic_input";

    @Input(INPUT)
    SubscribableChannel input();

}
