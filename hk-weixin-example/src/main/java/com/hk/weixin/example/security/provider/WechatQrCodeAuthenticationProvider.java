/**
 * 
 */
package com.hk.weixin.example.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.hk.weixin.example.security.token.WechatQrCodeAuthenticationToken;

/**
 * 微信二维码认证提供者
 * @author kally
 * @date 2018年2月8日上午11:25:39
 */
public class WechatQrCodeAuthenticationProvider implements AuthenticationProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		/*
		 * 这里可以根据authentication.getPrincipal() 返回的对象去数据库查询用户信息、权限等
		 */
		return new WechatQrCodeAuthenticationToken(authentication.getPrincipal(), null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AuthenticationProvider#supports(
	 * java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return WechatQrCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
