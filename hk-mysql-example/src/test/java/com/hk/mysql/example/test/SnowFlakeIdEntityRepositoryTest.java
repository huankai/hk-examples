package com.hk.mysql.example.test;

import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import com.hk.mysql.examples.domain.SnowFlakeIdEntity;
import com.hk.mysql.examples.repository.jpa.SnowFlakeIdEntityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2019-7-2 17:42
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class SnowFlakeIdEntityRepositoryTest extends BaseTest {

    @Autowired
    private SnowFlakeIdEntityRepository snowFlakeIdEntityRepository;

    @Test
    public void insertTest() {
        int size = 100;
        List<SnowFlakeIdEntity> list = new ArrayList<>(size);
        SnowFlakeIdEntity flakeIdEntity;
        for (int i = 0; i < size; i++) {
            flakeIdEntity = new SnowFlakeIdEntity();
            flakeIdEntity.setName("test" + i);
            list.add(flakeIdEntity);
        }
        System.out.println(JsonUtils.serialize(snowFlakeIdEntityRepository.saveAll(list), true));
    }
}
