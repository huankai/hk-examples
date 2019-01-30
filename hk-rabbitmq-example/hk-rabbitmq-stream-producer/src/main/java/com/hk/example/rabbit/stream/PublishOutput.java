package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 发布、订阅模式
 *
 * @author huangkai
 * @date 2019-01-30 10:39
 */
public interface PublishOutput {

    String OUTPUT = "publish_output";

    @Output(OUTPUT)
    MessageChannel output();

}
