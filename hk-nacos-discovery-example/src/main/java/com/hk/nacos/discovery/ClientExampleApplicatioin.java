package com.hk.nacos.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author huangkai
 * @date 2019-07-10 21:57
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ClientExampleApplicatioin {

    public static void main(String[] args) {
        SpringApplication.run(ClientExampleApplicatioin.class, args);
    }
}
