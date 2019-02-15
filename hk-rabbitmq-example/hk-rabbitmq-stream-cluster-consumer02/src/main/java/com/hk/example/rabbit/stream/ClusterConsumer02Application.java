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
 * @date 2019-02-14 09:46
 */
@SpringBootApplication
@EnableBinding(value = {ClusterPublishInput.class})
public class ClusterConsumer02Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterConsumer02Application.class);

    public static void main(String[] args) {
        SpringApplication.run(ClusterConsumer02Application.class, args);
    }


    @StreamListener(ClusterPublishInput.INPUT)
    public void receive(@Payload Date date) {
        LOGGER.info("ClusterConsumer02Application Receive message : {}", date);
    }
}
