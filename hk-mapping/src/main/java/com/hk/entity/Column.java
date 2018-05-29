package com.hk.entity;

import lombok.Data;

/**
 * 列实体
 *
 * @author: huangkai
 * @date 2018-05-29 11:03
 */
@Data
public class Column {

    /**
     * 列名
     */
    private String name;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 是否为主键
     */
    private boolean primaryKey;

    /**
     * 是否可以为空
     */
    private boolean nullable;

    /**
     * 列类型
     */
    private String type;

    /**
     * 列字段类型
     */
    private String fieldType;

    /**
     * 注释
     */
    private String comment;

    public Column(String name, String fieldName, boolean primaryKey, boolean nullable, String type, String fieldType, String comment) {
        this.name = name;
        this.fieldName = fieldName;
        this.primaryKey = primaryKey;
        this.nullable = nullable;
        this.type = type;
        this.fieldType = fieldType;
        this.comment = comment;
    }
}
