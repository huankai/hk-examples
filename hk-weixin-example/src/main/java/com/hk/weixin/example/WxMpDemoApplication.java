package com.hk.weixin.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hk.weixin.example.config.UserDetailServcieImpl;
import com.hk.weixin.example.repository.UserRepository;

/**
 * 
 * @author huangkai
 * @date 2017年12月19日上午10:57:48
 */
@ServletComponentScan(basePackages = { "com.hk.core" })
@SpringBootApplication
public class WxMpDemoApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WxMpDemoApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

	@Autowired
	private UserRepository userRepository;

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetailServcieImpl userDetailServcie = new UserDetailServcieImpl();
		userDetailServcie.setUserRepository(userRepository);
		return userDetailServcie;
	}

}
