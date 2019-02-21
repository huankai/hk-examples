package com.hk.cache.redis.example;

import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * 地理位置定位
 *
 * @author huangkai
 * @date 2019-02-19 10:23
 */
@Log4j2
@SpringBootTest(classes = {RedisExampleApplication.class})
public class GeoTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 添加地理位置
     */
    @Test
    public void geoAdd() {
        String key = "geoKey1";
        Long result = redisTemplate.opsForGeo().add(key, new Point(105.174977, 31.560872), "阳光新城");
        log.debug(result);
    }

    /**
     * 获取标记地理位置的 hash 字符串
     */
    @Test
    public void geoHash() {
        String key = "geoKey1";
        List<String> result = redisTemplate.opsForGeo().hash(key, "世纪城地铁站");
        log.debug(result);
    }

    /**
     * 返回经纬度
     */
    @Test
    public void geoOps() {
        String key = "geoKey1";
        List<Point> points = redisTemplate.opsForGeo().position(key, "世纪城地铁站");
        log.debug(JsonUtils.serialize(points));
    }

    /**
     * 返回两个位置的距离
     */
    @Test
    public void geoDist() {
        String key = "geoKey1";
        Distance result = redisTemplate.opsForGeo().distance(key, "世纪城地铁站", "阳光新城");
        log.debug(JsonUtils.serialize(result, true));
    }

    @Test
    public void geoRadius() {
        String key = "geoKey1";
        GeoResults<RedisGeoCommands.GeoLocation<String>> result = redisTemplate.opsForGeo().radius(key, new Circle(new Point(0d, 1d), 100));
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> item : result) {
            log.debug(item.getContent());
            log.debug(item.getDistance());
        }

    }

    @Test
    public void geoRadiusByMember() {
        String key = "geoKey1";
        GeoResults<RedisGeoCommands.GeoLocation<String>> result = redisTemplate.opsForGeo().radius(key, "世纪城地铁站", 100);
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> item : result) {
            log.debug(item.getContent());
            log.debug(item.getDistance());
        }

    }

}
