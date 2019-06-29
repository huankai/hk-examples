package com.hk.elasticsearch.example.config;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UnsupportedSecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangkai
 * @date 2019-06-27 22:28
 */
@Configuration
public class ElasticsearchExampleConfiguration {

    @Bean
    public SecurityContext securityContext() {
        return new UnsupportedSecurityContext();
    }

}
