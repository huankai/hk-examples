package com.hk.entity;

import com.hk.commons.util.StringUtils;
import lombok.Data;

import java.util.List;

/**
 * 表实体
 *
 * @author kevin
 * @date 2018-05-29 10:59
 */
@Data
public class Table {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 主键名
     */
    private String primaryKey;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 实体名 simpleName
     */
    private String className;

    /**
     * 实体主键字段
     */
    private String classPrimaryKeyField;

    /**
     * 列
     */
    private List<Column> columns;


    public Table(String tableName, String primaryKey, String comment, List<Column> columns) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.comment = comment;
        this.className = StringUtils.lineToBigHump(tableName);
        this.classPrimaryKeyField = StringUtils.lineToSmallHump(primaryKey);
        this.columns = columns;
    }
}
