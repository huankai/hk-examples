package com.hk.rocketmq.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.time.LocalDateTime;

/**
 * @author kevin
 * @date 2019-11-22 17:20
 */
@Slf4j
@SpringBootApplication
@EnableBinding(value = {TestInput.class, TransactionInput.class, TagInput.class, DelayInput.class})
public class RocketConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketConsumerApplication.class, args);
    }

    @StreamListener(TestInput.INPUT)
    public void testInput(String message) {
        log.debug("Receive TestInput Message:{}", message);
    }

    /**
     * 事物消息
     *
     * @param message
     */
    @StreamListener(TransactionInput.INPUT)
    public void transactionInput(String message) {
        log.debug("Receive transactionInput Message:{}", message);
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

    /**
     * 延迟消息
     *
     * @param message
     */
    @StreamListener(DelayInput.INPUT)
    public void delayInput(String message) {
        log.debug("Receive delayInput Message:{},CurrentTime:{}", message, LocalDateTime.now());
    }

}
