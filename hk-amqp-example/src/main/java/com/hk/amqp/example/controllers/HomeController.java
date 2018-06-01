/**
 * 
 */
package com.hk.amqp.example.controllers;

import com.hk.amqp.example.SenderService;
import com.hk.amqp.example.domain.Notice;
import com.hk.commons.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kally
 * @date 2018年3月5日下午2:41:31
 */
@Controller
public class HomeController {
	
	@Autowired
	private SenderService senderService;

	@RequestMapping("/index")
	public String index() {
		return "/index";
	}
	
	@RequestMapping("/send")
	public String send(Notice notice) {
		System.out.println(JsonUtils.toJSONString(notice));
		senderService.send(JsonUtils.toJSONString(notice));
		return "redirect:/index";
	}

}
