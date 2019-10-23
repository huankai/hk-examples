package com.hk.weixin.cp.config;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangkai
 * @date 2019/3/14 13:52
 */
@Configuration
@EnableConfigurationProperties(value = {WeixinCpProperties.class})
public class WeiXinCpConfiguration {

    private WeixinCpProperties cpProperties;

    public WeiXinCpConfiguration(WeixinCpProperties cpProperties) {
        this.cpProperties = cpProperties;
    }

    @Bean
    public WxCpDefaultConfigImpl configStorage() {
        WxCpDefaultConfigImpl configStorage = new WxCpDefaultConfigImpl();
        configStorage.setCorpId(cpProperties.getCorpId());
        configStorage.setCorpSecret(cpProperties.getCorpSecret());
        configStorage.setAgentId(cpProperties.getAgentId());
        return configStorage;
    }

    @Bean
    public WxCpService cpService() {
        WxCpService wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(configStorage());
        return wxCpService;
    }
}
