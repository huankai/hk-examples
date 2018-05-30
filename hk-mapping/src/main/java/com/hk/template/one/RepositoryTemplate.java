package com.hk.template.one;

import com.hk.template.AbstractTemplate;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * @author: huangkai
 * @date 2018-05-29 17:27
 */
public class RepositoryTemplate extends AbstractTemplate {

    @Getter
    @Setter
    private String entityClassName;

    @Getter
    @Setter
    private String customReposirotyClassName;

    public RepositoryTemplate(File outputFile, String packageName, String customReposirotyClassName, String className, String entityClassName, String baseEntityClassName, String comment, String author) {
        setOutputFile(outputFile);
        setPackageName(packageName);
        setClassName(className);
        setBaseEntityClassName(baseEntityClassName);
        setComment(comment);
        setAuthor(author);
        this.entityClassName = entityClassName;
        this.customReposirotyClassName = customReposirotyClassName;
    }

    public String getEntitySimpleClassName() {
        return entityClassName.substring(entityClassName.lastIndexOf(".") + 1);
    }

    public String getCustomReposirotySimpleClassName() {
        return customReposirotyClassName.substring(customReposirotyClassName.lastIndexOf(".") + 1);
    }

}
