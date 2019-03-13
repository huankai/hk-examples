package com.hk.core.authentication.security.accesstoken;

import com.hk.commons.util.StringUtils;
import lombok.Setter;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 使用缓存存储 Token
 *
 * @author huangkai
 * @date 2019/3/5 15:49
 */
public class CacheAccessTokenContext implements AccessTokenContext {

    @Setter
    private Cache cache = new ConcurrentMapCache("AccessTokenContext");

    @Override
    public Optional<TokenUserPrincipal> getToken(String token) {
        return Optional.ofNullable(cache.get(token, TokenUserPrincipal.class));
    }

    @Override
    public void removeAccessToken(String token) {
        cache.evict(token);
    }

    @Override
    public void storeToken(TokenUserPrincipal token) {
        getToken(token.getToken()).ifPresent(item -> {
            if (!StringUtils.equals(item.getAccount(), token.getAccount())) {
                throw new RuntimeException("Token 已存在"); // 一般不会出现这种情况，请确保每次生成的 Token 值不同。
            }
        });
        cache.put(token.getToken(), token);
    }

    @Override
    public void refreshToken(String token, Duration duration) {
        TokenUserPrincipal tokenUserPrincipal = getToken(token)
                .orElseThrow(() -> new RuntimeException("不存在的Token"));
        tokenUserPrincipal.setExpire(LocalDateTime.now().plus(duration));
        cache.put(token, tokenUserPrincipal);
    }

}
