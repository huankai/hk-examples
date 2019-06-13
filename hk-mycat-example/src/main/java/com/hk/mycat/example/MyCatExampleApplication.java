package com.hk.mycat.example;

import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.mycat.example.entity.User;
import com.hk.mycat.example.mappers.UserMapper;
import com.hk.mycat.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author huangkai
 * @date 2019-06-02 21:45
 */
@SpringBootApplication
public class MyCatExampleApplication {

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
        SpringApplication.run(MyCatExampleApplication.class, args);
    }


    @Configuration
    public class InitTest implements CommandLineRunner {

        @Autowired
        private UserService userService;

        @Autowired
        private UserMapper userMapper;

        @Override
        public void run(String... args) throws InterruptedException {
//            insertUser();

//            UserRoleVo userRoleVo = userMapper.findByUserId("2");
//            System.out.println(JsonUtils.serialize(userRoleVo, true));
            System.out.println("------find->" + JsonUtils.serialize(userService.find(), true));
            for (int i = 0; i < 5; i++) {
                insert2();

            }
//            User user = userService.find();
//            System.out.println("------->" + JsonUtils.serialize(user, true));
//            findById();
        }


        private void insert2() {
            User user = new User();
            user.setPassword("insert");
            user.setUserName("insert");
            user.setBirthday(LocalDate.now());
            user.setSex(1);
            user.setCreateDate(LocalDateTime.now());
            System.out.println(JsonUtils.serialize(userService.insert2(user), true));
        }

        private void findById() throws InterruptedException {
            System.out.println(JsonUtils.serialize(userService.findById("3"), true));
//            System.out.println(JsonUtils.serialize(userRepository.getOne("3"), true));
            Thread.sleep(2000);
            findById();
        }

        private void insertUser() {
            User user = new User();
            user.setPassword("insertxxx");
            user.setUserName("insertxxx");
            user.setBirthday(LocalDate.now());
            user.setSex(1);
            user.setCreateDate(LocalDateTime.now());
            System.out.println(JsonUtils.serialize(userService.insert(user), true));

        }
    }

}
