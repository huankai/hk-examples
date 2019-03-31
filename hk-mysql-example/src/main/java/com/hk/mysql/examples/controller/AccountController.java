package com.hk.mysql.examples.controller;

import com.hk.core.service.exception.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author huangkai
 * @date 2019-03-28 22:30
 */
@Controller
public class AccountController {

    @RequestMapping("aaa")
    public String index(@RequestParam boolean flag) {
        if (flag) {
            throw new ServiceException("exception");
        }
        return "index";
    }
}
