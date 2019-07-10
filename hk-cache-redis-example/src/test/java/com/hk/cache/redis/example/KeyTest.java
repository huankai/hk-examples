package com.hk.cache.redis.example;

import com.hk.commons.util.Contants;
import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author huangkai
 * @date 2019-02-19 10:23
 */
@Log4j2
@SpringBootTest(classes = {RedisExampleApplication.class})
public class KeyTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * <pre>
     *
     * 获取给定模式的key，生产环境严格禁用此命令，会导致 redis 锁住，
     * 同时还有 config / flushdb /flushall 这些不安全的命令，应该在
     * redis.conf 配置文件中使用
     *  rename-command KEYS ""
     *  rename-command KEYS ""
     *  rename-command KEYS ""
     *  rename-command KEYS "" 取消
     * </pre>
     */
    @Test
    public void keys() {
        Set<String> keys = redisTemplate.keys("*");
        log.debug("keys: {}", JsonUtils.serialize(keys));
    }

    /**
     * 序列化给定的key
     */
    @Test
    public void dump() {
        byte[] keyByte = redisTemplate.dump("BaseCode::id4ce3abf76f1a45528605d5a611b8a693");
        log.debug("dump:{}", new String(keyByte, Contants.CHARSET_UTF_8));
    }

    /**
     * 是否存在指定的 key
     */
    @Test
    public void exists() {
        log.debug("exists: {}", redisTemplate.hasKey("BaseCode::id4ce3abf76f1a45528605d5a611b8a693"));
    }

    /**
     * 设置一个key 的过期时间,如果key 不存在，什么也不做
     */
    @Test
    public void expire() {
        String key = "key1";
//        redisTemplate.opsForValue().set(key, "value1");
        log.debug(redisTemplate.expire(key, 1, TimeUnit.MINUTES));// key 不存在时，返回 false
    }

    /**
     * 将指定的 key 移动到另一个数据库中，如果 key 不存在，什么也不做
     */
    @Test
    public void move() {
        String key = "key1";
        redisTemplate.opsForValue().set(key, "value1");
        log.debug(redisTemplate.move(key, 1));
    }

    /**
     * 返回一个随机的key
     */
    @Test
    public void randomKey() {
        log.debug("randomKey:{}", redisTemplate.randomKey());
    }

    /**
     * 获取 key 的过期时间，-1 表示永不过期
     */
    @Test
    public void getExpire() {
        log.debug(redisTemplate.getExpire("key1"));
    }

    /**
     * 重命名 key ，如果 oldKey 不存在，报错
     * http://www.redis.cn/commands/rename.html
     */
    @Test
    public void rename() {
        String oldKey = "key1";
        redisTemplate.opsForValue().set(oldKey, "value1");
        redisTemplate.rename(oldKey, "key2");
    }

    /**
     * 重命名 key ，如果 oldKey 不存在，报错；和 rename 不同的是，只有 newKey 不存在时才会重命名
     */
    @Test
    public void renameIfAbsent() {
        String oldKey = "key1";
        String newKey = "key2";
        redisTemplate.opsForValue().set(oldKey, "value1");
        redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 获取 key 的数据类型
     */
    @Test
    public void type() {
        log.debug(redisTemplate.type("key1"));
    }

    /**
     * 增量迭代一个集合元素
     */
    @Test
    public void scan() {
        Cursor<Map.Entry<Object, Object>> entryCursor = redisTemplate.opsForHash().scan("key1", ScanOptions.NONE);
        while (entryCursor.hasNext()) {
            Map.Entry<Object, Object> entry = entryCursor.next();
            log.debug("key:{},value:{}", entry.getKey(), entry.getValue());
        }
    }


}
