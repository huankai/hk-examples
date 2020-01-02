package com.hk.live.config;

import com.hk.core.authentication.security.expression.AdminAccessWebSecurityExpressionHandler;
import com.hk.core.authentication.security.handler.logout.EquipmentLogoutHandler;
import com.hk.core.autoconfigure.authentication.AuthenticationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@EnableWebMvc
@Configuration
@EnableConfigurationProperties(value = {AuthenticationProperties.class})
public class SecurityExampleConfigurer extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties properties;

    public SecurityExampleConfigurer(AuthenticationProperties properties) {
        this.properties = properties;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.LoginProperties login = properties.getLogin();
        http
                .csrf().disable()
                .formLogin()
                .loginPage(login.getLoginUrl()).permitAll() // 登陆 请求地址不需要认证可以访问，配置在这里
                .usernameParameter(login.getUsernameParameter())
                .passwordParameter(login.getPasswordParameter())
                .loginProcessingUrl(login.getLoginProcessingUrl())
                .and()
                .logout().clearAuthentication(true)
                .logoutUrl(login.getLogoutUrl())
                .invalidateHttpSession(true)
                .addLogoutHandler(new SecurityContextLogoutHandler())
                .addLogoutHandler(new EquipmentLogoutHandler(login.getLogoutSuccessUrl()))
                .and()
                .authorizeRequests().expressionHandler(new AdminAccessWebSecurityExpressionHandler())// admin 角色的用户、admin权限、保护的用户拥有所有访问权限
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/error", "/favicon.ico");
    }
}
