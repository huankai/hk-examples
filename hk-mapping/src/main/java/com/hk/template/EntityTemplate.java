package com.hk.template;

import com.hk.entity.Table;

import java.util.Set;

/**
 * @author huangkai
 * @date 2018-5-30 20:46
 */
public interface EntityTemplate extends Template {

    BaseEntityTemplate getBaseEntityTemplate();

    Table getTable();

    /**
     * 要忽略的表字段
     *
     * @return
     */
    Set<String> getIngoreColumns();

    /**
     * 是否使用lombok框架,如果为true,会使用 Lombok 注解自动生成相关方法
     *
     * @return
     */
    boolean getUseLombokFramework();
}
