package com.hk.rocketmq.springcloud;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author kevin
 * @date 2019-11-23 10:49
 */
public interface TestOutput {

    String OUTPUT = "test-stream-output";

    @Output(OUTPUT)
    MessageChannel output();

}
