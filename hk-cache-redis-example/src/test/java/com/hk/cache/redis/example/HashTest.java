package com.hk.cache.redis.example;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据结构：Map
 *
 * @author huangkai
 * @date 2019-02-19 10:23
 */
@SpringBootTest(classes = {RedisExampleApplication.class})
@Log4j2
public class HashTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 设置值
     */
    @Test
    public void hSet() {
        redisTemplate.opsForHash().put("hkey1", "name", "zhangSan");
    }

    /**
     *
     */
    @Test
    public void hset2() {
        Map<Object, Object> user = new HashMap<>();
        user.put("id", "1");
        user.put("name", "lisi");
        user.put("age", "10");
        redisTemplate.opsForHash().putAll("hkey2", user);
    }

    /**
     * 获取值
     */
    @Test
    public void hGet() {
        log.debug(redisTemplate.opsForHash().get("hkey1", "name"));
    }

    /**
     * 删除指定的 hashKey
     */
    @Test
    public void hDel() {
        log.debug(redisTemplate.opsForHash().delete("hkey1", "name"));
    }

    /**
     * 判断指定的 hashKey 是否存在
     */
    @Test
    public void hExists() {
        log.debug(redisTemplate.opsForHash().hasKey("hkey1", "name"));
    }

    /**
     * 获取 全部 hashKey 和 value
     */
    @Test
    public void hGetAll() {
        String key = "hkey";
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        log.debug(JsonUtils.serialize(entries));

    }

    /**
     * <pre>
     *
     * 指定的 hashKey 增加指定的值，返回增加后的值。
     * 注意: hashKey 的值要能做数学运行才行，如字符串无法做加 1 操作。
     * </pre>
     */
    @Test
    public void hIncrBy() {
        String key = "hkey2";
        log.debug(redisTemplate.opsForHash().increment(key, "age", 1));
    }

    /**
     * <pre>
     *
     * 指定的 hashKey 增加指定的值，返回增加后的值，浮点运算
     * </pre>
     */
    @Test
    public void hIncrByFloat() {
        String key = "hkey2";
        log.debug(redisTemplate.opsForHash().increment(key, "age", 1.8));
    }


    /**
     * 获取 所有的 hashKey 名称
     */
    @Test
    public void hKeys() {
        Set<Object> keys = redisTemplate.opsForHash().keys("hkey2");
        log.debug(keys);
    }

    /**
     * 获取所有字段的数量
     */
    @Test
    public void hLen() {
        String key = "hkey2";
        log.debug(redisTemplate.opsForHash().size(key));
    }

    /**
     * 获取 key 的多个 hashKey 的值
     */
    @Test
    public void hmGet() {
        String key = "hkey2";
        List<Object> values = redisTemplate.opsForHash().multiGet(key, ArrayUtils.asArrayList("id", "age"));
        log.debug(values);
    }

    /**
     * 只有 hashKey (sex) 不存在时才有会设置值
     */
    @Test
    public void hSetNx() {
        Boolean result = redisTemplate.opsForHash().putIfAbsent("hkey2", "sex", "0");
        log.debug(result);
    }

    /**
     * 获取 hash 中所有值
     */
    @Test
    public void hVals() {
        List<Object> values = redisTemplate.opsForHash().values("hkey2");
        log.debug(values);
    }

    /**
     * 迭代元素
     */
    @Test
    public void hScan() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan("hkey2", ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            log.debug("key:{},value:{}", entry.getKey(), entry.getValue());
        }
    }


}
