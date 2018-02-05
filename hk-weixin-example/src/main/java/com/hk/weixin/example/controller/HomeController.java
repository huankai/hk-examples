/**
 * 
 */
package com.hk.weixin.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.core.authentication.api.SecurityContext;

/**
 * @author kally
 * @date 2018年2月5日下午2:40:18
 */
@Controller
public class HomeController {

	@Autowired
	private SecurityContext securityContext;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		if(securityContext.isAuthenticated()) {
			return "redirect:/";
		}
		return "login";
	}
	
}
