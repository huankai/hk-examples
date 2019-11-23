package com.hk.rocketmq.springcloud;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author kevin
 * @date 2019-11-23 13:46
 */
public interface TagInput {

    String INPUT = "tag-stream-input";

    @Input(INPUT)
    SubscribableChannel input();
}
