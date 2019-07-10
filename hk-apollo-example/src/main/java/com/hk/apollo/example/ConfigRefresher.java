package com.hk.apollo.example;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author kevin
 * @date 2019-7-10 14:29
 */
@Slf4j
@Component
public class ConfigRefresher implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @ApolloConfig
    private Config config;

    @PostConstruct
    private void init() {
        refresher(config.getPropertyNames());
    }

    @ApolloConfigChangeListener
    private void onChange(ConfigChangeEvent changeEvent) {
        refresher(changeEvent.changedKeys());
    }

    private void refresher(Set<String> changedKeys) {
        for (String changedKey : changedKeys) {
            log.debug("this key is changed:{}", changedKey);
        }
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changedKeys));
    }
}
