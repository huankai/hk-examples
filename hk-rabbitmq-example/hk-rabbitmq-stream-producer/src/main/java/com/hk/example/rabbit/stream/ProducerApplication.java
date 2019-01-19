package com.hk.example.rabbit.stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import com.hk.commons.JsonResult;
import com.hk.commons.util.IDGenerator;

import lombok.Builder;
import lombok.Data;

/**
 * @author huangkai
 * @date 2019-01-17 14:27
 */
@SpringBootApplication
@EnableBinding(value = {Source.class})
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Data
    @Builder
    private static class MessageBody {

        private String id;

        private String name;

        private String content;
    }

    /**
     * 发送消息
     *
     * @return
     */
    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT)
    public MessageSource<JsonResult<MessageBody>> send() {
        JsonResult<MessageBody> body = JsonResult.success(MessageBody.builder()
                .id(IDGenerator.STRING_UUID.generate())
                .name("xx")
                .content(RandomStringUtils.randomAlphabetic(10))
                .build());
        return () -> new GenericMessage<>(body);
    }
}
