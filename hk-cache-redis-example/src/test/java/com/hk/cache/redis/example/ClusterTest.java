package com.hk.cache.redis.example;

import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * redis 集群测试
 *
 * @author huangkai
 * @date 2019-02-21 17:27
 */
@Log4j2
@SpringBootTest(classes = {RedisExampleApplication.class})
public class ClusterTest extends BaseTest {


}
