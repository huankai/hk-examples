package com.hk.template;

import com.google.common.collect.Sets;
import com.hk.entity.Table;

import java.util.Set;

/**
 * @author huangkai
 * @date 2018-5-30 21:23
 */
public class SimpleEntityTemplate extends AbstractTemplate implements EntityTemplate {

    private static final Set<String> lombokPackage = Sets.newHashSet("lombok.Data", "lombok.EqualsAndHashCode");

    /**
     * 是否使用lombok框架,如果为true,会使用 Lombok 注解自动生成相关方法
     */
    private boolean useLombokFramework;

    private Table table;

    private Set<String> ingoreColumns;

    private BaseEntityTemplate baseEntityTemplate;

    public SimpleEntityTemplate() {
        importVar("javax.persistence.*",
                "org.hibernate.annotations.GenericGenerator");

    }

    @Override
    public BaseEntityTemplate getBaseEntityTemplate() {
        return baseEntityTemplate;
    }

    @Override
    public void setBaseEntityTemplate(BaseEntityTemplate baseEntityTemplate) {
        this.baseEntityTemplate = baseEntityTemplate;
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public Set<String> getIngoreColumns() {
        return ingoreColumns;
    }

    @Override
    public void setIngoreColumns(Set<String> ingoreColumns) {
        this.ingoreColumns = ingoreColumns;
    }

    @Override
    public boolean getUseLombokFramework() {
        return useLombokFramework;
    }

    @Override
    public void setUseLombokFramework(boolean useLombokFramework) {
        this.useLombokFramework = useLombokFramework;
        if (useLombokFramework) {
            importVar(lombokPackage.toArray(new String[]{}));
        } else {
            getImportVar().removeAll(lombokPackage);
        }
    }
}
