package com.hk.elasticsearch.example.controller;

import com.hk.commons.JsonResult;
import com.hk.elasticsearch.example.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-06-27 22:27
 */
@RestController
@RequestMapping("commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("suggest")
    public JsonResult<List<String>> suggest(String keyword){
        return JsonResult.success(commodityService.suggest(keyword));
    }
}
