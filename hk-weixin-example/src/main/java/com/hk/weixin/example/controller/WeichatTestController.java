package com.hk.weixin.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * weixin 测试类
 * 
 * @author: kevin
 * @date 2017年12月19日下午1:38:04
 */
@RestController
public class WeichatTestController {

	@Autowired
	private WxMpService wxMpService;

	/**
	 * 获取Token
	 * 
	 * @return
	 * @throws WxErrorException
	 */
	@GetMapping("get_token")
	public String getToken() throws WxErrorException {
		return wxMpService.getAccessToken();
	}

	/**
	 * 获取Token
	 * 
	 * @return
	 * @throws WxErrorException
	 */
	@GetMapping("get_menu")
	public Object getMenu() throws WxErrorException {
		return wxMpService.getMenuService().menuGet();
	}

	/**
	 * 获取用户列表信息
	 * 
	 * @return
	 * @throws WxErrorException
	 */
	@GetMapping("getusers")
	public Object getUserList() throws WxErrorException {
		// WxMpUserQuery userQuery = new WxMpUserQuery();
		return wxMpService.getUserService().userList(null);
	}

	/**
	 * 获取用户基本信息(包括UnionID机制)
	 * 
	 * @return
	 * @throws WxErrorException
	 */
	@GetMapping("getuserinfo")
	public Object getUserInfo() throws WxErrorException {
		return wxMpService.getUserService().userInfo("oNvZtv__To1bNI5clj3-oB05OO4U");
	}

	/**
	 * 获取设置的行业信息
	 * 
	 * @return
	 * @throws WxErrorException
	 */
	@GetMapping("get_industry")
	public Object getIndustry() throws WxErrorException {
		return wxMpService.getTemplateMsgService().getIndustry();
	}

	/**
	 * 获取模板列表
	 * 
	 * @return
	 * @throws WxErrorException
	 */
	@GetMapping("get_template")
	public Object getTemplate() throws WxErrorException {
		return wxMpService.getTemplateMsgService().getAllPrivateTemplate();
	}

	/**
	 * 发送模板消息
	 * 
	 * @return
	 * @throws WxErrorException
	 */
	@GetMapping("send_template_message")
	public Object sendTemplateMessage() throws WxErrorException {
		WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser("oNvZtv__To1bNI5clj3-oB05OO4U")
				.url("https://www.baidu.com").templateId("RniEogUuXy0RyTg5zkPaQBl4exR1SQXhKN761H7TSk0")
				.data(Lists.newArrayList(new WxMpTemplateData("first", "testad"), new WxMpTemplateData("spmc", "testtest"),
						new WxMpTemplateData("spPrice", "10000")))
				.build();
		return wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
	}

//	/**
//	 * 
//	 * @return
//	 */
//	@GetMapping("/login")
//	public String login() {
//		String authorizationUrl = wxMpService.oauth2buildAuthorizationUrl("http%3a%2f%2f5984d62b.ngrok.io%2fauth", "snsapi_base", "123");
//		return "redirect:" + authorizationUrl;
//	}

	@GetMapping("/auth")
	public Object auth(String code, String state) throws WxErrorException {
		WxMpOAuth2AccessToken token = wxMpService.oauth2getAccessToken(code);
		WxMpUser userInfo = wxMpService.oauth2getUserInfo(token, "zh_CN");
		return userInfo;
	}
}
