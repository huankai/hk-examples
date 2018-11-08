package com.hk.template;

import com.google.common.collect.Sets;
import com.hk.commons.util.StringUtils;

import java.io.File;
import java.util.Set;

/**
 * @author: kevin
 * @date: 2018-05-29 13:12
 */
public abstract class AbstractTemplate implements Template {

    private static final String FACTORIES_RESOURCE_PREFIX = "META-INF/templates/simple/";

    private static final String TEMPLATE_EXT = "vm";

    /**
     * 输出文件
     */
    private File outputFile;

    /**
     * 输出类名
     */
    private String className;

    /**
     * 注释
     */
    private String comment;

    /**
     * 作者
     */
    private String author;

    /**
     * 版本号
     */
    private String version;

    /**
     * string format date
     */
    private String dateAsString;

    /**
     * 模板所在路径
     */
    private String templatePath;

    /**
     * 模板需要导入的变量
     */
    private Set<String> importClassNameSet = Sets.newHashSet();

    @Override
    public File getOutputFile() {
        return outputFile;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public String getAuthor() {
        return StringUtils.isEmpty(author) ? Template.super.getAuthor() : author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public Set<String> getImportClassNames() {
        return importClassNameSet;
    }

    @Override
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public String getTemplatePath() {
        if (null == templatePath) {
            templatePath = String.format("%s/%s.%s", FACTORIES_RESOURCE_PREFIX, getClass().getSimpleName(), TEMPLATE_EXT);
        }
        return templatePath;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void setDateAsString(String date) {
        this.dateAsString = date;
    }

    @Override
    public String getDateAsString() {
        return StringUtils.isEmpty(dateAsString) ? Template.super.getDateAsString() : dateAsString;
    }
}
