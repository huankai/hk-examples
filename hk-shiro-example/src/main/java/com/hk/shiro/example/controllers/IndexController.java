package com.hk.shiro.example.controllers;

import com.hk.core.authentication.api.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangkai
 * @date 2018/12/24 23:32
 */
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private SecurityContext securityContext;

    /**
     * login view
     *
     * @return login view
     */
    @GetMapping(value = {"/login"})
    public String login() {
        return securityContext.isAuthenticated() ? "redirect:/" : "login";
    }
}
