/**
 *
 */
package com.hk.weixin.example.config;

import com.hk.weixin.example.security.config.WechatQrcodeAuthenticationSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 微信二维码配置
 *
 * @author kevin
 * @date 2018年2月8日下午3:07:41
 */
@Order(4)//此order必须小于 父类的order值，否则父类会覆盖子类配置
@Configuration
public class WeixinSecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private WechatQrcodeAuthenticationSecurityConfigurer wechatQrcodeAuthenticationSecurityConfigurer;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**").antMatchers("/wechat/login").antMatchers("/wechat/portal");
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
