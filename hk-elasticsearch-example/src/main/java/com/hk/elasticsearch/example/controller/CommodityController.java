package com.hk.elasticsearch.example.controller;

import com.hk.commons.JsonResult;
import com.hk.elasticsearch.example.entity.Commodity;
import com.hk.elasticsearch.example.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangkai
 * @date 2019-06-27 22:27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("commodity")
public class CommodityController {

    private final CommodityService commodityService;

//    @RequestMapping("suggest")
//    public JsonResult<List<String>> suggest(String keyword) {
//        return JsonResult.success(commodityService.suggest("suggest", keyword));
//    }

    @RequestMapping
    public JsonResult<Commodity> get(String id) {
        return JsonResult.success(commodityService.findById(id).orElse(null));
    }
}

