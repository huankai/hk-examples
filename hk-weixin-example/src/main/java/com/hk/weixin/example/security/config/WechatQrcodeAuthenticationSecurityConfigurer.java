/**
 * 
 */
package com.hk.weixin.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hk.weixin.example.security.filter.WechatQrCodeCallbackAuthenticationFilter;
import com.hk.weixin.example.security.provider.WechatQrCodeAuthenticationProvider;

import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 微信二维码配置
 * @author: kevin
 * @date: 2018年2月8日上午11:38:35
 */
@Configuration
public class WechatQrcodeAuthenticationSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {
	
	@Autowired
	private WxMpService wxMpService;
	
	@Autowired
	private WechatQrCodeConfig config;
	
	/**
	 * 此方法是将 WechatQrCodeAuthenticationProvider 注册到spring security的filter 中
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		WechatQrCodeCallbackAuthenticationFilter filter = new WechatQrCodeCallbackAuthenticationFilter(wxMpService,config);
		filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		WechatQrCodeAuthenticationProvider provider = new WechatQrCodeAuthenticationProvider();
		http.authenticationProvider(provider).addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
