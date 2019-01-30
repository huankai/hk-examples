package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author huangkai
 * @date 2019-01-30 10:49
 */
public interface RouteKeyOutput {

    String OUTPUT = "routekey_output";

    @Output(OUTPUT)
    MessageChannel output();
}
