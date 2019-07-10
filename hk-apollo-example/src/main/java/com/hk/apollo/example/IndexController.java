package com.hk.apollo.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2019-7-10 15:47
 */
@Slf4j
@RestController
public class IndexController {

    @RequestMapping
    public String index() {
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
        return "ok";
    }
}
