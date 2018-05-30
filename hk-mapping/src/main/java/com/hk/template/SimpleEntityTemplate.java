package com.hk.template;

import com.hk.entity.Table;

import java.io.File;
import java.util.Set;

/**
 * @author huangkai
 * @date 2018-5-30 21:23
 */
public class SimpleEntityTemplate extends AbstractTemplate implements EntityTemplate {

    /**
     * 是否使用lombok框架,如果为true,会使用 Lombok 注解自动生成相关方法
     */
    private boolean useLombok = true;

    private Table table;

    private Set<String> ingoreColumns;

    private BaseEntityTemplate baseEntityTemplate;

    public SimpleEntityTemplate(Table table, BaseEntityTemplate baseEntityTemplate,
                                File outputFile, String className, String templatePath) {
        super(outputFile, className, templatePath);
        this.table = table;
        this.baseEntityTemplate = baseEntityTemplate;
    }

    public SimpleEntityTemplate(Table table, BaseEntityTemplate baseEntityTemplate,
                                File outputFile, String className, String templatePath,
                                String comment, String author, String version) {
        super(outputFile, className, templatePath, comment, author, version);
        this.table = table;
        this.baseEntityTemplate = baseEntityTemplate;
    }

    @Override
    public Set<String> getIngoreColumns() {
        return ingoreColumns;
    }

    public void setUseLombok(boolean useLombok) {
        this.useLombok = useLombok;
    }

    public boolean isUseLombok() {
        return useLombok;
    }

    @Override
    public boolean getUseLombokFramework() {
        return useLombok;
    }

    public void setIngoreColumns(Set<String> ingoreColumns) {
        this.ingoreColumns = ingoreColumns;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public BaseEntityTemplate getBaseEntityTemplate() {
        return baseEntityTemplate;
    }

    public void setBaseEntityTemplate(BaseEntityTemplate baseEntityTemplate) {
        this.baseEntityTemplate = baseEntityTemplate;
    }

    @Override
    public Table getTable() {
        return table;
    }
}
