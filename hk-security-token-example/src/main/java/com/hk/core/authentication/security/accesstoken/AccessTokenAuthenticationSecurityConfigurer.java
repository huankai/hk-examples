package com.hk.core.authentication.security.accesstoken;

import com.hk.commons.util.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.Duration;

/**
 * @author huangkai
 * @date 2019/3/5 16:11
 */
public class AccessTokenAuthenticationSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private static final String AUTHORIZATION = "Authorization";

    private static final String TOKEN_PARAMETER = "access_token";

    private static final String BEARER = "bearer";

    private final AccessTokenContext accessTokenContext;

    private final String authorizationHeader;

    private final String tokenParameter;

    private final String authorizationType;

    private final Duration duration;

    public AccessTokenAuthenticationSecurityConfigurer(AccessTokenContext accessTokenContext, String authorizationHeader, String authorizationType,
                                                       String tokenParameter, Duration duration) {
        this.accessTokenContext = accessTokenContext;
        this.authorizationHeader = StringUtils.defaultIfEmpty(authorizationHeader, AUTHORIZATION);
        this.authorizationType = StringUtils.defaultIfEmpty(authorizationType, BEARER);
        this.tokenParameter = StringUtils.defaultIfEmpty(tokenParameter, TOKEN_PARAMETER);
        this.duration = duration;
    }

    @Override
    public void configure(HttpSecurity builder) {
        AccessTokenAuthenticationFilter filter = new AccessTokenAuthenticationFilter(authorizationHeader, authorizationType, tokenParameter);
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        AccessTokenAuthenticationProvider provider = new AccessTokenAuthenticationProvider(accessTokenContext, duration);
        builder.authenticationProvider(provider).addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
