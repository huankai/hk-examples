package com.hk.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author kevin
 * @date 2019-7-11 9:30
 */
@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping
    public String index(String message) {
        return restTemplate.getForObject("http://hk-nacos-provider?message=" + message, String.class);
    }
}
