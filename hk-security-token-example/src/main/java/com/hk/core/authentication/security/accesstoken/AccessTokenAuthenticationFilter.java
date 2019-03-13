package com.hk.core.authentication.security.accesstoken;

import com.hk.commons.JsonResult;
import com.hk.commons.util.StringUtils;
import com.hk.core.web.Webs;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * token 认证过滤器
 * <p>
 * 请求头中添加了 {@link #AUTHORIZATION} 或 请求参数中和添加了 {@link #TOKEN_PARAMETER} 时会验证
 *
 * @author huangkai
 * @date 2019/3/5 15:06
 */
public class AccessTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String authorizationHeader;

    private final String tokenParameter;

    private final String authorizationType;

    public AccessTokenAuthenticationFilter(String authorizationHeader, String authorizationType, String tokenParameter) {
        super(new TokenRequestMatcher(authorizationHeader, authorizationType, tokenParameter));
        this.authorizationHeader = authorizationHeader;
        this.tokenParameter = tokenParameter;
        this.authorizationType = authorizationType;
        setAuthenticationSuccessHandler((request, response, authentication) -> request.getRequestDispatcher(request.getRequestURI()).forward(request, response));
        setAuthenticationFailureHandler((request, response, exception) -> Webs.writeJson(response, HttpServletResponse.SC_OK, JsonResult.error(exception.getMessage())));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String token = TokenUtils.getAccessToken(request, authorizationHeader, authorizationType, tokenParameter);
        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationServiceException("token 认证失败");
        }
        AccessTokenAuthenticationToken authenticationToken = new AccessTokenAuthenticationToken(token);
        setDetails(request, authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    private void setDetails(HttpServletRequest request,
                            AccessTokenAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
