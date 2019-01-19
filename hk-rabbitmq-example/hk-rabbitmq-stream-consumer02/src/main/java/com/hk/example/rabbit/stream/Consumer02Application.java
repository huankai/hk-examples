package com.hk.example.rabbit.stream;

import com.hk.commons.JsonResult;
import com.hk.commons.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author huangkai
 * @date 2019-01-17 14:39
 */
@SpringBootApplication
@EnableBinding(value = {Sink.class})
public class Consumer02Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer02Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Consumer02Application.class, args);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageBody {

        private String id;

        private String name;

        private String content;
    }

    @StreamListener(Sink.INPUT)
    public void receive(@Payload JsonResult<MessageBody> message) {
        LOGGER.info("Consumer02Application Receive message : {}", JsonUtils.serialize(message));
    }
}
