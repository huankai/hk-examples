package com.hk.elasticsearch.example.entity;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

/**
 * 商品属性信息
 *
 * @author huangkai
 * @date 2019/3/11 14:43
 */
@Data
@SuppressWarnings("serial")
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
