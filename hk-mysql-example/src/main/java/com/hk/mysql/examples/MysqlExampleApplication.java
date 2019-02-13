package com.hk.mysql.examples;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author sjq-278
 * @date 2018-12-12 10:27
 */
@SpringBootApplication
public class MysqlExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlExampleApplication.class);
    }

    @Bean
    public SecurityContext securityContext() {
        return new SecurityContext() {
            @Override
            public UserPrincipal getPrincipal() {
                return new UserPrincipal();
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }
        };
    }


}
