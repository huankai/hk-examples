package com.hk.cache.redis.example;

import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * 数据结构：List
 *
 * @author huangkai
 * @date 2019-02-19 10:23
 */
@Log4j2
@SpringBootTest(classes = {RedisExampleApplication.class})
public class ListTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 从队列左边入队值
     */
    @Test
    public void leftPush() {
        Long result = redisTemplate.opsForList().leftPush("lKey1", "value2");
        log.debug(result);
    }

    /**
     * 当 key 存在时，从队列左边入队值，不存在什么也不做
     */
    @Test
    public void leftPushX() {
        Long result = redisTemplate.opsForList().leftPushIfPresent("lKey2", "value3");
        log.debug(result);
    }

    /**
     * 从 队列左边弹出第一个值，原队列中长度会减去1
     */
    @Test
    public void lPop() {
        String value = redisTemplate.opsForList().leftPop("lKey1");
        log.debug(value);
    }

    /**
     * 获取 队列的长度
     */
    @Test
    public void lLen() {
        log.debug(redisTemplate.opsForList().size("lKey1"));
    }

    /**
     * 获取队列指定索引的值，包头包尾，如果超出原队列的索引值，不会报错
     */
    @Test
    public void lRange() {
        List<String> values = redisTemplate.opsForList().range("lKey1", 0, 11);
        log.debug(values);
    }

    /**
     * 从存于 key 的列表里移除前 count 次出现的值为 value 的元素。 这个 count 参数通过下面几种方式影响这个操作：
     * count > 0: 从头往尾移除值为 value 的元素。
     * count < 0: 从尾往头移除值为 value 的元素。
     * count = 0: 移除所有值为 value 的元素。
     */
    @Test
    public void lRem() {
        String key = "lKey1";
        Long result = redisTemplate.opsForList().remove(key, 1, "value2");
        log.debug(result);

    }

    /**
     * <pre>
     * 更新指定索引的值，索引从 0 开始，如果不存在，报错
     * </pre>
     */
    @Test
    public void lSet() {
        String key = "lKey1";
        redisTemplate.opsForList().set(key, 1, "update");
    }

    /**
     * <pre>
     *
     * 保留指定区间的元素，其它的删除，包头包尾
     * </pre>
     */
    @Test
    public void lTrim() {
        String key = "lKey1";
        redisTemplate.opsForList().trim(key, 0, 1);
    }


    /**
     * 获取 指定索引的元素
     */
    @Test
    public void lIndex() {
        log.debug(redisTemplate.opsForList().index("lKey1", 1));
    }

    /**
     * 在队列右边弹出元素，并删除原队列弹出的元素
     */
    @Test
    public void rPop() {
        String key = "lKey1";
        String value = redisTemplate.opsForList().rightPop(key);
        log.debug(value);
    }

    /**
     * 右边入队一个元素
     */
    @Test
    public void rightPush() {
        String key = "lKey1";
        Long result = redisTemplate.opsForList().rightPush(key, "value5");
        log.debug(result);
    }

    /**
     * 当 key 存在时，从队列右边入队值，不存在什么也不做
     */
    @Test
    public void rightPushX() {
        String key = "lKey1";
        Long result = redisTemplate.opsForList().rightPushIfPresent(key, "value6");
        log.debug(result);
    }

    /**
     * 从 sourceKey 右边 弹出元素放入 destinationKey 左边
     */
    @Test
    public void rightPopAndLeftPush() {
        String sourceKey = "lKey1";
        String destinationKey = "lKey2";
        String result = redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
        System.out.println(result);
    }

}
