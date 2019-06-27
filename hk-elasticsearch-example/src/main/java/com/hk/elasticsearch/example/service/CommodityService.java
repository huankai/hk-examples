package com.hk.elasticsearch.example.service;

import com.hk.core.elasticsearch.query.Condition;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.elasticsearch.example.entity.Commodity;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-4-11 13:45
 */
public interface CommodityService {

    QueryPage<Commodity> findByPage(QueryModel<Commodity> queryModel);


    Commodity save(Commodity commodity);

    Iterable<Commodity> findAll();

    void deleteById(String id);

    List<String> suggest(String keyword);

    QueryPage<Commodity> findByPage(List<Condition> conditions, int pageIndex, int pageSize);
}
