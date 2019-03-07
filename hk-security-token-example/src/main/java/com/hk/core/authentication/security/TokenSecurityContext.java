package com.hk.core.authentication.security;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.security.accesstoken.TokenUserPrincipal;


/**
 * token 安全上下文
 *
 * @author huangkai
 * @date 2019/3/7 13:46
 */
public interface TokenSecurityContext extends SecurityContext {

    /**
     * 获取 当前登陆用户 token
     */
    default String getToken() {
        return getTokenUserPrincipal().getToken();
    }

    TokenUserPrincipal getTokenUserPrincipal();

}
