package com.hk.mapping.web;

import com.hk.core.ConfigurationStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author: huangkai
 * @date 2018-05-29 20:08
 */
@SpringBootApplication
@EnableScheduling
public class MappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MappingApplication.class, args);
    }

    @Bean
    public ConfigurationStorage configurationStorage() {
        return new WebConfigurationStorage();
    }

    /**
     * 每个整点执行一次
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void clearCache() {
        configurationStorage().clear();
    }
}
