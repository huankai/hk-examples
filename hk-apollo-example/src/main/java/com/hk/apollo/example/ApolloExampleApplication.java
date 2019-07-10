package com.hk.apollo.example;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kevin
 * @date 2019-7-10 14:12
 */
@EnableApolloConfig
@SpringBootApplication
public class ApolloExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloExampleApplication.class, args);
    }
}
