package com.hk.cache.redis.example;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author huangkai
 * @date 2019-02-19 10:23
 */
@SpringBootTest(classes = {RedisExampleApplication.class})
@Log4j2
public class StringTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 设置值
     */
    @Test
    public void set() {
        redisTemplate.opsForValue().set("key2", "value2");
    }

    /**
     * 设置 Key 值，并给定过期时间，无论 Key 是否存在
     */
    @Test
    public void set2() {
        redisTemplate.opsForValue().set("key1", "value1", 1, TimeUnit.MINUTES);
    }

    /**
     * 在指定的 key 后面添加内容，如果 key 不存在 ，会执行  set 操作。
     */
    @Test
    public void append() {
        log.debug(redisTemplate.opsForValue().append("key3", "-append"));
    }

    /**
     *
     */
    @Test
    public void bitField() {
        List<Long> longs = redisTemplate.opsForValue().bitField("key1", BitFieldSubCommands.create());
        log.debug("bitField:{}", JsonUtils.serialize(longs));
    }

    /**
     * 给定的key 做减法运算，如果 key 不存在报错；返回计算后的结果
     */
    @Test
    public void decrement() {
        String key = "key3";
//        log.debug(redisTemplate.opsForValue().decrement(key));

        log.debug(redisTemplate.opsForValue().decrement(key, -5)); //减去指定的值，如果第二个参数为负数，则做加法运算
    }

    /**
     * 获取指定的 key 的值，如果 key 不存在， 返回 Null
     */
    @Test
    public void get() {
        String key = "key3";
        log.debug(redisTemplate.opsForValue().get(key));
    }

    /**
     *
     */
    @Test
    public void getBit() {
        String key = "key1";
        log.debug("getBit:{}", redisTemplate.opsForValue().getBit(key, 2));
    }


    /**
     * 获取 key 指定范围的值，从 0 开始，包括头也包括尾部
     */
    @Test
    public void getRange() {
        log.debug(redisTemplate.opsForValue().get("key1", 0, 3));
    }

    /**
     * 设置新值，并返回旧值，如果 Key  不存在，也 执行 set 操作，此时没有旧值，返回 null.
     */
    @Test
    public void getSet() {
        String key = "key8";
        log.debug(redisTemplate.opsForValue().getAndSet(key, "newValue"));
    }

    /**
     * 在指定的 key 上执行 加法操作，如果 key 不存在报错；如果 key 不是 Number 类型也报错；返回计算后的结果
     */
    @Test
    public void incr() {
        String key = "key3";
        log.debug(redisTemplate.opsForValue().increment(key)); // 加1
//        log.debug(redisTemplate.opsForValue().increment(key, 5));//加指定的值
    }

    /**
     * 获取 多个 key 的的值
     */
    @Test
    public void mget() {
        log.debug(redisTemplate.opsForValue().multiGet(ArrayUtils.asArrayList("key1", "key2")));
    }

    /**
     * 设置多个 key，如果 key 存在 ，会覆盖旧值
     */
    @Test
    public void mset() {
        Map<String, String> keyValue = new HashMap<>();
        keyValue.put("key1", "value1");
        keyValue.put("key7", "value7");
        redisTemplate.opsForValue().multiSet(keyValue);
    }

    /**
     * 同时设置多个 key，必须所有的 key 都存在时才会执行成功
     */
    @Test
    public void msetnx() {
        Map<String, String> keyValue = new HashMap<>();
        keyValue.put("key1", "newValue1");
        keyValue.put("key7", "newValue7");
        keyValue.put("key9", "value9"); // key9 不存在
        log.debug(redisTemplate.opsForValue().multiSetIfAbsent(keyValue));
    }

    /**
     *
     */
    @Test
    public void setBit() {
        log.debug(redisTemplate.opsForValue().setBit("key1", 1, false));

    }

    /**
     * 只有 key 不存在时，才能执行成功
     */
    @Test
    public void setNx() {
        log.debug(redisTemplate.opsForValue().setIfAbsent("key1", "value2")); //vvale1
    }

    /**
     * 从指定位置开始设置 key的值
     */
    @Test
    public void setRange() {
        redisTemplate.opsForValue().set("key1", "val", 1);
    }

    /**
     * 获取指定 key 的值的长度
     */
    @Test
    public void strLen() {
        log.debug(redisTemplate.opsForValue().size("key1"));
    }

}
