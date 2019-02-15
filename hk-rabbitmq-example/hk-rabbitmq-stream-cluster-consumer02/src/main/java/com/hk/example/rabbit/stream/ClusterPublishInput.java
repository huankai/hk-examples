package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author huangkai
 * @date 2019-02-14 09:47
 */
public interface ClusterPublishInput {

    String INPUT = "cluster_publish_input";

    @Input(INPUT)
    SubscribableChannel input();
}
