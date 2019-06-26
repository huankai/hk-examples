package com.hk.security.example.config;

import com.hk.commons.JsonResult;
import com.hk.core.authentication.security.expression.AdminAccessWebSecurityExpressionHandler;
import com.hk.core.authentication.security.handler.logout.EquipmentLogoutHandler;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import com.hk.core.web.Webs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author huangkai
 * @date 2018-12-25 16:43
 */
@EnableWebMvc
@Configuration
@EnableConfigurationProperties(value = {AuthenticationProperties.class})
public class SecurityExampleConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties properties;

    public SecurityExampleConfiguration(AuthenticationProperties properties) {
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


    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.LoginProperties browser = properties.getLogin();
        http
                .csrf().disable()

                .formLogin()

                .loginPage(browser.getLoginUrl()).permitAll() // 登陆 请求地址不需要认证可以访问，配置在这里
                .usernameParameter(browser.getUsernameParameter())
                .passwordParameter(browser.getPasswordParameter())
                .loginProcessingUrl(browser.getLoginProcessingUrl())
                .and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(browser.getLoginUrl()) {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                if (Webs.isAndroid(request) || Webs.isIPhone(request)) {
                    Webs.writeJson(response, 200, JsonResult.unauthorized("用户未认证"));
                } else {
                    super.commence(request, response, authException);
                }
            }
        })
                .and()
//                .rememberMe().disable()//禁用remember-me功能
                .sessionManagement()
                .sessionAuthenticationStrategy(new CompositeSessionAuthenticationStrategy(new ArrayList<>()))
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .enableSessionUrlRewriting(false)
                .invalidSessionStrategy((request, response) -> {
                    if (Webs.isAndroid(request) || Webs.isIPhone(request)) {
                        Webs.writeJson(response, 200, JsonResult.unauthorized("用户未认证"));
                    } else {
                        request.getSession();
                        response.sendRedirect(browser.getLoginUrl());
                    }
                })
                .maximumSessions(browser.getMaximumSessions())
                .sessionRegistry(sessionRegistry())
                .maxSessionsPreventsLogin(browser.isMaxSessionsPreventsLogin())
                .and()
                .and()
                .logout().clearAuthentication(true)
                .logoutUrl(browser.getLogoutUrl())
                .invalidateHttpSession(true)
                .addLogoutHandler(new SecurityContextLogoutHandler())
                .addLogoutHandler(new EquipmentLogoutHandler(browser.getLogoutSuccessUrl()))
                .and()
                .authorizeRequests().expressionHandler(new AdminAccessWebSecurityExpressionHandler())// admin 角色的用户、admin权限、保护的用户拥有所有访问权限
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/error", "/favicon.ico");
    }
}
