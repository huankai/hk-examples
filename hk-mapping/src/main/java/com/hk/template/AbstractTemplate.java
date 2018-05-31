package com.hk.template;

import com.hk.commons.util.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.net.URL;

/**
 * @author: huangkai
 * @date 2018-05-29 13:12
 */
public abstract class AbstractTemplate implements Template {

    private static final String FACTORIES_RESOURCE_PREFIX = "META-INF/templates/";

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
    private Resource templateResource;

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
    public void setTemplateResource(Resource resource) {
        this.templateResource = resource;
    }

    @Override
    public Resource getTemplateResource() {
        if (null == templateResource) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String simpleName = getClass().getSimpleName();
            URL url = classLoader.getResource(String.format("%s%s.%s", FACTORIES_RESOURCE_PREFIX, simpleName, TEMPLATE_EXT));
            if (null != url) {
                templateResource = new UrlResource(url);
            }
        }
        return templateResource;
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
