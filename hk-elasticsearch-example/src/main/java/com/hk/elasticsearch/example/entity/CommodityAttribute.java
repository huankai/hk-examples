package com.hk.elasticsearch.example.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 商品属性信息
 *
 * @author huangkai
 * @date 2019/3/11 14:43
 */
@Data
public class CommodityAttribute implements Serializable {

    /**
     * 商品属性id
     */
    private String id;

    /**
     * 商品属性名
     */
    @Field(type = FieldType.Text)
    private String name;

    /**
     * 商品属性值
     */
    @Field(type = FieldType.Text)
    private String value;
}
