package com.hk.rocketmq.springcloud;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author kevin
 * @date 2019-11-23 13:47
 */
public interface TagOutput {

    String OUTPUT = "tag-stream-output";

    @Output(OUTPUT)
    MessageChannel output();
}
