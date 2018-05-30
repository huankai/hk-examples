package com.hk.core;

import com.hk.config.Configuration;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-30 15:27
 */
public interface ConfigurationStorage {

    /**
     * @return
     */
    Configuration getConfiguration();

    /**
     * @param configuration
     */
    void setConfiguration(Configuration configuration);

    void clear();

    List<String> getAllConfigurationKeyList();


}
