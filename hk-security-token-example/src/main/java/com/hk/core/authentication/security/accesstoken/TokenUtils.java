package com.hk.core.authentication.security.accesstoken;


import com.hk.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huangkai
 * @date 2019/3/5 17:03
 */
abstract class TokenUtils {

    /**
     * @param request             request
     * @param authorizationHeader 认证头 Authorization
     * @param authorizationType   认证类型 bearer | basic
     * @param tokenParameter      token 请求参数名
     * @return
     */
    static String getAccessToken(HttpServletRequest request, String authorizationHeader,
                                 String authorizationType, String tokenParameter) {
        String token = request.getHeader(authorizationHeader);
        if (StringUtils.isNotEmpty(token)) {
            token = StringUtils.trimToEmpty(StringUtils.substringAfter(token, authorizationType));
        } else {
            token = request.getParameter(tokenParameter);
        }
        return token;
    }

}
