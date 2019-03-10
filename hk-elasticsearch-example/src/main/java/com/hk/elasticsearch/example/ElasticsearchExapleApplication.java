package com.hk.elasticsearch.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author huangkai
 * @date 2019-03-09 16:08
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = {"**.repository.elasticsearch"})
public class ElasticsearchExapleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchExapleApplication.class, args);
    }
}
