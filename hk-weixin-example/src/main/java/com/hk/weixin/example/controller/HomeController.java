/**
 *
 */
package com.hk.weixin.example.controller;

import com.hk.commons.util.StringUtils;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kevin
 * @date 2018年2月5日下午2:40:18
 */
@Controller
public class HomeController {

    @Autowired
    private WxMpService wxMpService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * @return
     */
    @GetMapping("/login")
    public String login(ModelMap modelMap, HttpServletRequest request) throws WxErrorException {
        StringBuffer url = request.getRequestURL();
        String queryString = request.getQueryString();
        if (StringUtils.isNotEmpty(queryString)) {
            url.append("?").append(StringUtils.substringBefore(queryString, "#"));
        }
        WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(url.toString());
        modelMap.put("jsapiTicket", jsapiSignature);
        return "login";
    }

    /**
     * 回调
     *
     */
    @RequestMapping("/callback")
    public String callback() {
        return null;
    }

}
