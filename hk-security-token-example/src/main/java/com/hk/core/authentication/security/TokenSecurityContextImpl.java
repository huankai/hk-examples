package com.hk.core.authentication.security;

import com.hk.core.authentication.security.accesstoken.TokenUserPrincipal;

/**
 * @author huangkai
 * @date 2019/3/7 14:13
 */
public class TokenSecurityContextImpl extends SpringSecurityContext implements TokenSecurityContext {

    @Override
    public TokenUserPrincipal getTokenUserPrincipal() {
        return TokenUserPrincipal.class.cast(getPrincipal());
    }
}
