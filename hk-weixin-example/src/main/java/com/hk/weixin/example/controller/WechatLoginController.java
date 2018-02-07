package com.hk.weixin.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.commons.util.StringUtils;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;


/**
 * 
 * @author kally
 * @date 2018年2月7日下午1:56:15
 */
@Controller
@RequestMapping("/wechat")
public class WechatLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(WechatLoginController.class);

	@Autowired
	private WxMpService wxMpService;
	
	@GetMapping("/login")
	public void wechatLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String connectUrl = wxMpService.buildQrConnectUrl("http://15cd1912.ngrok.io/wechat/callback", "snsapi_login", "3d6be0a4685d839573b04816624a415e");
		response.sendRedirect(connectUrl);
	}
	
	@GetMapping("/callback")
	public String callBack(String code,String state) throws WxErrorException {
		if(StringUtils.isNotEmpty(code)) {
			//用户同意授权
			WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(code);
			WxMpUser mpUser = wxMpService.oauth2getUserInfo(accessToken,null);
			logger.debug("用户信息: {}", JsonUtils.toJSONString(mpUser));
			SecurityContextHolder.getContext().setAuthentication(new AbstractAuthenticationToken(null) {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 9185048744053984861L;

				@Override
				public Object getCredentials() {
					return accessToken.getAccessToken();
				}

				@Override
				public Object getPrincipal() {
					return mpUser.getNickname();
				}
			});
		}
		return "redirect:/";
	}
}
