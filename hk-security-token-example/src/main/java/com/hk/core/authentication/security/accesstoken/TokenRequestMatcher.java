package com.hk.core.authentication.security.accesstoken;

import com.hk.commons.util.StringUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huangkai
 * @date 2019/3/5 16:26
 */
public class TokenRequestMatcher implements RequestMatcher {

    private final String authorizationHeader;

    private final String tokenParameter;

    private final String authorizationType;

    public TokenRequestMatcher(String authorizationHeader, String tokenParameter, String authorizationType) {
        this.authorizationHeader = authorizationHeader;
        this.tokenParameter = tokenParameter;
        this.authorizationType = authorizationType;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return StringUtils.isNotEmpty(TokenUtils.getAccessToken(request, authorizationHeader, authorizationType, tokenParameter));
    }
}
