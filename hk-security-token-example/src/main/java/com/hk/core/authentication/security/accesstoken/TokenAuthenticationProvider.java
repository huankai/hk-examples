package com.hk.core.authentication.security.accesstoken;

import com.hk.core.authentication.security.SecurityUserPrincipal;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author huangkai
 * @date 2019/3/6 17:30
 */
public abstract class TokenAuthenticationProvider {

    /**
     * token 过期时间
     */
    @Getter
    private final Duration expire;

    /**
     * Token 生成
     */
    @Setter
    @Getter
    private TokenKeyGenerate tokenKeyGenerate;

    protected TokenAuthenticationProvider(Duration expire) {
        this(TokenKeyGenerate.base64Token, expire);
    }

    protected TokenAuthenticationProvider(TokenKeyGenerate tokenKeyGenerate, Duration expire) {
        this.expire = expire;
        this.tokenKeyGenerate = tokenKeyGenerate;
    }

    /**
     * 转换为 {@link TokenUserPrincipal} 对象，生成 token 并设置有效期
     *
     * @param userPrincipal {@link SecurityUserPrincipal}
     * @return {@link TokenUserPrincipal}
     */
    protected final TokenUserPrincipal convertToTokenUserPrincipal(SecurityUserPrincipal userPrincipal) {
        return new TokenUserPrincipal(tokenKeyGenerate.generate(), LocalDateTime.now().plus(expire), userPrincipal);
    }
}
