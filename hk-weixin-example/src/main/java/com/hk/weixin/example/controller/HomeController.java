/**
 *
 */
package com.hk.weixin.example.controller;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
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
    public String index(ModelMap modelMap) {
        return "index";
    }

    /**
     * @return
     */
    @GetMapping("/login")
    public String login(ModelMap modelMap, HttpServletRequest request) throws WxErrorException {
        WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(request.getRequestURL().toString());
        modelMap.put("jsapiTicket", jsapiSignature);
        return "login";
    }

    /**
     * 回调
     *
     * @return
     */
    @RequestMapping("/callback")
    public String callback() {

        return null;
    }

}
