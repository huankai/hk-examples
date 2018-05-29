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

    public RepositoryTemplate(File outputFile, String packageName, String className, String entityClassName,String baseEntityClassName, String comment, String author) {
        setOutputFile(outputFile);
        setPackageName(packageName);
        setClassName(className);
        this.entityClassName = entityClassName;
        setBaseEntityClassName(baseEntityClassName);
        setComment(comment);
        setAuthor(author);
    }

    public String getEntityClassSimpleName() {
        return entityClassName.substring(entityClassName.lastIndexOf(".") + 1);
    }

}
