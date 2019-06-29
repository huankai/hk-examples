package com.hk.elasticsearch.test;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.core.elasticsearch.query.Condition;
import com.hk.core.elasticsearch.query.SimpleCondition;
import com.hk.core.page.QueryPage;
import com.hk.core.query.Order;
import com.hk.core.query.QueryModel;
import com.hk.core.test.BaseTest;
import com.hk.elasticsearch.example.ElasticsearchExampleApplication;
import com.hk.elasticsearch.example.entity.Commodity;
import com.hk.elasticsearch.example.repository.elasticsearch.CommodityRepository;
import com.hk.elasticsearch.example.service.CommodityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangkai
 */
@SpringBootTest(classes = {ElasticsearchExampleApplication.class})
public class CommodityTest extends BaseTest {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private CommodityRepository commodityRepository;

    @Test
    public void bulkUpdate() {
        Commodity commodity = new Commodity();
        commodity.setId("dcc05813084945aeb7315140c8f2df95");
        commodity.setPrice(4888d);
        commodity.setCategoryId("sddfffd");
        commodityRepository.bulkUpdate(commodity);
    }

    @Test
    public void partialUpdates() {
        List<Commodity> list = new ArrayList<>();
        Commodity commodity = new Commodity();
        commodity.setId("dcc05813084945aeb7315140c8f2df95");
        commodity.setPrice(3888d);
        commodity.setCategoryId("sddfffd");
        list.add(commodity);
        commodity = new Commodity();
        commodity.setId("08dacdec3e4d4158b6de6d5f42705b31");
        commodity.setPrice(7888d);
        commodity.setCategoryId("haha");
        list.add(commodity);
        commodityRepository.bulkUpdate(list);
    }

    /**
     * 查询数据
     */
    @Test
    public void list() {
//        commodityRepository.search(new MatchQueryBuilder("",""));
        Iterable<Commodity> result = commodityService.findAll();
        result.forEach(item -> System.out.println(JsonUtils.serialize(item, true)));
    }

    @Test
    public void criteriaQuery() {
        CriteriaQuery query = new CriteriaQuery(Criteria.where("name").is("苹果"));
        List<Commodity> result = elasticsearchTemplate.queryForList(query, Commodity.class);
        result.forEach(item -> System.out.println(JsonUtils.serialize(item, true)));
    }

    /**
     * 删除
     */
    @Test
    public void delete() {
        commodityService.deleteById("c93417f8a0644fe1a054fbad58f07f60");
    }

    @Test
    public void findByPage() {
        QueryModel<Commodity> queryModel = new QueryModel<>();
        Commodity commodity = new Commodity();
        commodity.setSubTitle("a");
//        queryModel.setPageSize(1);
        queryModel.addOrders(Order.desc("price"));
        queryModel.setParam(commodity);
        QueryPage<Commodity> page = commodityService.queryForPage(queryModel);
        System.out.println(JsonUtils.serialize(page, true));
    }

    @Test
    public void findByPage2() {
        List<Condition> conditions = ArrayUtils.asArrayList(new SimpleCondition("price", Criteria.OperationKey.GREATER, 5300));
        QueryPage<Commodity> page = commodityService.findByPage(conditions, 0, 10);
        System.out.println(JsonUtils.serialize(page, true));
    }

}
