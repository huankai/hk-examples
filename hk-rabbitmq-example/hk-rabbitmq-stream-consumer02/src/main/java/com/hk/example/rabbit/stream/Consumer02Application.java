package com.hk.example.rabbit.stream;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@SpringBootApplication
@EnableBinding(value = {PublishInput.class, RouteKeyInput.class, TopicInput.class})
public class Consumer02Application {

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
        log.info("Consumer02Application Receive message : {}", date);
    }

    @StreamListener(RouteKeyInput.INPUT)
    public void routeKeyReceive(@Payload Date date) {
        log.info("Consumer02Application routeKeyReceive message : {}", date);
    }

    @StreamListener(TopicInput.INPUT)
    public void topicReceive(@Payload Date date) {
        log.info("Consumer01Application topicReceive message : {}", date);
    }
}
