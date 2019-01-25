package com.hk.security.cas.example.controller;

import com.hk.commons.JsonResult;
import com.hk.core.authentication.api.SecurityContextUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangkai
 * @date 2019-01-25 17:10
 */
@RestController
public class IndexController {

    @GetMapping(path = {"/", "/index"})
    public JsonResult<Object> index() {
        return JsonResult.success(SecurityContextUtils.getPrincipal());
    }
}
