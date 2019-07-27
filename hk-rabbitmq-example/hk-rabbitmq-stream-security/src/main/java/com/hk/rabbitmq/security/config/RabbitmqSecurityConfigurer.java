package com.hk.rabbitmq.security.config;

import com.hk.commons.util.ByteConstants;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @author kevin
 * @date 2019-7-25 15:16
 */
@Configuration
public class RabbitmqSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }

    private UserDetailsService userDetailsService = username -> new SecurityUserPrincipal(1L, null, null, null, null, username, false,
            username, ByteConstants.ONE, username,
            username, ByteConstants.ONE, null, "admin", ByteConstants.TWO, null, null);

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*.ico");
    }
}
