package com.hk.core.authentication.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import com.hk.core.authentication.security.accesstoken.AccessTokenAuthenticationSecurityConfigurer;

import java.time.Duration;

/**
 * @author huangkai
 * @date 2019/3/7 13:53
 */
@Data
@ConfigurationProperties(prefix = "hk.authentication.token")
public class TokenProperties {

    /**
     * token 过期时间，默认为 2 小时
     */
    private Duration expire = Duration.ofHours(2);

    /**
     * <pre>
     *
     * 认证类型: bearer | basic ...
     * 默认值 {@link AccessTokenAuthenticationSecurityConfigurer#BEARER}
     * </pre>
     */
    private String authorizationType;

    /**
     * <pre>
     * 从请求头中的某个字段中获取 token
     * 默认值 {@link AccessTokenAuthenticationSecurityConfigurer#AUTHORIZATION}
     *
     * </pre>
     */
    private String tokenAuthorizationHeader;

    /**
     * <pre>
     * 从请求参数中获取 token
     * 默认值 {@link AccessTokenAuthenticationSecurityConfigurer#TOKEN_PARAMETER}
     * </pre>
     */
    private String tokenParameter;
}
