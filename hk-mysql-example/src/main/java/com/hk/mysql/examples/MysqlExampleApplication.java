package com.hk.mysql.examples;

import com.hk.commons.util.ByteConstants;
import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.mysql.examples.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sjq-278
 * @date 2018-12-12 10:27
 */
@SpringBootApplication
@RestController
public class MysqlExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlExampleApplication.class);
    }

    @Autowired
    private AccountService accountService;

    @RequestMapping("/")
    public String index() {
        System.out.println(accountService.getOne("1"));
        return "success";
    }

    @Bean
    public SecurityContext securityContext() {
        return new SecurityContext() {
            @Override
            public UserPrincipal getPrincipal() {
                return new UserPrincipal("1", "1", ByteConstants.ZERO);
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }
        };
    }


}
