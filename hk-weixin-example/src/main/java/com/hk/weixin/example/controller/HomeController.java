/**
 * 
 */
package com.hk.weixin.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kally
 * @date 2018年2月5日下午2:40:18
 */
@Controller
public class HomeController {

	/**
	 * 首页
	 * 
	 * @return
	 */
	@GetMapping({"/","/index"})
	public String index(ModelMap modelMap) {
		return "index";
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 回调
	 * 
	 * @return
	 */
	@RequestMapping("/callback")
	public String callback() {

		return null;
	}

}
