package com.hk.util;

import com.hk.commons.util.SnowflakeIdGenerator;

/**
 * @author kevin
 * @date 2019-7-9 16:28
 */
public class SnowflakeIdTest {

    public static void main(String[] args) {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1, 1);
        System.out.println(snowflakeIdGenerator.generate());
    }
}
