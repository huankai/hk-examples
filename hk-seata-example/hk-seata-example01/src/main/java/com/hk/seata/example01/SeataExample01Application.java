package com.hk.seata.example01;

import com.hk.commons.util.ByteConstants;
import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author kevin
 * @date 2019-8-1 9:36
 */
@SpringBootApplication
public class SeataExample01Application {

    public static void main(String[] args) {
        SpringApplication.run(SeataExample01Application.class, args);
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
                return false;
            }
        };
    }

}
