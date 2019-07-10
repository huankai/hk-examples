package com.hk.elasticsearch.example.entity;

import com.hk.core.elasticsearch.analyzer.IKanalyzer;
import com.hk.core.elasticsearch.domain.AbstractIDPersistable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.completion.Completion;

import java.io.Serializable;
import java.util.List;


/**
 * <pre>
 * {@link Document} 注解参数说明：
 * indexName: 索引名称，
 * type: 索引类型
 * replicas: 分片数，设置后不能更改，因为  ES 底层是使用 分片数 hash 取模，如果更改了此参数，会导致数据查询不到的问题
 * shards: 每个主分片的副本数，默认为1，此值可以修改，对于单台服务器部署的 ES 是没有副本数的，因为 ES 要求 主分片与副本不能在同一台节点上。
 * </pre>
 *
 * @author huangkai
 * @date 2019-03-09 16:10
 */
@Data
@Document(indexName = "xls-commodity2", refreshInterval = "-1")
@EqualsAndHashCode(callSuper = true)
public class Commodity extends AbstractIDPersistable {

    /**
     * 厂家id
     * fielddata: 默认为 false,当为 false 时，无法进行统计分析
     * analyzer: 分词器
     * searchAnalyzer: 搜索分词器
     * type: 字段类型
     * format: 格式
     */
    @Field(type = FieldType.Keyword)
    private String brandId;

    /**
     * 商品名称
     */
    @Field(analyzer = IKanalyzer.IK_MAX_WORD_ANALYZER,
            searchAnalyzer = IKanalyzer.IK_MAX_WORD_ANALYZER, type = FieldType.Text)
    private String name;

    /**
     * 品牌商品
     */
    @Field(fielddata = true, analyzer = IKanalyzer.IK_MAX_WORD_ANALYZER,
            searchAnalyzer = IKanalyzer.IK_MAX_WORD_ANALYZER, type = FieldType.Text)
    private String brandName;

    /**
     * 品牌商家编号
     */
    @Field(type = FieldType.Keyword)
    private String mchtCode;

    /**
     * 商品类别id
     */
    @Field(type = FieldType.Keyword)
    private String categoryId;

    /**
     * 最少购买限制
     */
    @Field(type = FieldType.Integer)
    private Integer minimum;

    /**
     * 商品类别名称
     */
    @Field(fielddata = true, analyzer = IKanalyzer.IK_MAX_WORD_ANALYZER,
            searchAnalyzer = IKanalyzer.IK_MAX_WORD_ANALYZER, type = FieldType.Text)
    private String categoryName;

    /**
     * 商品货号
     */
    @Field(type = FieldType.Keyword)
    private String commoditySn;

    /**
     * 是否新品
     */
    @Field(type = FieldType.Boolean)
    private Boolean newStatus;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createDate;

    /**
     * 是否店家推荐
     */
    @Field(type = FieldType.Boolean)
    private Boolean ownerStatus;

    /**
     * 是否人气商品
     */
    @Field(type = FieldType.Boolean)
    private Boolean popularStatus;

    /**
     * 是否上架
     */
    @Field(type = FieldType.Boolean)
    private Boolean publishStatus;

    @Field(analyzer = IKanalyzer.IK_MAX_WORD_ANALYZER,
            searchAnalyzer = IKanalyzer.IK_MAX_WORD_ANALYZER, type = FieldType.Text)
    private String subTitle;

    @Field(type = FieldType.Integer)
    private Integer sale;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Double)
    private Double originalPrice;

    @Field(type = FieldType.Double)
    private Double promotionPrice;

    @Field(type = FieldType.Integer)
    private Integer stock;

    @Field(type = FieldType.Keyword)
    private String unit;

    @Field(type = FieldType.Keyword)
    private String weight;

    @Field(type = FieldType.Keyword)
    private String mainPic;

    @Field(type = FieldType.Keyword)
    private String detailTitle;

    @Field(type = FieldType.Keyword)
    private String detailContent;

//    @Field(type = FieldType.Nested)
//    private List<IntegralUseLimited> integralLimitList;

    /**
     * 商品图片地址
     */
    private List<String> mediaList;

    /**
     * 详情图
     */
    private List<String> detailPics;

    /**
     * 商品服务
     */
    @Field(type = FieldType.Nested)
    private List<CommodityServer> serverList;

    /**
     * 商品库存信息
     */
    @Field(type = FieldType.Nested)
    private List<CommoditySKUStore> storeList;

    /**
     * 商品买一送
     */
    @Field(type = FieldType.Nested)
    private List<CommodityGive> giveList;

    /**
     * 商品参数
     */
    @Field(type = FieldType.Nested)
    private List<CommodityParameter> parameterList;

    /**
     * 商品规格
     */
    @Field(type = FieldType.Nested)
    private List<CommoditySpecification> attributeList;

    @CompletionField(maxInputLength = 100, preservePositionIncrements = false,
            searchAnalyzer = IKanalyzer.IK_MAX_WORD_ANALYZER, analyzer = IKanalyzer.IK_MAX_WORD_ANALYZER)
    private Completion suggest;

    public Completion getSuggest() {
        if (null == suggest) {
            suggest = new Completion(new String[]{getSubTitle()});
        }
        return suggest;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommodityParameter implements Serializable {

        @Field(type = FieldType.Keyword)
        private String name;

        @Field(type = FieldType.Keyword)
        private String value;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommoditySpecification implements Serializable {

        @Field(type = FieldType.Keyword)
        private String id;

        @Field(type = FieldType.Keyword)
        private String name;

        private List<String> values;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommoditySKUAttribute implements Serializable {

        @Field(type = FieldType.Keyword)
        private String name;

        @Field(type = FieldType.Keyword)
        private String value;

    }

    /**
     * 买一送
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommodityGive implements Serializable {

        @Field(type = FieldType.Keyword)
        private String giveCommodityId;

        @Field(type = FieldType.Keyword)
        private String giveCommodityName;

        @Field(type = FieldType.Double)
        private Double price;

        @Field(type = FieldType.Keyword)
        private String description;
    }

    /**
     * 商品SKU　库存信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommoditySKUStore implements Serializable {

        @Field(type = FieldType.Keyword)
        private String commodityId;

        @Field(type = FieldType.Keyword)
        private String skuId;

        @Field(type = FieldType.Double)
        private Double price;

        @Field(type = FieldType.Integer)
        private Integer store;

        @Field(type = FieldType.Integer)
        private Integer sale;

        /**
         * suk 可以使用积分数
         */
        @Field(type = FieldType.Double)
        private Double integration;

        @Field(type = FieldType.Double)
        private Double promotionPrice;

        @Field(type = FieldType.Keyword)
        private String pic;

        @Field(type = FieldType.Nested)
        private List<CommoditySKUAttribute> attributeList;

    }

//    /**
//     * 积分使用限制
//     */
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class IntegralUseLimited {
//
//        /**
//         * <pre>
//         *
//         * 1: 购买总金额达到多少可以使用积分数
//         * 2:购买指定商品可使用积分数
//         * </pre>
//         */
//        @Field(type = FieldType.Keyword)
//        private String useType;
//
//        /**
//         * 商品 sku Id
//         */
//        private String skuId;
//
//        private List<String> otherCommodityIds;
//
//        /**
//         * 积分限制范围：1：本店，2：平台
//         */
//        @Field(type = FieldType.Integer)
//        private Integer useRange;
//
//        /**
//         * 购买满足多少金额，才可使用积分
//         */
//        @Field(type = FieldType.Double)
//        private Double fullAmount;
//
//        /**
//         * 最多可使用积分
//         */
//        @Field(type = FieldType.Integer)
//        private Integer maxUseIntegration;
//
//    }

    /**
     * 商品服务信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommodityServer implements Serializable {

        @Field(type = FieldType.Keyword)
        private String id;

        @Field(type = FieldType.Keyword)
        private String serviceDetail;

    }
}
