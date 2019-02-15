package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author huangkai
 * @date 2019-02-14 09:42
 */
public interface ClusterPublishOutput {

    String OUTPUT = "cluster_publish_output";

    @Output(OUTPUT)
    MessageChannel output();
}
