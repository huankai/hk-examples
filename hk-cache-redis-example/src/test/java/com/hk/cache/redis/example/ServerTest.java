package com.hk.cache.redis.example;

import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author huangkai
 * @date 2019-02-19 10:23
 */
@SpringBootTest(classes = {RedisExampleApplication.class})
@Log4j2
public class ServerTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取给定模式的key
     */
    @Test
    public void clientList() {
        log.debug("keys: {}", JsonUtils.serialize(redisTemplate.getClientList(), true));
    }

    /**
     * kill 指定 的客户端
     */
    @Test
    public void killClient() {
        CollectionUtils.getFirstOrDefault(redisTemplate.getClientList())
                .ifPresent(client -> {
                    String addressPort = client.getAddressPort();
                    String[] arr = addressPort.split(":");
                    redisTemplate.killClient(arr[0], Integer.parseInt(arr[1]));
                });
    }

}
