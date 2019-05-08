package com.hk.elasticsearch.example.service.impl;

import com.hk.commons.util.StringUtils;
import com.hk.core.data.commons.utils.OrderUtils;
import com.hk.core.elasticsearch.highlight.HighlightTag;
import com.hk.core.elasticsearch.query.Condition;
import com.hk.core.page.QueryPage;
import com.hk.core.page.SimpleQueryPage;
import com.hk.core.query.QueryModel;
import com.hk.elasticsearch.example.entity.Commodity;
import com.hk.elasticsearch.example.repository.elasticsearch.CommodityRepository;
import com.hk.elasticsearch.example.service.CommodityService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-4-11 13:45
 */
@Service
public class CommodityServiceImpl implements CommodityService, HighlightTag {

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public QueryPage<Commodity> findByPage(QueryModel<Commodity> queryModel) {
        Commodity param = queryModel.getParam();
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder().withPageable(PageRequest.of(queryModel.getStartRowIndex(),
                queryModel.getPageSize(), OrderUtils.toSort(queryModel.getOrders())));
        if (param != null) {
            // bool查询，这是个 复合过滤器（compound filter） ，它可以接受多个其他过滤器作为参数，并将这些过滤器结合成各式各样的布尔（逻辑）组合。
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            if (StringUtils.isNotEmpty(param.getCategoryName())) {
//                1、boolQuery.must()  所有的语句都 必须（must） 匹配，与 AND 等价;
//                  boolQuery.mustNot() 所有的语句都 不能（must not） 匹配，与 NOT 等价;
//                  boolQuery.should()至少有一个语句要匹配，与 OR 等价
//                2、QueryBuilders.matchQuery 会将搜索词分词，再与目标查询字段进行匹配，若分词中的任意一个词与目标字段匹配上，则可查询到
                boolQuery.must(QueryBuilders.matchQuery("categoryName", param.getCategoryName()));
            }
            if (StringUtils.isNotEmpty(param.getCategoryId())) {
//                termQuery: 不会对搜索词进行分词处理，而是作为一个整体与目标字段进行匹配，若完全匹配，则可查询到
                boolQuery.must(QueryBuilders.termQuery("categoryId", param.getCategoryId()));
            }
            //过滤
            queryBuilder.withFilter(boolQuery);

            // 查询
            if (StringUtils.isNotEmpty(param.getSubTitle())) {
//                @see: https://blog.csdn.net/u011730199/article/details/82386425
                queryBuilder.withQuery(QueryBuilders.disMaxQuery().tieBreaker(0.3f)
                        .add(QueryBuilders.matchQuery("name", param.getSubTitle()))
                        .add(QueryBuilders.matchQuery("subTitle", param.getSubTitle())));
            }
        } else {
            queryBuilder.withQuery(QueryBuilders.matchAllQuery());
        }
        //高亮查询
        queryBuilder.withHighlightFields(new HighlightBuilder.Field("subTitle")
                .preTags(PRE_TAG).postTags(POST_TAG));
        Page<Commodity> page = commodityRepository.search(queryBuilder.build());
        return new SimpleQueryPage<>(queryModel, page.getContent(), page.getTotalElements());
    }

    @Override
    public Commodity save(Commodity commodity) {
        return commodityRepository.save(commodity);
    }

    @Override
    public Iterable<Commodity> findAll() {
        return commodityRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        commodityRepository.deleteById(id);
    }

    @Override
    public QueryPage<Commodity> findByPage(List<Condition> conditions, int pageIndex, int pageSize) {
        return commodityRepository.findByPage(conditions, pageIndex, pageSize);
    }
}
