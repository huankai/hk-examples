package com.hk.template;

import com.hk.entity.Table;

import java.util.Set;

/**
 * @author kevin
 * @date 2018-5-30 20:46
 */
public interface EntityTemplate extends Template {

    /**
     * Base EntityTemplate
     *
     * @return
     */
    BaseEntityTemplate getBaseEntityTemplate();

    /**
     * @param baseEntityTemplate
     */
    void setBaseEntityTemplate(BaseEntityTemplate baseEntityTemplate);

    /**
     * Table
     *
     * @return
     */
    Table getTable();

    /**
     * @param table
     */
    void setTable(Table table);

    /**
     * 要忽略的表字段
     *
     * @return
     */
    Set<String> getIngoreColumns();

    /**
     * @param ingoreColumns
     */
    void setIngoreColumns(Set<String> ingoreColumns);

    /**
     * 是否使用lombok框架,如果为true,会使用 Lombok 注解自动生成相关方法
     *
     * @return
     */
    boolean getUseLombokFramework();

    /**
     * @param useLombokFramework
     */
    void setUseLombokFramework(boolean useLombokFramework);
}
