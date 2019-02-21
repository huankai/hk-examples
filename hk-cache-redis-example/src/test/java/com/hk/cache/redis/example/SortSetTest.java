package com.hk.cache.redis.example;

import com.hk.commons.util.ArrayUtils;
import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;

import java.util.Set;

/**
 * 数据结构：SortSet ,有序 Set集合
 *
 * @author huangkai
 * @date 2019-02-19 10:23
 */
@Log4j2
@SpringBootTest(classes = {RedisExampleApplication.class})
public class SortSetTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 添加值
     */
    @Test
    public void zAdd() {
        String key = "zKey1";
        Boolean result = redisTemplate.opsForZSet().add(key, "value1", 1d);
        log.debug(result);
        Long result2 = redisTemplate.opsForZSet().add(key, ArrayUtils.asHashSet(new DefaultTypedTuple<>("value2", 0D)));
        log.debug(result2);
    }

    /**
     * 获取集合中元素数量
     */
    @Test
    public void zCard() {
        String key = "zKey1";
        Long result = redisTemplate.opsForZSet().size(key);
        log.debug(result);
    }

    /**
     * 获取指定区间中的元素个数,包头包尾
     */
    @Test
    public void zCount() {
        String key1 = "zKey1";
        Long result = redisTemplate.opsForZSet().count(key1, 0D, 1D);
        log.debug(result);
    }

    /**
     * 将 key 的值的 score 添加指定的值
     */
    @Test
    public void zIncrBy() {
        String key = "zKey1";
        redisTemplate.opsForZSet().incrementScore(key, "value1", 3D);
    }

    /**
     * 获取队列的交集，并将结果放入第三个队列中
     */
    @Test
    public void zInterByStore() {
        String key1 = "zKey1", key2 = "zKey2", key3 = "zKey3";
        Long result = redisTemplate.opsForZSet().intersectAndStore(key1, key2, key3);
        log.debug(result);
    }

    /**
     * 根据指定的索引返回数据
     */
    @Test
    public void zRange() {
        String key = "zKey1";
        Set<String> result = redisTemplate.opsForZSet().range(key, 0, 1);
        log.debug(result);

    }

    /**
     * 从指定范围中获取结果集,正序排序
     */
    @Test
    public void zRangeByLex() {
        String key = "zKey1";
        // 假设 zKey1 中有数据 value2 与 value1,获取 大于等于 value2元素
        Set<String> result = redisTemplate.opsForZSet().rangeByLex(key, RedisZSetCommands.Range.range().gte("[value2"));// 要以 [ 开头,且 [ 与后面的不能有空格
        log.debug(result);
        // 分页获取结果集
        Set<String> resultPage = redisTemplate.opsForZSet().rangeByLex(key, RedisZSetCommands.Range.range().gte("[value2"),
                RedisZSetCommands.Limit.limit().count(10).offset(0));
        log.debug(resultPage);
    }

    /**
     * <pre>
     *
     * 指定成员区间内的元素,反序排序
     * </pre>
     */
    @Test
    public void zRevRangeByLex() {
        String key = "zKey1";
        Set<String> result = redisTemplate.opsForZSet().reverseRange(key, 0, 1);
        log.debug(result);
    }


    /**
     * 移动集合中的元素到另一个集合中
     */
    @Test
    public void zRangeByScore() {
        String key = "zKey1";
        Set<String> result = redisTemplate.opsForZSet().rangeByScore(key, 0D, 1D);
        log.debug(result);
    }

    /**
     * 获取集合中的元素所在的索引,如果元素值不存在,返回 Null
     */
    @Test
    public void zRank() {
        String key = "zKey1";
        Long value = redisTemplate.opsForZSet().rank(key, "value");
        log.debug(value);
    }

    /**
     * 从集合删除集合中的元素
     */
    @Test
    public void zRemove() {
        String key = "zKey1";
        Long result = redisTemplate.opsForZSet().remove(key, "value0", "value1");
        log.debug(result);
    }

    /**
     * 从集合中指定范围的元素
     */
    @Test
    public void zRemoveRangeByLen() {
        String key = "zKey1";
        Long result = redisTemplate.opsForZSet().removeRange(key, 5, 10);
        log.debug(result);
    }

    /**
     * 删除 Source 在指定区间的元素
     */
    @Test
    public void zRemoveRangeByScore() {
        String key = "zKey1";
        Long result = redisTemplate.opsForZSet().removeRangeByScore(key, 5D, 10D);
        log.debug(result);
    }

    /**
     * 获取指定范围的元素,反序排序
     */
    @Test
    public void zRevRange() {
        String key = "zKey1";
        Set<String> result = redisTemplate.opsForZSet().reverseRange(key, 0, 10);
        log.debug(result);
    }

    /**
     * 获取集合中指定 元素 的 score 值
     */
    @Test
    public void zScore() {
        String key = "zKey1";
        Double value = redisTemplate.opsForZSet().score(key, "value");
        log.debug(value);
    }

    /**
     * 多个集合元素并集,并将结果保存到第三个集合中
     */
    @Test
    public void zUnionStore() {
        String key1 = "zKey1", key2 = "zKey2", key3 = "zKey3";
        Long result = redisTemplate.opsForZSet().unionAndStore(key1, key2, key3);
        log.debug(result);
    }

    /**
     * 遍历
     */
    @Test
    public void zScan() {
        String key = "zKey1";
        Cursor<ZSetOperations.TypedTuple<String>> cursor = redisTemplate.opsForZSet().scan(key, ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<String> next = cursor.next();
            log.debug("value:{},score:{}", next.getValue(), next.getScore());
        }
    }

}
