package com.hk.springcloud.stream.kafka;

import com.hk.commons.util.JsonUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: sjq-278
 * @date: 2018-11-08 17:08
 */
@SpringBootApplication
@EnableBinding(Sink.class)
public class Consumer02Application {

    private Logger logger = LoggerFactory.getLogger(Consumer02Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Consumer02Application.class, args);
    }

    /**
     * 集合消息
     *
     * @param messages
     */
    @StreamListener(Sink.INPUT)
    public void collectionMessageReceive(@Payload List<MessageVo> messages) {
        logger.info(JsonUtils.serialize(messages, true));
    }

    @Data
    private static class MessageVo {

        private String id;

        private String name;

        private String title;

        private LocalDateTime date;
    }
}
