package com.hk.weixin.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 
 * @author huangkai
 * @date 2017年12月19日上午10:57:48
 */
@ServletComponentScan(basePackages = { "com.hk.core" })
@SpringBootApplication
public class WxMpDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxMpDemoApplication.class, args);
	}
}
