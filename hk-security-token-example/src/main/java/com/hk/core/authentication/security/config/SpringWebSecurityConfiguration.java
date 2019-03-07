package com.hk.core.authentication.security.config;

import com.hk.core.authentication.security.TokenSecurityContext;
import com.hk.core.authentication.security.TokenSecurityContextImpl;
import com.hk.core.authentication.security.accesstoken.AccessTokenAuthenticationSecurityConfigurer;
import com.hk.core.authentication.security.accesstoken.AccessTokenContext;
import com.hk.core.authentication.security.accesstoken.InMemoryAccessTokenContext;
import com.hk.core.authentication.service.UserDetailServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author huangkai
 * @date 2019/3/7 13:51
 */
@EnableWebMvc
@Configuration
@EnableConfigurationProperties(value = {TokenProperties.class})
public class SpringWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private TokenProperties tokenProperties;

    public SpringWebSecurityConfiguration(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    @Bean
    public TokenSecurityContext tokenSecurityContext() {
        return new TokenSecurityContextImpl();
    }

    @Bean
    public AccessTokenContext tokenContext() {
        return new InMemoryAccessTokenContext();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl(tokenProperties.getExpire());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(new AccessTokenAuthenticationSecurityConfigurer(tokenContext(), tokenProperties.getTokenAuthorizationHeader(),
                tokenProperties.getAuthorizationType(), tokenProperties.getTokenParameter()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
