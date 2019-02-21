package com.hk.cache.redis.example;

import com.hk.core.redis.locks.RedisLock;
import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.locks.Lock;

/**
 * redis 事物
 *
 * @author huangkai
 * @date 2019-02-19 10:23
 */
@Log4j2
@SpringBootTest(classes = {RedisExampleApplication.class})
public class TransactionTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 丢弃所有 MULTI 之后的命令
     */
    @Test
    public void discard() {
    }

    /**
     * 执行所有 MULTI 之后的命令
     */
    @Test
    public void exec() {
    }

    /**
     * 标记一个事物开始
     */
    @Test
    public void multi() {
    }

    /**
     * 取消事物
     */
    @Test
    public void unWatch() {
    }

    /**
     * 锁定 Key 直到执行了 MULTI/EXEC
     */
    @Test
    public void watch() {

    }


}
