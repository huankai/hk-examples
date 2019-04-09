package com.hk.elasticsearch.test;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.IDGenerator;
import com.hk.commons.util.JsonUtils;
import com.hk.core.elasticsearch.query.Condition;
import com.hk.core.elasticsearch.query.SimpleCondition;
import com.hk.core.page.QueryPage;
import com.hk.core.query.Order;
import com.hk.core.query.QueryModel;
import com.hk.core.test.BaseTest;
import com.hk.elasticsearch.example.ElasticsearchExampleApplication;
import com.hk.elasticsearch.example.entity.Commodity;
import com.hk.elasticsearch.example.entity.CommodityFile;
import com.hk.elasticsearch.example.repository.elasticsearch.CommodityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangkai
 */
@SpringBootTest(classes = {ElasticsearchExampleApplication.class})
public class CommodityTest extends BaseTest {

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 添加数据
     */
    @Test
    public void addCommodity() {
        Commodity commodity = new Commodity();
        commodity.setId(IDGenerator.STRING_UUID.generate());
        commodity.setName("苹果7 Plus");
        commodity.setPrice(BigDecimal.valueOf(5688));
        List<CommodityFile> files = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CommodityFile file = new CommodityFile();
            file.setFileName("fileName" + i);
            file.setFilePath("filePath" + i);
            file.setId(IDGenerator.STRING_UUID.generate());
            files.add(file);
        }
        commodity.setFiles(files);
        commodityRepository.save(commodity);
    }

    /**
     * 查询数据
     */
    @Test
    public void list() {
//        commodityRepository.search(new MatchQueryBuilder("",""));
        Iterable<Commodity> result = commodityRepository.findAll();
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
        commodityRepository.deleteById("c93417f8a0644fe1a054fbad58f07f60");
    }

    @Test
    public void findByPage() {
        QueryModel<Commodity> queryModel = new QueryModel<>();
        Commodity commodity = new Commodity();
        commodity.setName("苹果");
//        queryModel.setPageSize(1);
        queryModel.addOrders(Order.desc("price"));
        queryModel.setParam(commodity);
        QueryPage<Commodity> page = commodityRepository.findByPage(queryModel);
        System.out.println(JsonUtils.serialize(page, true));
    }

    @Test
    public void findByPage2() {
        List<Condition> conditions = ArrayUtils.asArrayList(new SimpleCondition("price", Criteria.OperationKey.GREATER, 5300));
        QueryPage<Commodity> page = commodityRepository.findByPage(conditions, 0, 10);
        System.out.println(JsonUtils.serialize(page, true));
    }

}
