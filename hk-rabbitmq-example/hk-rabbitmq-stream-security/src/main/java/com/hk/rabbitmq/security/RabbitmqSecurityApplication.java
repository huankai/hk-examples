package com.hk.rabbitmq.security;

import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

/**
 * @author kevin
 * @date 2019-7-25 15:15
 */
@Slf4j
@SpringBootApplication
@EnableBinding(Processor.class)
public class RabbitmqSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqSecurityApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    public void receiveMessage(String message) {
        try {
            UserPrincipal principal = SecurityContextUtils.getPrincipal();
            log.info("principal:{}", JsonUtils.serialize(principal));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info(message);
    }
}
