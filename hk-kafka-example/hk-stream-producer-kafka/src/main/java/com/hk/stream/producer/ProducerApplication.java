package com.hk.stream.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2019-7-23 16:47
 */
@Slf4j
@SpringBootApplication
@RestController
@EnableBinding(value = {Processor.class, ProducerApplication.SendToEvent.class})
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Autowired
    private Processor processor;

    @RequestMapping("/")
    public String index(String message) {
        boolean result = processor.output().send(MessageBuilder.withPayload(message).setHeader("haha", "hahadf").build());
        log.info(String.valueOf(result));
        return message;
    }

    @StreamListener(Sink.INPUT)
    @SendTo("sendToInput")
    public String input(String message) {
        log.info("input receiveMessage:{}", message);
        return message;
    }

    @StreamListener(SendToEvent.INPUT)
    public void sentToInput(String message) {
        log.info("sentToInput message:{}", message);
    }


    public interface SendToEvent {

        String INPUT = "sendToInput";

        @Input(SendToEvent.INPUT)
        SubscribableChannel input();

    }

}
