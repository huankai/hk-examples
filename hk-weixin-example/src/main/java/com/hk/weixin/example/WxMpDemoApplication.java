package com.hk.weixin.example;

import com.hk.commons.util.ByteConstants;
import com.hk.core.authentication.api.ClientAppInfo;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.authentication.security.UserDetailClientService;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author kevin
 * @date 2017年12月19日上午10:57:48
 */
@SpringBootApplication
public class WxMpDemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WxMpDemoApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailClientService() {
            @Override
            public SecurityUserPrincipal loadUserByLoginUsername(String username) {
                return new SecurityUserPrincipal(1L, null, null, null, null, username, false,
                        username, ByteConstants.ONE, username,
                        username, ByteConstants.ONE, null, "$2a$10$KgOArE6QpbY2iTQC0WGGS.hP72PQsHpToqbNVEEmUrd5LcEqrbzAG", ByteConstants.TWO, null, null);
            }

            @Override
            public ClientAppInfo getClientInfoById(Long  clientId) {
                return null;
            }
        };
    }

}
