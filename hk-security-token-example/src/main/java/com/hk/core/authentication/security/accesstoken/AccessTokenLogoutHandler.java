package com.hk.core.authentication.security.accesstoken;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用 token 退出登陆处理器，退出成功后，删除 token
 *
 * @author huangkai
 * @date 2019/3/6 16:26
 */
public class AccessTokenLogoutHandler implements LogoutHandler {

    private final AccessTokenContext tokenContext;

    private final String authorizationHeader;

    private final String tokenParameter;

    private final String authorizationType;

    public AccessTokenLogoutHandler(AccessTokenContext tokenContext, String authorizationHeader, String authorizationType, String tokenParameter) {
        this.tokenContext = tokenContext;
        this.authorizationHeader = authorizationHeader;
        this.tokenParameter = tokenParameter;
        this.authorizationType = authorizationType;
    }


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = TokenUtils.getAccessToken(request, authorizationHeader, authorizationType, tokenParameter);
        tokenContext.removeAccessToken(token);
    }
}
