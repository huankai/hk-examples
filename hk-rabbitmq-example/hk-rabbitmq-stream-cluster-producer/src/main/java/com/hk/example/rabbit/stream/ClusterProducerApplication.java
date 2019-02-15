package com.hk.example.rabbit.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.Date;

/**
 * @author huangkai
 * @date 2019-02-14 09:39
 */
@SpringBootApplication
@EnableBinding(value = {ClusterPublishOutput.class})
public class ClusterProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClusterProducerApplication.class, args);
    }

    /**
     * 发布、订阅模式 发送消息
     */
    @Bean
    @InboundChannelAdapter(value = ClusterPublishOutput.OUTPUT, poller = @Poller(fixedRate = "4000", maxMessagesPerPoll = "1"))
    public MessageSource<Date> send() {
        return () -> new GenericMessage<>(new Date());
    }
}
