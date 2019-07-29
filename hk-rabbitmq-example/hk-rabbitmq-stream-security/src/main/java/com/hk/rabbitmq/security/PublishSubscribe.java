package com.hk.rabbitmq.security;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author huangkai
 * @date 2019-07-27 22:57
 */
public interface PublishSubscribe {

    String INPUT = "publishSubscribe_input";

    /**
     * @return input channel.
     */
    @Input(PublishSubscribe.INPUT)
    SubscribableChannel input();


    /**
     * Name of the output channel.
     */
    String OUTPUT = "publishSubscribe_output";

    /**
     * @return output channel
     */
    @Output(PublishSubscribe.OUTPUT)
    MessageChannel output();

}
