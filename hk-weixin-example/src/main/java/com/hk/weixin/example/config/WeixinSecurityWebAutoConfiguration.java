/**
 *
 */
package com.hk.weixin.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import com.hk.core.authentication.security.config.SecurityWebAutoConfiguration;
import com.hk.weixin.example.security.config.WechatQrcodeAuthenticationSecurityConfigurer;

/**
 * 微信二维码配置
 *
 * @author kally
 * @date 2018年2月8日下午3:07:41
 */
@Order(4)//此order必须小于 父类的order值，否则父类会覆盖子类配置
@Configuration
public class WeixinSecurityWebAutoConfiguration extends SecurityWebAutoConfiguration {

    @Autowired
    private WechatQrcodeAuthenticationSecurityConfigurer wechatQrcodeAuthenticationSecurityConfigurer;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);//必须调用super定义的配置
        web.ignoring().antMatchers("/wechat/login").antMatchers("/wechat/portal");
    }

    /**
     * 将配置信息添加到 HttpSecurity 对象中
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);//必须调用super定义的配置
        http.apply(wechatQrcodeAuthenticationSecurityConfigurer);
    }

}
