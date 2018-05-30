package com.hk.mapping.web;

import com.google.common.collect.Lists;
import com.hk.config.Configuration;
import com.hk.core.ConfigurationStorage;
import com.hk.core.web.Webs;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: huangkai
 * @date 2018-05-30 16:20
 */
public class WebConfigurationStorage implements ConfigurationStorage {

    private Map<String, Configuration> configurationMap = new ConcurrentHashMap<>();

    @Override
    public Configuration getConfiguration() {
        String sessionId = Webs.getSessionId();
        Configuration configuration = configurationMap.get(sessionId);
        if (null == configuration) {
            configuration = new Configuration();
            setConfiguration(configuration);
        }
        return configuration;
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        configurationMap.put(Webs.getSessionId(), configuration);
    }

    @Override
    public void clear() {
        configurationMap.clear();
    }

    @Override
    public List<String> getAllConfigurationKeyList() {
        return Lists.newArrayList(configurationMap.keySet());
    }
}
