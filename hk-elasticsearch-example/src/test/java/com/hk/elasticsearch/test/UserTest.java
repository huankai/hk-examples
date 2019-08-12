package com.hk.elasticsearch.test;

import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import com.hk.elasticsearch.example.ElasticsearchExampleApplication;
import com.hk.elasticsearch.example.entity.User;
import com.hk.elasticsearch.example.repository.elasticsearch.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2019-8-10 12:00
 */
@Slf4j
@SpringBootTest(classes = {ElasticsearchExampleApplication.class})
public class UserTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            users.add(user);
        }
        log.info(JsonUtils.serialize(userRepository.saveAll(users)), true);
    }
}
