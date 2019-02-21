package com.hk.cache.redis.example;

import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

/**
 * 数据结构：Set，不能有重复值
 *
 * @author huangkai
 * @date 2019-02-19 10:23
 */
@Log4j2
@SpringBootTest(classes = {RedisExampleApplication.class})
public class SetTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 添加值
     */
    @Test
    public void sAdd() {
        String key = "sKey1";
        Long result = redisTemplate.opsForSet().add(key, "value1", "value2");
        log.debug(result);
    }

    /**
     * 获取集合中元素数量
     */
    @Test
    public void sCard() {
        String key = "sKey1";
        Long result = redisTemplate.opsForSet().size(key);
        log.debug(result);
    }

    /**
     * 比较多个队列不同的值
     */
    @Test
    public void sDiff() {
        String key1 = "sKey1", key2 = "sKey2";
        Set<String> diff = redisTemplate.opsForSet().difference(key1, key2);
        log.debug(diff);
    }

    /**
     * 比较队列的同值，并将结果放入第三个队列中
     */
    @Test
    public void sDiffStore() {
        String key1 = "sKey1", key2 = "sKey2", key3 = "sKey3";
        Long result = redisTemplate.opsForSet().differenceAndStore(key1, key2, key3);
        log.debug(result);
    }

    /**
     * 获取队列的交集
     */
    @Test
    public void sInter() {
        String key1 = "sKey1", key2 = "sKey2";
        Set<String> result = redisTemplate.opsForSet().intersect(key1, key2);
        log.debug(result);
    }

    /**
     * 获取队列的交集，并将结果放入第三个队列中
     */
    @Test
    public void sInterStore() {
        String key1 = "sKey1", key2 = "sKey2", key3 = "sKey3";
        Long result = redisTemplate.opsForSet().intersectAndStore(key1, key2, key3);
        log.debug(result);

    }

    /**
     * 值 是否是队列中的元素
     */
    @Test
    public void sisMember() {
        String key = "sKey1";
        Boolean result = redisTemplate.opsForSet().isMember(key, "value1");
        log.debug(result);
    }

    /**
     * <pre>
     *
     * 获取队列中的元素
     * </pre>
     */
    @Test
    public void sMembers() {
        String key = "sKey1";
        Set<String> members = redisTemplate.opsForSet().members(key);
        log.debug(members);
    }


    /**
     * 移动集合中的元素到另一个集合中
     */
    @Test
    public void sMove() {
        String key1 = "sKey1", key2 = "sKey2";
        Boolean result = redisTemplate.opsForSet().move(key1, "value1", key2);
        log.debug(result);
    }

    /**
     * 删除并获取集合中的元素
     */
    @Test
    public void sPop() {
        String key = "sKey1";
        String value = redisTemplate.opsForSet().pop(key);
//        String value = redisTemplate.opsForSet().pop(key, 2); //删除并获取集合中的多个元素
        log.debug(value);
    }

    /**
     * 从集合中随机获取一个元素,不会删除集合中的元素
     */
    @Test
    public void sRandMember() {
        String key = "sKey2";
        String result = redisTemplate.opsForSet().randomMember(key);
        log.debug(result);
    }

    /**
     * 从集合中删除一个或多个元素
     */
    @Test
    public void sREM() {
        String key = "sKey2";
        Long result = redisTemplate.opsForSet().remove(key, "value2", "value3");
        log.debug(result);
    }

    /**
     * 多个集合元素并集
     */
    @Test
    public void sUnion() {
        String key1 = "skey1", key2 = "sKey2";
        Set<String> result = redisTemplate.opsForSet().union(key1, key2);
        log.debug(result);
    }

    /**
     * 多个集合元素并集,并将结果保存到第三个集合中
     */
    @Test
    public void sUnionStore() {
        String key1 = "skey1", key2 = "sKey2", key3 = "sKey4";
        Long result = redisTemplate.opsForSet().unionAndStore(key1, key2, key3);
        log.debug(result);
    }

    /**
     * 遍历元素
     */
    @Test
    public void sScan() {
        String key = "sKey3";
        Cursor<String> cursor = redisTemplate.opsForSet().scan(key, ScanOptions.NONE);
        while (cursor.hasNext()) {
            String value = cursor.next();
            log.debug(value);
        }
    }

}
