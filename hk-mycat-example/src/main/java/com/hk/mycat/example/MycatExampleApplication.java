package com.hk.mycat.example;

import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.mycat.example.entity.User;
import com.hk.mycat.example.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author huangkai
 * @date 2019-06-02 21:45
 */
@SpringBootApplication
public class MycatExampleApplication {

    @Bean
    public SecurityContext securityContext() {
        return new SecurityContext() {
            @Override
            public UserPrincipal getPrincipal() {
                throw new UnsupportedOperationException("不支持认证");
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MycatExampleApplication.class, args);
    }


    @Configuration
    public class InitTest implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;

        @Override
        public void run(String... args) {
            Optional<User> optional = userRepository.findById("3");
            System.out.println(JsonUtils.serialize(optional, true));
            System.out.println("---------------");
//            insertUser();
        }

        private void insertUser() {
            User user = new User();
            user.setPassword("insert");
            user.setUserName("insert");
            user.setBrithday(LocalDate.now());
            user.setSex(1);
            user.setCreateDate(LocalDateTime.now());
            System.out.println(JsonUtils.serialize(userRepository.save(user), true));

        }
    }

}
