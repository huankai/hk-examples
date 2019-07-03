package com.hk.cache.redis.example;

import com.hk.commons.util.ByteConstants;
import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author huangkai
 * @date 2019-02-19 10:19
 */
@SpringBootApplication
public class RedisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisExampleApplication.class, args);
    }

    @Bean
    public SecurityContext securityContext() {
        return new SecurityContext() {
            @Override
            public UserPrincipal getPrincipal() {
                return new UserPrincipal(1L, "1", ByteConstants.ZERO);
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }
        };
    }
}
