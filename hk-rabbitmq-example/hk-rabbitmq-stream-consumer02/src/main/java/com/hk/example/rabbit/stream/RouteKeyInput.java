package com.hk.example.rabbit.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 发布、订阅模式，消息消费者01
 *
 * @author huangkai
 * @date 2019-01-30 10:41
 */
public interface RouteKeyInput {

    String INPUT = "routekey_input";

    @Input(INPUT)
    SubscribableChannel input();

}
