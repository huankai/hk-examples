package com.hk.core.authentication.security;

import com.hk.core.authentication.security.accesstoken.TokenUserPrincipal;

import java.util.Optional;

/**
 * @author huangkai
 * @date 2019/3/7 14:13
 */
public class TokenSecurityContextImpl extends SpringSecurityContext implements TokenSecurityContext {

    @Override
    public TokenUserPrincipal getTokenUserPrincipal() {
        return TokenUserPrincipal.class.cast(getPrincipal());
    }

    @Override
    public <T> Optional<T> getSessionAttribute(String key, Class<T> clazz) {
        throw new UnsupportedOperationException("AccessToken Un support getSessionAttribute.");
    }

    @Override
    public <T> Optional<T> consumeSessionAttribute(String key, Class<T> clazz) {
        throw new UnsupportedOperationException("AccessToken Un support getSessionAttributeAndRemove.");
    }

    @Override
    public void setSessionAttribute(String key, Object value, boolean create) {
        throw new UnsupportedOperationException("AccessToken Un support setSessionAttribute.");
    }

    @Override
    public void removeSessionAttribute(String key) {
        throw new UnsupportedOperationException("AccessToken Un support removeSessionAttribute.");

    }
}
