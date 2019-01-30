package com.hk.example.rabbit.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Date;

/**
 * @author huangkai
 * @date 2019-01-17 14:39
 */
@SpringBootApplication
@EnableBinding(value = {PublishInput.class, RouteKeyInput.class, TopicInput.class})
public class Consumer02Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer02Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Consumer02Application.class, args);
    }

//    @Data
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class MessageBody {
//
//        private String id;
//
//        private String name;
//
//        private String content;
//
//        private LocalDateTime date;
//    }

    @StreamListener(PublishInput.INPUT)
    public void receive(@Payload Date date) {
        LOGGER.info("Consumer02Application Receive message : {}", date);
    }

    @StreamListener(RouteKeyInput.INPUT)
    public void routeKeyReceive(@Payload Date date) {
        LOGGER.info("Consumer02Application routeKeyReceive message : {}", date);
    }

    @StreamListener(TopicInput.INPUT)
    public void topicReceive(@Payload Date date) {
        LOGGER.info("Consumer01Application topicReceive message : {}", date);
    }
}
