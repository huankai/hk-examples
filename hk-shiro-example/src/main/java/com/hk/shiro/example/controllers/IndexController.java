package com.hk.shiro.example.controllers;

import com.hk.commons.JsonResult;
import com.hk.core.authentication.api.SecurityContext;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * 用户是否有权限访问
     */
    @RequestMapping("auth")
    @ResponseBody
    @RequiresPermissions(value = {"admin"})
    public JsonResult<Void> auth() {
        return JsonResult.success();
    }

    /**
     * 用户是否有角色访问
     */
    @RequestMapping("role")
    @ResponseBody
    @RequiresRoles(value = {"admin_role"})
    public JsonResult<Void> role() {
        return JsonResult.success();
    }
}
