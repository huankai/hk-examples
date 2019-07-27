package com.hk.rabbitmq.security.controller;

import com.hk.commons.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2019-7-25 15:21
 */
@Slf4j
@RestController
public class IndexController {

    @Autowired
    private Processor processor;

    @RequestMapping("/")
    public String index(String message) {
        log.info("index...");
        processor.output().send(MessageBuilder.withPayload(StringUtils.defaultIfEmpty(message, RandomStringUtils.randomNumeric(10))).build());
        return message;
    }
}
