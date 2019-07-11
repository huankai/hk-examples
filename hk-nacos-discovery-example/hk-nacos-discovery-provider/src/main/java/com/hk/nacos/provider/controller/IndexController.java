package com.hk.nacos.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2019-7-11 9:28
 */
@RestController
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping
    public String index(String message) {
        LOGGER.debug("index....");
        LOGGER.info("index....");
        LOGGER.warn("index....");
        LOGGER.error("index....");
        return "Hello " + message;
    }
}
