package com.hk.rocketmq.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author kevin
 * @date 2019-11-22 17:20
 */
@Slf4j
@SpringBootApplication
@EnableBinding(value = {TagInput.class})
public class RocketConsumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(RocketConsumer2Application.class, args);
    }

    /**
     * 消费指定 Tag 的消息
     *
     * @param message
     */
    @StreamListener(TagInput.INPUT)
    public void tagInput(String message) {
        log.debug("Receive tagInput Message:{}", message);
    }

}
