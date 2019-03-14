package com.hk.weixin.cp;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangkai
 * @date 2019/3/14 13:27
 */
@SpringBootApplication
public class WeixinCpExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinCpExampleApplication.class, args);
    }


    @Configuration
    public static class InitRunner implements CommandLineRunner {

        @Autowired
        private WxCpService cpService;

        @Override
        public void run(String... args) throws Exception {
            WxCpMessage message = WxCpMessage.TEXT().content("测试消息").toUser("kevin").build();
            cpService.messageSend(message);
        }
    }

}
