package com.hk.weixin.cp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huangkai
 * @date 2019/3/14 13:48
 */
@Data
@ConfigurationProperties(prefix = "wechat.cp")
public class WeixinCpProperties {

    private String corpId;

    private String corpSecret;

    private Integer agentId;

}
