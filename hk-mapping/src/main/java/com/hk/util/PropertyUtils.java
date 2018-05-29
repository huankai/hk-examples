package com.hk.util;

import com.hk.commons.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: huangkai
 * @date 2018-05-29 11:35
 */
public class PropertyUtils {

    private static final String CONFIG_PROPERTIES = "config.properties";

    private static final Map<String, String> CONFIG = new HashMap<>();

    static {
        init();
    }

    public static String get(String key) {
        return CONFIG.get(key);
    }

    public static String getOrDefault(String key, String defaultValue) {
        String value = CONFIG.get(key);
        return StringUtils.isEmpty(value) ? defaultValue : value;
    }

    public static void put(String key, String value) {
        CONFIG.put(key, value);
    }

    private static void init() {
        Properties properties = new Properties();
        try (InputStream in = ClassLoader.getSystemResourceAsStream(CONFIG_PROPERTIES)) {
            if (null != in) {
                properties.load(in);
                properties.forEach((key, value) -> CONFIG.put(String.valueOf(key), String.valueOf(value)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
