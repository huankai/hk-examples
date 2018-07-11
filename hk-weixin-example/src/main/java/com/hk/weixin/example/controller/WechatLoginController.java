package com.hk.weixin.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.weixin.example.security.config.WechatQrCodeConfig;

import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 
 * @author: kevin
 * @date 2018年2月7日下午1:56:15
 */
@Controller
@RequestMapping("/wechat")
public class WechatLoginController {

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WechatQrCodeConfig config;

	/**
	 * 二维码登陆地址
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/login")
	public void wechatLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String callbackUrl = String.format("%s%s", config.getCallHost(), config.getCallbackUrl());
		String connectUrl = wxMpService.buildQrConnectUrl(callbackUrl, "snsapi_login",
				config.getState());
		response.sendRedirect(connectUrl);
	}
}
